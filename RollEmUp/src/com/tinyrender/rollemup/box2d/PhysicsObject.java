package com.tinyrender.rollemup.box2d;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;


public abstract class PhysicsObject {
	public final static short CATEGORY_PLAYER		= 0x0001;
	public final static short CATEGORY_OBJECT		= 0x0002;
	public final static short CATEGORY_SUB_OBJECT	= 0x0004;
	public final static short MASK_NO_COLLISION		= 0;
	public final static short MASK_COLLIDE_ALL		= -1;
	public final static short MASK_PLAYER			= CATEGORY_OBJECT | CATEGORY_SUB_OBJECT;
	public final static short MASK_OBJECT			= MASK_COLLIDE_ALL & ~CATEGORY_SUB_OBJECT;
	public final static short MASK_SUB_OBJECT		= CATEGORY_PLAYER | CATEGORY_OBJECT;
	
	public interface ContactResolver {
		public void enterContact(PhysicsObject collidesWith);
		public void leaveContact(PhysicsObject leftCollisionWith);
	}
	
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
