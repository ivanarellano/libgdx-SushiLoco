package com.tinyrender.rollemup;

import com.badlogic.gdx.math.Vector2;

public abstract class GameObject extends PhysicsObject {
	public enum GameType {
		PLAYER, SUSHI, PLATFORM;
	}
	
	public float size;
	public Vector2 pos;
	public Vector2 vel;
	public GameObjectRepresentation objectRepresentation;
	public GameType gameType;
	public boolean isRolled = false;
	
	public GameObject(PhysicsWorld world) {
		super(world);
		objectRepresentation = new GameObjectRepresentation(this);
	}
	
	public GameType getType() {
		return gameType;
	}
	
	public abstract void draw();
}
