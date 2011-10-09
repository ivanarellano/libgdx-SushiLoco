package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.Body;


public abstract class PhysicsObject {
	interface ContactResolver {
		public void enterContact(PhysicsObject collidesWith);
		public void leaveContact(PhysicsObject leftCollisionWith);
	}
	
	// disable collision
	final static short CATEGORY_NO_COLLISION = 0x0000;
	
	public ContactResolver contactResolver;
	public Body body;
	public PhysicsWorld world;
	public int numContacts;
	
	public PhysicsObject(PhysicsWorld world) {
		this.world = world;
		contactResolver = new ContactResolver() {
			@Override public void enterContact(PhysicsObject collidesWith) {}
			@Override public void leaveContact(PhysicsObject leftCollisionWith) {}
		};
	}
	
	public abstract void update();
}
