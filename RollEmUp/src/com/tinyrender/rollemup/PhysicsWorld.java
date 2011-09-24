package com.tinyrender.rollemup;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public abstract class PhysicsWorld {
	final static float UPDATE_INTERVAL = 1.0f / 60.0f;
	final static float MAX_CYCLES_PER_FRAME = 5.0f;
	static float timeAccumulator = 0.0f;
	
	public World b2world;
	public WorldContactListener contactListener;
	public Vector2 gravity;
	
	public PhysicsWorld() {
		gravity = new Vector2(0, -10.0f);
		b2world = new World(gravity, true);
		contactListener = new WorldContactListener();
		b2world.setContactListener(contactListener);
	}

	public void physicsStep(float deltaTime) {
		b2world.step(Gdx.graphics.getDeltaTime(), 5, 2);
		
		timeAccumulator += deltaTime;
		if (timeAccumulator > (MAX_CYCLES_PER_FRAME * UPDATE_INTERVAL)) {
		    timeAccumulator = UPDATE_INTERVAL;
		}

		while (timeAccumulator >= UPDATE_INTERVAL) {
		    timeAccumulator -= UPDATE_INTERVAL;
		    b2world.step(UPDATE_INTERVAL, 3, 2);
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
	
	class WorldContactListener implements ContactListener{
		class ContactFixtures {
			Fixture fixtureA;
			Fixture fixtureB;
			
			ContactFixtures(Fixture fixA, Fixture fixB) {
				fixtureA = fixA;
				fixtureB = fixB;
			}
		}
		
		public ArrayList<ContactFixtures> contacts = new ArrayList<ContactFixtures>();
		
		@Override
		public void beginContact(Contact contact) {
			ContactFixtures contactPoint = new ContactFixtures(contact.getFixtureA(), contact.getFixtureB());
			contacts.add(contactPoint);
		}

		@Override
		public void endContact(Contact contact) {
			int contactsSize = contacts.size();

			for (int i = 0; i < contactsSize; i++ ) {
				if (contacts.get(i).fixtureA == contact.getFixtureA() &&
						contacts.get(i).fixtureB == contact.getFixtureB()) {
					contacts.remove(i);
					break;
				}
			}
		}

		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {			
		}

		@Override
		public void preSolve(Contact contact, Manifold oldManifold) {			
		}
		
	}
}
