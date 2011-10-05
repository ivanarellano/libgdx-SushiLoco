package com.tinyrender.rollemup;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject extends PhysicsObject {
	public enum Type {
		PLAYER, SUSHI, PLATFORM;
	}
	
	public float size;
	public Vector2 pos;
	public Vector2 vel;
	public boolean isRolled = false;
	
	public GameObject(PhysicsWorld world) {
		super(world);
	}
	
	@Override
	public void enterContact(PhysicsObject collidesWith) {
	}

	@Override
	public void leaveContact(PhysicsObject leftCollisionWith) {		
	}
	
	public abstract void draw();
	public abstract Type getType();
}
