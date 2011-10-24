package com.tinyrender.rollemup.object;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.box2d.BodyFactory;
import com.tinyrender.rollemup.box2d.JointFactory;
import com.tinyrender.rollemup.box2d.PhysicsObject;
import com.tinyrender.rollemup.controller.PlayerController;

public class Player extends GameObject {	
	final static float MAX_VELOCITY = 6.0f;

	public boolean isJumping = false;
	public boolean isGrowing = false;
	public float mass;
	public float scaleAmount = 1.13f;
	CircleShape playerShape;
	PlayerSensor sensor;
	public PlayerController controller;
	
	public List<GameObject> objectsToRoll = new ArrayList<GameObject>();
	
	public Player(World world) {
		super(world);
		objectRepresentation.setTexture(Assets.atlas.findRegion("player"));
		float radius = (objectRepresentation.texture.getRegionWidth()/2.0f)*0.7f / Level.PTM_RATIO;

		body = BodyFactory.createCircle(427.0f/Level.PTM_RATIO, 64.0f/Level.PTM_RATIO, radius,
										1.0f, 0.0f, 1.0f, false, BodyType.DynamicBody, world);
		pos = body.getPosition();
		gameType = GameObjectType.PLAYER;
		playerShape = (CircleShape) body.getFixtureList().get(0).getShape();
		mass = body.getMass();
		body.setUserData(this);
		
		Filter filter = new Filter();
		filter.categoryBits = CATEGORY_PLAYER;
		filter.maskBits = MASK_COLLIDE_ALL;
		body.getFixtureList().get(0).setFilterData(filter);
		
		// Gdx.app.log("initPlayer", ""+ mass);
		
		// Add sensor to player body
		sensor = new PlayerSensor(pos.x, pos.y-(radius/3.0f), radius/1.35f, BodyType.DynamicBody, world);
		JointFactory.revolute(body, sensor.body, new Vector2(pos.x, pos.y), world);
		
		controller = new PlayerController(this);
		
		contactResolver = new ContactResolver() {
			@Override
			public void enterContact(PhysicsObject collidesWith) {
				GameObject otherObject = (GameObject) collidesWith.body.getUserData();
				numContacts++;
				if (otherObject.getType().equals(GameObjectType.ROLLABLE)) {
					objectsToRoll.add(otherObject);
					mass += otherObject.body.getMass();
					
					// Gdx.app.log("playerSize", "new: "+ mass + "  other: " + otherObject.body.getMass());
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
		rotation = body.getAngle()*180.0f/(float) Math.PI;
		
		if (isGrowing) {
			if (subObjects.size() % 4 == 0) {
				grow();
				
				if (scaleAmount >= 1.06f)
					scaleAmount -= 0.028f;
				
				isGrowing = false;
			}
		}
		
		if (Gdx.input.isKeyPressed(Keys.A))
			body.applyForceToCenter(-40.0f * body.getMass(), 0);
		else if (Gdx.input.isKeyPressed(Keys.D))
			body.applyForceToCenter(40.0f * body.getMass(), 0);
				
		// Stick newly rolled objects
		if (!objectsToRoll.isEmpty()) {
			for (GameObject obj : objectsToRoll)
				controller.rollObject(obj);
			objectsToRoll.clear();
		}

		// Set X velocity to MAX if we're going too fast
		if (Math.abs(vel.x) > MAX_VELOCITY) {			
			vel.x = Math.signum(vel.x) * MAX_VELOCITY;
			body.setLinearVelocity(vel.x, vel.y);
		}
		
		// Apply force when tilted, otherwise dampen down acceleration to stop
		if ((Gdx.input.getAccelerometerY() <= -0.35f && vel.x > -MAX_VELOCITY) ||
				Gdx.input.getAccelerometerY() >= 0.35f && vel.x < MAX_VELOCITY) {
			body.applyForceToCenter(Gdx.input.getAccelerometerY()*0.55f * body.getMass(), 0);
		} else {
			body.setLinearVelocity(vel.x * 0.9f, vel.y);
		}
		
		// Regain momentum with small impulse
		if (vel.x < MAX_VELOCITY/3 || vel.x > -MAX_VELOCITY/3)
			body.applyLinearImpulse(Gdx.input.getAccelerometerY()*0.1f * body.getMass(), 0, pos.x, pos.y);
 
		// Jump
		if (isJumping) {
			isJumping = false;
			if (sensor.isGrounded)
				controller.jump(this, 13.0f);
		}
	}
	
	public void grow() {
		float sensorYOffset = -(playerShape.getRadius() / 3.0f) * scaleAmount;
		controller.scaleCircle(this, scaleAmount, new Vector2(0.0f,0.0f));
		controller.scaleCircle(sensor, scaleAmount, new Vector2(0.0f, sensorYOffset));
	}
}