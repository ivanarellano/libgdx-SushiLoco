package com.tinyrender.rollemup.box2d;

import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public abstract class PhysicsWorld implements ContactListener {
	public final static int PTM_RATIO = 64;
	Iterator<Body> bodiesList;
	final static float UPDATE_INTERVAL = 1.0f / 60.0f;
	final static float MINIMUM_TIMESTEP = UPDATE_INTERVAL / 2.0f;
	final static int MAX_CYCLES_PER_FRAME = 25;
	
	public Vector2 gravity = new Vector2(0, -12.0f);
	public World b2world = new World(gravity, true);
	
	public PhysicsWorld() {
		b2world.setContactListener(this);
	}

	public void physicsStep(float deltaTime) {
		float frameTime = deltaTime;
		int stepsPerformed = 0;
		
		while (frameTime > 0.0f && stepsPerformed < MAX_CYCLES_PER_FRAME) {
			float dt = Math.min(frameTime, UPDATE_INTERVAL);
			frameTime -= dt;
			
			if (frameTime < MINIMUM_TIMESTEP) {
				dt += frameTime;
				frameTime = 0.0f;
			}
			
			b2world.step(dt, 5, 3);
			stepsPerformed++;
		}
		
		b2world.clearForces();
	}
	
	public void resumeWorld() {
		if(null == b2world)
			b2world = new World(gravity, true);
	}
	
	public void disposeWorld() {
		b2world.dispose();
		b2world = null;
	}
	
	public Iterator<Body> getWorldBodies() {
		return b2world.getBodies();
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
