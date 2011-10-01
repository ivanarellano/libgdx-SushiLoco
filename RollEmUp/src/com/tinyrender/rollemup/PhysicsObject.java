package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.Body;

public abstract class PhysicsObject {
	// disable collision
	final static short CATEGORY_NO_COLLISION = 0x0000;
	
	public PhysicsWorld world;
	public Body body;
	public int numContacts;
	
	public PhysicsObject(PhysicsWorld world) {
		this.world = world;
	}
	
	public void setBody(Body body) {
		this.body = body;
	}
	
	public abstract void update();
	public abstract void enterContact(PhysicsObject collidesWith);
	public abstract void leaveContact(PhysicsObject leftCollisionWith);
}
