package com.tinyrender.rollemup.box2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public abstract class PhysicsWorld implements ContactListener {	
	final static float UPDATE_INTERVAL = 1.0f / 60.0f;
	final static float MAX_CYCLES_PER_FRAME = 5.0f;
	static float timeAccumulator = 0.0f;
	
	public PhysicsWorld world;
	public World b2world;
	public Vector2 gravity;
	
	public PhysicsWorld() {
		world = this;
		gravity = new Vector2(0, -10.0f);
		b2world = new World(gravity, true);
		b2world.setContactListener(this);
	}

	public void physicsStep(float deltaTime) {
		b2world.step(Gdx.graphics.getDeltaTime(), 5, 2);
		
		timeAccumulator += deltaTime;
		if (timeAccumulator > (MAX_CYCLES_PER_FRAME * UPDATE_INTERVAL)) {
		    timeAccumulator = UPDATE_INTERVAL;
		}

		while (timeAccumulator >= UPDATE_INTERVAL) {
		    timeAccumulator -= UPDATE_INTERVAL;
		    b2world.step(UPDATE_INTERVAL, 5, 2);
		}
	}
	
	public void resumeWorld() {
		if(null == b2world)
			b2world = new World(gravity, true);
	}
	
	public void disposeWorld() {
		b2world.dispose();
		b2world = null;
	}
		
	@Override
	public void beginContact(Contact contact) {
		PhysicsObject objectA = (PhysicsObject) contact.getFixtureA().getBody().getUserData();
		PhysicsObject objectB = (PhysicsObject) contact.getFixtureB().getBody().getUserData();

		objectA.contactResolver.enterContact(objectB);
		objectB.contactResolver.enterContact(objectA);
	}

	@Override
	public void endContact(Contact contact) {
		PhysicsObject objectA = (PhysicsObject) contact.getFixtureA().getBody().getUserData();
		PhysicsObject objectB = (PhysicsObject) contact.getFixtureB().getBody().getUserData();

		objectA.contactResolver.leaveContact(objectB);
		objectB.contactResolver.leaveContact(objectA);
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {			
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {			
	}
}
