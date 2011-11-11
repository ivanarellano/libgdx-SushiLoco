package com.tinyrender.rollemup.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
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

	public boolean isJumping;
	public boolean isGrowing;
	float growthGoal;
	float growthScale = 1.27f;
	float forceYOffset;
	
	public Level level;
	CircleShape playerShape;
	PlayerSensor sensor;
	
	public PlayerController controller = new PlayerController(this);
	Array<GameObject> objectsToRoll = new Array<GameObject>(2);
	
	public Player(Level level, World world) {
		super(world);
		this.level = level;
		size = 1;
				
		objectRepresentation.setTexture(Assets.atlas.findRegion("player"));
		float radius = (objectRepresentation.texture.getRegionWidth()/2.0f)*0.7f / Level.PTM_RATIO;

		body = BodyFactory.createCircle(427.0f/Level.PTM_RATIO, 64.0f/Level.PTM_RATIO, radius,
										0.8f, 0.0f, 1.0f, false, BodyType.DynamicBody, world);
		pos = body.getPosition();
		gameObjType = GameObjectType.PLAYER;
		playerShape = (CircleShape) body.getFixtureList().get(0).getShape();
		body.setUserData(this);
		
		// Set collision attributes
		Filter filter = new Filter();
		filter.categoryBits = CATEGORY_PLAYER;
		filter.maskBits = MASK_COLLIDE_ALL;
		body.getFixtureList().get(0).setFilterData(filter);
		
		// Add sensor to player body
		sensor = new PlayerSensor(pos.x, pos.y-(radius/3.0f), radius/1.35f, BodyType.DynamicBody, world);
		JointFactory.revolute(body, sensor.body, pos.x, pos.y, world);
		
		forceYOffset = -(playerShape.getRadius() / 3.0f) * growthScale;
						
		contactResolver = new ContactResolver() {
			@Override
			public void enterContact(PhysicsObject collidesWith) {
				GameObject otherObject = (GameObject) collidesWith.body.getUserData();
				
				if (isRollable(otherObject)) {					
					objectsToRoll.add(otherObject);
				}
			}
			
			@Override public void leaveContact(PhysicsObject leftCollisionWith) { }
		};
	}
	
	@Override
	public void update() {
		pos = body.getPosition();
		vel = body.getLinearVelocity();
		rot = body.getAngle() * MathUtils.radiansToDegrees;
		/*
		if (isGrowing) {
			if (score >= growthGoal) {
				grow();
				
				isGrowing = false;
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
			isGrowing = true;
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
		
		// Regain momentum with small impulse
		if (vel.x < MAX_VELOCITY/4.0f || vel.x > -MAX_VELOCITY/4.0f)
			body.applyLinearImpulse(Gdx.input.getAccelerometerY()*0.1f, 0.0f, pos.x, pos.y);
 		
		// Jump
		if (isJumping) {
			isJumping = false;
			if (sensor.isGrounded)
				controller.jump(this, 10.0f);
		}
	}
	
	public void grow() {
		float sensorYOffset = -(playerShape.getRadius() / 3.0f) * growthScale;
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
}