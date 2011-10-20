package com.tinyrender.rollemup.box2d;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;


public abstract class PhysicsObject {
	public interface ContactResolver {
		public void enterContact(PhysicsObject collidesWith);
		public void leaveContact(PhysicsObject leftCollisionWith);
	}
	
	// disable collision
	public final static short CATEGORY_NO_COLLISION = 0x0000;
	public final static short GROUP_ROLLABLE_OBJECT = -2;
	
	public ContactResolver contactResolver;
	public Body body;
	public World world;
	public int numContacts;
	
	public PhysicsObject(World world) {
		this.world = world;
		contactResolver = new ContactResolver() {
			@Override public void enterContact(PhysicsObject collidesWith) {}
			@Override public void leaveContact(PhysicsObject leftCollisionWith) {}
		};
	}
	
	public abstract void update();
}
