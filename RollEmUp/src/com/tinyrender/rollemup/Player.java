package com.tinyrender.rollemup;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
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
			numContacts++;
			isGrounded = true;
		}

		@Override
		public void leaveContact() {
			numContacts--;
			if(numContacts <= 0)
				isGrounded = false;
		}
	}
	
	final static float MAX_VELOCITY = 10.0f;

	public PlayerSensor sensor;
	
	public boolean isJumping = false;
	public float radius = 2.5f;
	
	ArrayList<GameObject> rolledObjects = new ArrayList<GameObject>();
	
	public Player(PhysicsWorld world) {
		super(world);
		body = createPlayerBody(BodyType.DynamicBody, 0.0f, 5.0f, radius, 1.0f);
		sensor = new PlayerSensor(0.0f, 5.0f-radius+0.8f, radius/1.3f, BodyType.DynamicBody, world);
		sensor.fixture = sensor.body.getFixtureList().get(0);
		
		Vector2 anchorA = new Vector2(body.getPosition().x, body.getPosition().y);
		RevoluteJointDef djd = new RevoluteJointDef();
		
		djd.initialize(body, sensor.body, anchorA);
		world.b2world.createJoint(djd);
		
		body.setUserData(this);
	}
	
	public void update() {
		vel = body.getLinearVelocity();
		pos = body.getPosition();

		// terminal velocity on x	
		if (Math.abs(vel.x) > MAX_VELOCITY) {			
			vel.x = Math.signum(vel.x) * MAX_VELOCITY;
			body.setLinearVelocity(vel.x, vel.y);
		}
 
		// apply a force when the phone is tilted enough,
		// otherwise dampen down the acceleration to a stop
		if ((Gdx.input.getAccelerometerY() <= -0.2f && vel.x > -MAX_VELOCITY) ||
				Gdx.input.getAccelerometerY() >= 0.2f && vel.x < MAX_VELOCITY) {
			body.applyForceToCenter(Gdx.input.getAccelerometerY() * 11.0f, 0);
		} else {
			body.setLinearVelocity(vel.x * 0.9f, vel.y);
		}

		// regain rolling momentum with a small impulse
		if (vel.x < MAX_VELOCITY/3 || vel.x > -MAX_VELOCITY/3)
			body.applyLinearImpulse(Gdx.input.getAccelerometerY() * 1.7f, 0, pos.x, pos.y);
		
		// jump if grounded
		if(isJumping) {
			isJumping = false;
			if(sensor.isGrounded) {
				body.applyLinearImpulse(0, 310.0f, pos.x, pos.y);
			}
		}
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

		if(collidesWith.objectType().equals(Type.SUSHI))
			Gdx.app.log("enterContact", "I hit sushi");
	}
	
	@Override
	public void leaveContact() {
		numContacts--;
	}

	@Override
	public Type objectType() {
		return Type.PLAYER;
	}
}
