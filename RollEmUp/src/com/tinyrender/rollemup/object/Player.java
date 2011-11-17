package com.tinyrender.rollemup.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.box2d.BodyFactory;
import com.tinyrender.rollemup.box2d.JointFactory;
import com.tinyrender.rollemup.box2d.PhysicsObject;
import com.tinyrender.rollemup.controller.PlayerController;

public class Player extends GameObject {	
	final static float MAX_VELOCITY = 7.0f;
	public static boolean IS_GROWING;
	public final static int STATE_IDLE = 0;
	public final static int STATE_FALLING = 1;
	public final static int STATE_JUMPING = 2;
	public final static int STATE_ROLLING = 3;
	public final static int DIRECTION_NONE = 0;
	public final static int DIRECTION_LEFT = 1;
	public final static int DIRECTION_RIGHT = 2;
	
	public int state;
	public int direction;
	
	float growthGoal;
	float growthScale = 1.0f;
	float forceYOffset;
	
	public Vector2 vel = new Vector2();
	float tmpSubX;
	float tmpSubY;
	
	public CircleShape shape;
	PlayerSensor sensor;
	
	public PlayerController controller = new PlayerController(this);
	public Array<GameObject> objectsToRoll = new Array<GameObject>(2);
	
	public Level level;
	
	public Player(Level level, World world) {
		super(world);
		this.level = level;
		
		size = 1;
		gameObjType = GameObjectType.PLAYER;
		objRep.setTexture(Assets.atlas.findRegion("player"));
		
		float radius = (objRep.width/2.0f) * 0.7f / Level.PTM_RATIO;

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
		
		// Add sensor to player body
		sensor = new PlayerSensor(pos.x, pos.y-(radius/3.0f), radius/1.35f, BodyType.DynamicBody, world);
		JointFactory.revolute(body, sensor.body, pos.x, pos.y, world);
		
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
		
		/*
		if (IS_GROWING) {
			if (score >= growthGoal) {
				grow();
				
				IS_GROWING = false;
			}
		}
		*/
		
		// Desktop player controls
		if (Gdx.input.isKeyPressed(Keys.A))
			body.applyForceToCenter(-40.0f, forceYOffset);
		else if (Gdx.input.isKeyPressed(Keys.D))
			body.applyForceToCenter(40.0f, forceYOffset);

		// Stick newly rolled objects
		for (int i = 0; i < objectsToRoll.size; i++) {
			controller.rollObject(objectsToRoll.pop(), world);
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
		
		for (int i = 0; i < subObj.size; i++) {
			subObj.get(i).rot = this.rot;
			subObj.get(i).pos.set(this.pos.x * Level.PTM_RATIO, this.pos.y * Level.PTM_RATIO);
		}
	}
	
	public void grow() {
		float sensorYOffset = -(shape.getRadius() / 3.0f) * growthScale;
		forceYOffset = sensorYOffset;
		controller.scaleCircle(this, growthScale, 0.0f, 0.0f);
		controller.scaleCircle(sensor, growthScale, 0.0f, sensorYOffset);
	}
	
	public boolean isRollable(GameObject otherObj) {
		if (otherObj.getType().equals(GameObjectType.ROLLABLE))
			if (otherObj.size <= this.size)
				return true;
		return false;
	}
	
	public boolean isGrounded() {
		if (sensor.numContacts > 0)
			return true;
		return false;
	}
}