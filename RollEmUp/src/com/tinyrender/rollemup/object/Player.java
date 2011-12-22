package com.tinyrender.rollemup.object;

import java.util.ArrayList;

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
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.PlayerXP;
import com.tinyrender.rollemup.box2d.BodyFactory;
import com.tinyrender.rollemup.box2d.PhysicsObject;
import com.tinyrender.rollemup.controller.PlayerController;

public class Player extends GameObject {
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
			
			groundSensor.rect.x = pos.x - groundSensor.rect.width/2.0f;
			groundSensor.rect.y = pos.y - shape.getRadius() - groundSensor.rect.height;
			
			world.QueryAABB(groundSensor, 
					groundSensor.rect.x, groundSensor.rect.y, 
					groundSensor.rect.width + groundSensor.rect.x,
					groundSensor.rect.height + groundSensor.rect.y);
		}
	}
	
	final static float MAX_VELOCITY = 7.0f;
	public final static int STATE_IDLE = 0;
	public final static int STATE_FALLING = 1;
	public final static int STATE_JUMPING = 2;
	public final static int STATE_ROLLING = 3;
	public final static int DIRECTION_NONE = 0;
	public final static int DIRECTION_LEFT = 1;
	public final static int DIRECTION_RIGHT = 2;
	public static boolean IS_GROWING;
	
	public int state;
	public int direction;
	
	float growthScale = 1.3f;
	float forceYOffset;
	
	public Vector2 vel = new Vector2();
	
	public CircleShape shape;
	public GroundSensor groundSensor = new GroundSensor();
	public PlayerXP xp = new PlayerXP();
	public PlayerController controller = new PlayerController(this);
	private GameObject rolledObj;
	public Array<GameObject> objectsToRoll = new Array<GameObject>();
	
	public Level worldLevel;
	
	public Player(Level worldLevel, World world) {
		super(world);
		this.worldLevel = worldLevel;
		rolledObj = new GameObject(world);
		
		level = 1;
		gameObjType = GameObjectType.PLAYER;
		objRep.setTexture(Assets.atlas.findRegion("player"));
		
		float radius = (objRep.halfWidth) * 0.7f / Level.PTM_RATIO;

		body = BodyFactory.createCircle(427.0f/Level.PTM_RATIO, 64.0f/Level.PTM_RATIO, radius,
										0.8f, 0.0f, 1.0f, false, BodyType.DynamicBody, world);
		
		pos = body.getPosition();
		shape = (CircleShape) body.getFixtureList().get(0).getShape();
		body.setUserData(this);
		
		// Set collision attributes
		Filter filter = new Filter();
		filter.categoryBits = PhysicsObject.CATEGORY_PLAYER;
		filter.maskBits = PhysicsObject.MASK_COLLIDE_ALL;
		body.getFixtureList().get(0).setFilterData(filter);
		
		groundSensor.rect.width = radius;
		groundSensor.rect.height = 15.0f / Level.PTM_RATIO;
		
		forceYOffset = -(shape.getRadius() / 3.0f) * growthScale;
						
		contactResolver = new ContactResolver() {
			@Override
			public void enterContact(PhysicsObject collidesWith) {
				GameObject otherObject = (GameObject) collidesWith.body.getUserData();
				
				if (isRollable(otherObject))			
					objectsToRoll.add(otherObject);
			}
			
			@Override public void leaveContact(PhysicsObject leftCollisionWith) { }
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
		
		if (IS_GROWING) {
			// Level up
			if (xp.currentLevel.tag != PlayerXP.MAX_LEVEL.tag && score >= xp.nextLevel.score) {
				grow();
				xp.levelUp();
			}
			
			IS_GROWING = false;
		}
				
		// Desktop player controls
		if (Gdx.input.isKeyPressed(Keys.A))
			body.applyForceToCenter(-40.0f, forceYOffset);
		else if (Gdx.input.isKeyPressed(Keys.D))
			body.applyForceToCenter(40.0f, forceYOffset);

		// Stick objects
		for (int i = 0; i < objectsToRoll.size; i++) {
			rolledObj = objectsToRoll.pop();
			
			// Update if object is rolled
			if (controller.rollObject(rolledObj)) {
				score += rolledObj.score;
			}
		}
			
		// Set X velocity to MAX if we're going too fast
		if (Math.abs(vel.x) > MAX_VELOCITY) {			
			vel.x = Math.signum(vel.x) * MAX_VELOCITY;
			body.setLinearVelocity(vel.x, vel.y);
		}
		
		// Apply force when tilted, otherwise dampen down acceleration to stop
		if ((Gdx.input.getAccelerometerY() <= -0.35f && vel.x > -MAX_VELOCITY) ||
				Gdx.input.getAccelerometerY() >= 0.35f && vel.x < MAX_VELOCITY) {
			body.applyForceToCenter(Gdx.input.getAccelerometerY()*0.55f, forceYOffset);
		} else {
			body.setLinearVelocity(vel.x * 0.9f, vel.y);
		}
		
		// Apply small impulse at low speeds to regain momentum
		if (vel.x < MAX_VELOCITY/4.0f || vel.x > -MAX_VELOCITY/4.0f)
			body.applyLinearImpulse(Gdx.input.getAccelerometerY() * 0.1f, 0.0f, pos.x, pos.y);
		
		for (int i = 0; i < childObj.size; i++) {
			childObj.get(i).rot = this.rot;
			childObj.get(i).pos.set(this.pos.x * Level.PTM_RATIO, this.pos.y * Level.PTM_RATIO);
		}

		groundSensor.update();
	}
	
	public void grow() {
		forceYOffset = -(shape.getRadius() / 4.5f) * growthScale;
		controller.scaleCircle(this, growthScale, 0.0f, 0.0f);
	}
	
	public boolean isRollable(GameObject otherObj) {
		if (otherObj.getType().equals(GameObjectType.ROLLABLE) && otherObj.childObj.size == 0)
			if (otherObj.level <= xp.currentLevel.tag)
				return true;
		return false;
	}
	
	public boolean isGrounded() {
		if (groundSensor.foundBodies.size() > 1)
			return true;
		return false;
	}
}