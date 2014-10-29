package com.scrappile.sushiloco.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.utils.Array;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.ExperienceChain;
import com.scrappile.sushiloco.GameObject;
import com.scrappile.sushiloco.Level;
import com.scrappile.sushiloco.box2d.BodyFactory;
import com.scrappile.sushiloco.box2d.PhysicsObject;
import com.scrappile.sushiloco.box2d.PhysicsWorld;
import com.scrappile.sushiloco.controller.PlayerController;

import java.util.ArrayList;

public class Player extends GameObject {
    public final static float MAX_VELOCITY = 7.0f;
    public final static float MAX_JUMP = 12.0f;
    public final static int STATE_IDLE = 0;
    public final static int STATE_FALLING = 1;
    public final static int STATE_JUMPING = 2;
    public final static int STATE_ROLLING = 3;
    public final static int DIRECTION_NONE = 0;
    public final static int DIRECTION_LEFT = 1;
    public final static int DIRECTION_RIGHT = 2;
    public int state;
    public int direction;
    public float growthScale = 1.3f;
    public float forceYOffset;
    public Vector2 vel = new Vector2();
	// The player's physical shape
	public CircleShape shape;
	// Detects when the player is grounded
	public GroundSensor groundSensor = new GroundSensor();
	public ExperienceChain xp = new ExperienceChain();
	public PlayerController controller = new PlayerController(this);
	public Array<GameObject> objectsToRoll = new Array<GameObject>();
	public Level worldLevel;
	GameObject rolledObj;

    public Player(Level worldLevel) {
        this.worldLevel = worldLevel;
        children.ensureCapacity(80);
        rolledObj = new GameObject();

        this.level = 1;
        this.type = Type.PLAYER;

        objRep.setTexture(Assets.atlas.findRegion("player"));

        float radius = (objRep.halfWidth) * 0.7f / Level.PTM_RATIO;

        // Generate box2d circle body
        body = BodyFactory.createCircle(427.0f / Level.PTM_RATIO, 64.0f / Level.PTM_RATIO, radius,
                0.8f, 0.0f, 1.0f, false, BodyType.DynamicBody, this);
        body.setActive(true);

        pos = body.getPosition();
        shape = (CircleShape) body.getFixtureList().get(0).getShape();

        // Set collision attributes
        Filter filter = new Filter();
        filter.categoryBits = PhysicsObject.Category.PLAYER;
        filter.maskBits = PhysicsObject.Mask.COLLIDE_ALL;
        body.getFixtureList().get(0).setFilterData(filter);

        // Set ground sensor just below player
        groundSensor.rect.width = 33.0f / Level.PTM_RATIO + radius;
        groundSensor.rect.height = 22.0f / Level.PTM_RATIO;

        forceYOffset = -(shape.getRadius() / 3.0f) * growthScale;

        // Determine outcome of box2d body collisions with player
        contactResolver = new ContactResolver() {
            @Override
            public void enterContact(PhysicsObject collidesWith) {
                // Collides with rollable object
                if (collidesWith.type == Type.ROLLABLE) {
                    GameObject otherObject = (GameObject) collidesWith.body.getUserData();

                    if (isRollable(otherObject))
                        objectsToRoll.add(otherObject);
                }
            }

            @Override
            public void leaveContact(PhysicsObject leftCollisionWith) {
            }
        };
    }

    @Override
    public void update() {
        vel = body.getLinearVelocity();
        pos = body.getPosition();
        rot = body.getAngle() * MathUtils.radiansToDegrees;

        // Find the current moving direction
        if (vel.x <= -0.1f)
            direction = DIRECTION_LEFT;
        else if (vel.x >= 0.1f)
            direction = DIRECTION_RIGHT;
        else
            direction = DIRECTION_NONE;

        // Find the current player state between: idle, jumping, falling, rolling
        if (vel.y < 0.0f && !isGrounded())
            state = STATE_FALLING;
        else if (vel.y > 0.1f)
            state = STATE_JUMPING;
        else if ((direction == DIRECTION_LEFT || direction == DIRECTION_RIGHT) && isGrounded())
            state = STATE_ROLLING;
        else if (isGrounded())
            state = STATE_IDLE;

        // Update current player level
        if (xp.justLeveledUp())
            levelUp();

        // Desktop player controls
        if (Gdx.input.isKeyPressed(Keys.A))
            body.applyForceToCenter(-40.0f, forceYOffset, true);
        else if (Gdx.input.isKeyPressed(Keys.D))
            body.applyForceToCenter(40.0f, forceYOffset, true);

        // Stick objects
        for (int i = 0; i < objectsToRoll.size; i++) {
            rolledObj = objectsToRoll.pop();

            controller.rollObject(rolledObj);
            xp.addPoints(rolledObj.points);
        }

        // Keep X velocity within maximum
        if (Math.abs(vel.x) > MAX_VELOCITY) {
            vel.x = Math.signum(vel.x) * MAX_VELOCITY;
            body.setLinearVelocity(vel.x, vel.y);
        }

        // Apply force when tilted, otherwise dampen down acceleration to stop
        if ((Gdx.input.getAccelerometerY() <= -0.35f && vel.x > -MAX_VELOCITY) ||
                Gdx.input.getAccelerometerY() >= 0.35f && vel.x < MAX_VELOCITY) {
            body.applyForceToCenter(Gdx.input.getAccelerometerY() * 0.55f, forceYOffset, true);
        } else {
            body.setLinearVelocity(vel.x * 0.9f, vel.y);
        }

        // Apply small impulse at low speeds to regain momentum
        if (vel.x < MAX_VELOCITY / 4.0f || vel.x > -MAX_VELOCITY / 4.0f)
            body.applyLinearImpulse(Gdx.input.getAccelerometerY() * 0.1f, 0.0f, pos.x, pos.y, true);

        // Update all rolled objects with player position and rotation
        for (int i = 0; i < children.size; i++) {
            children.get(i).rot = this.rot;
            children.get(i).pos.set(this.pos.x, this.pos.y);
        }

        // Update sensor position to let us know if we're grounded
        groundSensor.update();
    }

    public void levelUp() {
        xp.levelUp();
        controller.grow();
    }

    public boolean isRollable(GameObject otherObj) {
        if (otherObj.type == Type.ROLLABLE && otherObj.children.size == 0)
            if (otherObj.level <= xp.getLevelTag())
                return true;
        return false;
    }

    public boolean isGrounded() {
        return groundSensor.foundBodies.size() > 1;
    }

    public class GroundSensor implements QueryCallback {
        public Rectangle rect = new Rectangle();
        public ArrayList<Fixture> foundBodies = new ArrayList<Fixture>();

        @Override
        public boolean reportFixture(Fixture fixture) {
            foundBodies.add(fixture);
            return true;
        }

        public void update() {
            groundSensor.foundBodies.clear();

            groundSensor.rect.x = pos.x - groundSensor.rect.width / 2.0f;
            groundSensor.rect.y = pos.y - shape.getRadius() - groundSensor.rect.height;

            PhysicsWorld.b2world.QueryAABB(groundSensor,
                    groundSensor.rect.x, groundSensor.rect.y,
                    groundSensor.rect.width + groundSensor.rect.x,
                    groundSensor.rect.height + groundSensor.rect.y);
        }
    }
}