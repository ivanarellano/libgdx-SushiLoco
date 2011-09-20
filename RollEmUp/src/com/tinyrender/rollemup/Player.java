package com.tinyrender.rollemup;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Player {
	final static float MAX_VELOCITY = 10.0f;

	public Body body;
	public Body groundSensor;
	public Fixture bodyFixture;
	public Fixture groundSensorFixture;
	
	Vector2 vel;
	Vector2 pos;
	
	public boolean isGrounded;
	public boolean isJumping;
	
	World b2world;
		
	public Player(World world) {
		b2world = world;
		body = createPlayer(BodyType.DynamicBody, 0, 5.0f, 2.5f, 1.0f);
		body.setUserData("player");
	}
	
	public void update() {
		vel = body.getLinearVelocity();
		pos = body.getPosition();
		isGrounded = isPlayerGrounded();

		// terminal velocity on x	
		if (Math.abs(vel.x) > MAX_VELOCITY) {			
			vel.x = Math.signum(vel.x) * MAX_VELOCITY;
			body.setLinearVelocity(vel.x, vel.y);
		}
 
		if ((Gdx.input.getAccelerometerY() <= -0.2f && vel.x > -MAX_VELOCITY) ||
				Gdx.input.getAccelerometerY() >= 0.2f && vel.x < MAX_VELOCITY)
			body.applyForceToCenter(Gdx.input.getAccelerometerY() * 12.0f, 0);

		if (vel.x < MAX_VELOCITY/3 || vel.x > -MAX_VELOCITY/3)
			body.applyLinearImpulse(Gdx.input.getAccelerometerY() * 2.0f, 0, pos.x, pos.y);
		
		if(isJumping) {
			isJumping = false;
			if(isGrounded)
				body.applyLinearImpulse(0, 320.0f, pos.x, pos.y);
		}
	}
	
	private Body createPlayer(BodyType bodyType, float x, float y, float radius, float density) {
		BodyDef bd = new BodyDef();
		bd.type = bodyType;
		bd.position.set(x, y);
		Body body = b2world.createBody(bd);
 
		CircleShape shape = new CircleShape();		
		shape.setRadius(radius);
		
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = density;
		fd.friction = 1.0f;
		bodyFixture = body.createFixture(fd);
		shape.dispose();
		
		groundSensor = createPlayerSensor(x, y-radius+.5f, radius/2);
		
		return body;
	}
	
	private Body createPlayerSensor(float x, float y, float radius) {
		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = BodyType.DynamicBody;
		Body body = b2world.createBody(bd);
 
		CircleShape shape = new CircleShape();		
		shape.setRadius(radius);
		
		FixtureDef fd = new FixtureDef();
		fd.isSensor = true;
		fd.shape = shape;
		groundSensorFixture = body.createFixture(fd);
		shape.dispose();
		
		return body;
	}
	
	private boolean isPlayerGrounded() {				
		List<Contact> contactList = b2world.getContactList();
		for(int i = 0; i < contactList.size(); i++) {
			Contact contact = contactList.get(i);
			if(contact.isTouching() && (contact.getFixtureA() == groundSensorFixture ||
			   contact.getFixtureB() == groundSensorFixture)) {
				return true;
			}
		}
		return false;
	}
}
