package com.tinyrender.rollemup;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject extends PhysicsObject {
	public enum Type {
		PLAYER, SUSHI, PLATFORM;
	}
	
	public float size;
	public Vector2 pos;
	public Vector2 vel;
	public Type type;
	public boolean isRolled = false;
	
	public GameObject(PhysicsWorld world) {
		super(world);
	}
	
	public Type getType() {
		return type;
	}
	
	public abstract void draw();
}
