package com.tinyrender.rollemup.object;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.Sensor;
import com.tinyrender.rollemup.box2d.BodyFactory;
import com.tinyrender.rollemup.box2d.JointFactory;
import com.tinyrender.rollemup.box2d.PhysicsObject;
import com.tinyrender.rollemup.controller.PlayerController;

public class Player extends GameObject {
	class PlayerSensor extends Sensor {
		public boolean isGrounded = false;
		
		PlayerSensor(float x, float y, float radius, BodyType bodyType, World world) {
			super(x, y, radius, bodyType, world);
			
			contactResolver = new ContactResolver() {
				@Override
				public void enterContact(PhysicsObject collidesWith) {
					numContacts++;
					isGrounded = true;
				}

				@Override
				public void leaveContact(PhysicsObject leftCollisionWith) {
					numContacts--;
					if (numContacts <= 0) 
						isGrounded = false;
				}
			};
		}
	}
	
	final static float MAX_VELOCITY = 4.0f;

	public PlayerSensor sensor;
	public PlayerController controller;
	public boolean isJumping = false;
	public boolean isGrowing = false;
	public float scaleAmount = 1.13f;
	
	public List<GameObject> objectsToRoll = new ArrayList<GameObject>();
	public List<GameObject> objectsRolled = new ArrayList<GameObject>();
	
	public Player(World world) {
		super(world);

		float radius = (Assets.player.getRegionWidth()/2.0f)*0.7f /Level.PTM_RATIO;
		
		body = BodyFactory.createCircle(427.0f/Level.PTM_RATIO, 64.0f/Level.PTM_RATIO, radius,
										1.0f,
										0.0f, 1.0f, false, BodyType.DynamicBody, world);
		pos = body.getPosition();
				
		sensor = new PlayerSensor(pos.x, pos.y+(radius*-0.5f), radius/1.35f, BodyType.DynamicBody, world);
		
		// join sensor to player body
		JointFactory.revolute(body, sensor.body, new Vector2(pos.x, pos.y), world);
		
		controller = new PlayerController(this);
		objectRepresentation.setTexture(Assets.player);
		gameType = GameObjectType.PLAYER;
		body.setUserData(this);
		
		contactResolver = new ContactResolver() {
			@Override
			public void enterContact(PhysicsObject collidesWith) {
				GameObject otherObject = (GameObject) collidesWith.body.getUserData();
				numContacts++;
				if (otherObject.getType().equals(GameObjectType.ROLLABLE)) {
					objectsToRoll.add(otherObject);
					size += otherObject.size;
					
					//Gdx.app.log("player", "size: " + size);
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
		if (isGrowing) {
			if (objectsRolled.size() % 4 == 0) {
				grow();
				
				if (scaleAmount >= 1.06f)
					scaleAmount -= 0.028f;
				
				//Gdx.app.log("scale", ": " + scaleAmount);
				
				isGrowing = false;
			}
		}
		
		vel = body.getLinearVelocity();
		pos = body.getPosition();
		rotation = body.getAngle()*180.0f/(float) Math.PI;
		
		if (Gdx.input.isKeyPressed(Keys.A))
			body.applyForceToCenter(-25.0f * body.getMass(), 0);
		else if (Gdx.input.isKeyPressed(Keys.D))
			body.applyForceToCenter(25.0f * body.getMass(), 0);
				
		// stick newly rolled objects
		if (!objectsToRoll.isEmpty()) {
			for (GameObject obj : objectsToRoll)
				controller.rollObject(obj);
			objectsToRoll.clear();
		}

		// terminal velocity on x	
		if (Math.abs(vel.x) > MAX_VELOCITY) {			
			vel.x = Math.signum(vel.x) * MAX_VELOCITY;
			body.setLinearVelocity(vel.x, vel.y);
		}
 
		// apply force when tilted, otherwise
		// dampen down acceleration to stop
		if ((Gdx.input.getAccelerometerY() <= -0.2f && vel.x > -MAX_VELOCITY) ||
				Gdx.input.getAccelerometerY() >= 0.2f && vel.x < MAX_VELOCITY) {
			body.applyForceToCenter(Gdx.input.getAccelerometerY()*0.1f * body.getMass(), 0);
		} else {
			body.setLinearVelocity(vel.x * 0.9f, vel.y);
		}

		// regain momentum with small impulse
		if (vel.x < MAX_VELOCITY/3 || vel.x > -MAX_VELOCITY/3)
			body.applyLinearImpulse(Gdx.input.getAccelerometerY()*0.1f * body.getMass(), 0, pos.x, pos.y);
		
		// jump if grounded
		if (isJumping) {
			isJumping = false;
			if (sensor.isGrounded)
				controller.jump(this, 17.0f);
		}
	}
	
	public void grow() {
		controller.scaleCircle(this, scaleAmount, new Vector2(0.0f,0.0f));
		controller.scaleCircle(sensor, scaleAmount, new Vector2(0.0f, scaleAmount*-0.1f));
	}
}