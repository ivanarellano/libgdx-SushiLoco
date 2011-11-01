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

	public boolean isJumping = false;
	public boolean isGrowing = false;
	public float mass;
	public float growthGoal;
	public float growthScale;
	CircleShape playerShape;
	PlayerSensor sensor;
	public PlayerController controller;
	
	public Array<GameObject> objectsToRoll = new Array<GameObject>(2);
	//public int stuffTillGrowth = 0;
	
	public Player(World world) {
		super(world);
		objectRepresentation.setTexture(Assets.atlas.findRegion("player"));
		float radius = (objectRepresentation.texture.getRegionWidth()/2.0f)*0.7f / Level.PTM_RATIO;

		body = BodyFactory.createCircle(427.0f/Level.PTM_RATIO, 64.0f/Level.PTM_RATIO, radius,
										2.0f, 0.0f, 1.0f, false, BodyType.DynamicBody, world);
		pos = body.getPosition();
		gameType = GameObjectType.PLAYER;
		playerShape = (CircleShape) body.getFixtureList().get(0).getShape();
		mass = body.getMass();
		body.setUserData(this);
		
		Filter filter = new Filter();
		filter.categoryBits = CATEGORY_PLAYER;
		filter.maskBits = MASK_COLLIDE_ALL;
		body.getFixtureList().get(0).setFilterData(filter);
		
		// Add sensor to player body
		sensor = new PlayerSensor(pos.x, pos.y-(radius/3.0f), radius/1.35f, BodyType.DynamicBody, world);
		JointFactory.revolute(body, sensor.body, pos.x, pos.y, world);
		
		growthScale = 1.27f;
		growthGoal = mass * growthScale;
		
		//Gdx.app.log("initPlayer", "mass: " + Float.toString(mass) + " _growthGoal: " + Float.toString(growthGoal));
		
		controller = new PlayerController(this);
		
		contactResolver = new ContactResolver() {
			@Override
			public void enterContact(PhysicsObject collidesWith) {
				GameObject otherObject = (GameObject) collidesWith.body.getUserData();
				numContacts++;

				if (isRollable(otherObject)) {
					//stuffTillGrowth++;
					
					objectsToRoll.add(otherObject);
					mass += otherObject.body.getMass();
					
					//Gdx.app.log("stuffTillGrowth", Integer.toString(stuffTillGrowth));
					Gdx.app.log("playerSize", "new: "+ mass + "  other: " + otherObject.body.getMass());
				}
			}
			
			@Override
			public void leaveContact(PhysicsObject leftCollisionWith) {
				numContacts--;
			}
		};
	}
	
	@Override
	public void update() {
		vel = body.getLinearVelocity();
		pos = body.getPosition();
		rotation = body.getAngle() * MathUtils.radiansToDegrees;
		
		if (isGrowing) {
			if (mass >= growthGoal) {
				//stuffTillGrowth = 0;
				grow();
				growthGoal = mass * growthScale;
				
				//Gdx.app.log("grow!", "scale: "+ Float.toString(growthScale) + " _mass: " + Float.toString(mass)
				//				+ " _goal: " + Float.toString(growthGoal));
				
				if (growthScale <= 1.0f)
					growthScale = 1.27f;
				
				growthScale /= 1.01f;

				isGrowing = false;
			}
		}
		
		// Desktop player controls
		if (Gdx.input.isKeyPressed(Keys.A))
			body.applyForceToCenter(-40.0f * body.getMass(), 0);
		else if (Gdx.input.isKeyPressed(Keys.D))
			body.applyForceToCenter(40.0f * body.getMass(), 0);
				
		// Stick newly rolled objects
		for (int i = 0; i < objectsToRoll.size; i++)
			controller.rollObject(objectsToRoll.pop());
		
		// Set X velocity to MAX if we're going too fast
		if (Math.abs(vel.x) > MAX_VELOCITY) {			
			vel.x = Math.signum(vel.x) * MAX_VELOCITY;
			body.setLinearVelocity(vel.x, vel.y);
		}
		
		// Apply force when tilted, otherwise dampen down acceleration to stop
		if ((Gdx.input.getAccelerometerY() <= -0.35f && vel.x > -MAX_VELOCITY) ||
				Gdx.input.getAccelerometerY() >= 0.35f && vel.x < MAX_VELOCITY) {
			body.applyForceToCenter(Gdx.input.getAccelerometerY()*0.55f * body.getMass(), 0.0f);
		} else {
			body.setLinearVelocity(vel.x * 0.9f, vel.y);
		}
		
		// Regain momentum with small impulse
		if (vel.x < MAX_VELOCITY/4.0f || vel.x > -MAX_VELOCITY/4.0f)
			body.applyLinearImpulse(Gdx.input.getAccelerometerY()*0.1f * body.getMass(), 0.0f, pos.x, pos.y);
 		
		// Jump
		if (isJumping) {
			isJumping = false;
			if (sensor.isGrounded)
				controller.jump(this, 8.5f);
		}
	}
	
	public void grow() {
		float sensorYOffset = -(playerShape.getRadius() / 3.0f) * growthScale;
		controller.scaleCircle(this, growthScale, 0.0f, 0.0f);
		controller.scaleCircle(sensor, growthScale, 0.0f, sensorYOffset);
	}
	
	public boolean isRollable(GameObject obj) {
		if (obj.getType().equals(GameObjectType.ROLLABLE))
			if (obj.body.getMass() < mass)
				return true;
		
		return false;
	}
}