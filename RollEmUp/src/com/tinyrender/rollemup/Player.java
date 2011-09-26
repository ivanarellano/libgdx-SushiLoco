package com.tinyrender.rollemup;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Player extends GameObject {
	class PlayerSensor extends Sensor {
		public Fixture fixture;
		public boolean isGrounded = false;
		
		PlayerSensor(float x, float y, float radius, BodyType bodyType, PhysicsWorld world) {
			super(x, y, radius, bodyType, world);
		}
		
		@Override
		public void enterContact(GameObject collidesWith) {
			if (!collidesWith.isRolled) {
				numContacts++;
				
				if (collidesWith.getType().equals(Type.SUSHI)) {
					numContacts--;
					objectsToRoll.add(collidesWith);
				}
				
				isGrounded = true;
			}
		}

		@Override
		public void leaveContact(GameObject leftCollisionWith) {
			if (!leftCollisionWith.isRolled)
				numContacts--;
			
			if (numContacts <= 0) 
				isGrounded = false;
		}
		
		@Override
		public Type getType() {
			return Type.PLAYER_SENSOR;
		}
	}
	
	final static float MAX_VELOCITY = 10.0f;

	public PlayerSensor sensor;
	
	public boolean isJumping = false;
	public float radius = 2.5f;
	
	List<GameObject> objectsToRoll = new ArrayList<GameObject>();
	ArrayList<GameObject> objectsRolled = new ArrayList<GameObject>();
	
	public Player(PhysicsWorld world) {
		super(world);
		body = createPlayerBody(BodyType.DynamicBody, 0.0f, 5.0f, radius, 1.0f);
		pos = body.getPosition();
		
		sensor = new PlayerSensor(pos.x, pos.y-1.0f, radius/1.1f, BodyType.DynamicBody, world);
		sensor.fixture = sensor.body.getFixtureList().get(0);

		Vector2 anchorA = new Vector2(pos.x, pos.y);
		RevoluteJointDef rjd = new RevoluteJointDef();
		rjd.initialize(body, sensor.body, anchorA);
		world.b2world.createJoint(rjd);

		body.setUserData(this);
	}
	
	@Override
	public void update() {
		vel = body.getLinearVelocity();
		pos = body.getPosition();
		
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
			body.applyForceToCenter(Gdx.input.getAccelerometerY() * 11.0f, 0);
		} else {
			body.setLinearVelocity(vel.x * 0.9f, vel.y);
		}

		// regain momentum with small impulse
		if (vel.x < MAX_VELOCITY/3 || vel.x > -MAX_VELOCITY/3)
			body.applyLinearImpulse(Gdx.input.getAccelerometerY() * 2.0f, 0, pos.x, pos.y);
		
		// jump if grounded
		if(isJumping) {
			isJumping = false;
			if(sensor.isGrounded)
				body.applyLinearImpulse(0, 310.0f, pos.x, pos.y);
		}
	}
	
	public void stickObject(GameObject other) {
		other.isRolled = true;
		objectsRolled.add(other);
		
		Utils.weldJoints(body, other.body, new Vector2(pos.x, pos.y), world.b2world);
		
		other.body.setAngularVelocity(0.0f);
		Fixture otherFix = other.body.getFixtureList().get(0);
		Filter filter = new Filter();
		filter.maskBits = GameObject.CATEGORY_NO_COLLISION;
		otherFix.setSensor(true);
		otherFix.setFilterData(filter);
	}
	
	private Body createPlayerBody(BodyType bodyType, float x, float y, float radius, float density) {
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
	
	@Override
	public void enterContact(GameObject collidesWith) {
		numContacts++;
	}
	
	@Override
	public void leaveContact(GameObject leftCollisionWith) {
		numContacts--;
	}

	@Override
	public Type getType() {
		return Type.PLAYER;
	}
}
