package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class GameObject extends PhysicsObject{
	public enum Type {
		PLAYER, SUSHI, PLATFORM;
	}
	
	public Sprite sprite;
	public int size;
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
	
	public abstract Type getType();
}
