package com.tinyrender.rollemup;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Player extends GameObject {
	class PlayerSensor extends Sensor {
		public boolean isGrounded = false;
		
		PlayerSensor(float x, float y, float radius, BodyType bodyType, PhysicsWorld world) {
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
	public boolean isJumping = false;
	public boolean isGrowing = false;
	public float radius;
	
	List<GameObject> objectsToRoll = new ArrayList<GameObject>();
	ArrayList<GameObject> objectsRolled = new ArrayList<GameObject>();
	
	public Player(PhysicsWorld world) {
		super(world);
		radius = (Assets.player.getRegionWidth()/2.0f)*0.7f /Level.PTM_RATIO;
		body = createPlayerBody(427.0f/Level.PTM_RATIO, 64.0f/Level.PTM_RATIO, radius, 1.0f, BodyType.DynamicBody);
		pos = body.getPosition();
				
		sensor = new PlayerSensor(pos.x, pos.y+(radius*-0.4f), radius/1.35f, BodyType.DynamicBody, world);
		// join sensor to player body
		Utils.revoluteJoint(body, sensor.body, new Vector2(pos.x, pos.y), world.b2world);
		
		type = Type.PLAYER;
		body.setUserData(this);
		
		contactResolver = new ContactResolver() {
			@Override
			public void enterContact(PhysicsObject collidesWith) {
				GameObject otherObject = (GameObject) collidesWith.body.getUserData();
				numContacts++;
				if (otherObject.getType().equals(Type.SUSHI)) {
					objectsToRoll.add(otherObject);
					size += otherObject.size; // TODO: otherObject.size is always 0
					//Gdx.app.log("size", Float.toString(size));
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
				isGrowing = false;
			}
		}
		
		vel = body.getLinearVelocity();
		pos = body.getPosition();
		
		if (Gdx.input.isKeyPressed(Keys.A))
			body.applyForceToCenter(-25.0f * body.getMass(), 0);
		else if (Gdx.input.isKeyPressed(Keys.D))
			body.applyForceToCenter(25.0f * body.getMass(), 0);
				
		// stick newly rolled objects
		if (!objectsToRoll.isEmpty()) {
			for (GameObject obj : objectsToRoll)
				stickObject(obj);
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
				body.applyLinearImpulse(0, 17.0f * body.getMass(), pos.x, pos.y);
		}
	}
	
	public void draw() {
		Assets.batch.draw(Assets.player,
				(pos.x-0.5f) * Level.PTM_RATIO, (pos.y-0.5f) * Level.PTM_RATIO,
				32.0f, 32.0f,
				64.0f, 64.0f,
				1.0f, 1.0f,
				body.getAngle()*180.0f/(float) Math.PI);
	}
	
	public void grow() {
		radius *= 1.13f;
		
		Fixture fixture = body.getFixtureList().get(0);
		CircleShape shape = (CircleShape) fixture.getShape();
		shape.setRadius(radius);
		
		fixture = sensor.body.getFixtureList().get(0);
		shape = (CircleShape) fixture.getShape();
		shape.setPosition(new Vector2(0.0f, radius*-0.35f));
		shape.setRadius(radius/1.35f);
	}
	
	private void stickObject(GameObject other) {
		other.isRolled = true;
		objectsRolled.add(other);
		other.body.setAngularVelocity(0.0f);
		
		Utils.weldJoint(body, other.body, new Vector2(pos.x, pos.y), world.b2world);
		
		Fixture otherFix = other.body.getFixtureList().get(0);
		Filter filter = new Filter();
		filter.maskBits = GameObject.CATEGORY_NO_COLLISION;
		otherFix.setSensor(true);
		otherFix.setFilterData(filter);
		
		body.resetMassData();
		
		isGrowing = true;
	}
	
	private Body createPlayerBody(float x, float y, float radius, float density, BodyType bodyType) {
		BodyDef bd = new BodyDef();
		bd.type = bodyType;
		bd.position.set(x, y);
		Body body = world.b2world.createBody(bd);
 
		CircleShape shape = new CircleShape();		
		shape.setRadius(radius);
		
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = density;
		fd.friction = 1.0f;
		body.createFixture(fd);
		shape.dispose();
		
		return body;
	}
}