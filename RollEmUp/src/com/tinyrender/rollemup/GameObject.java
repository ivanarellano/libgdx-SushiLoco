package com.tinyrender.rollemup;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.box2d.PhysicsObject;

public abstract class GameObject extends PhysicsObject {
	public enum GameType {
		PLAYER, SUSHI, PLATFORM;
	}
	
	public boolean isRolled = false;
	public float rotation = 0.0f;
	public float size = 0.0f;
	public Vector2 pos;
	public Vector2 vel;
	
	public GameObjectRepresentation objectRepresentation;
	public GameType gameType;
	
	public GameObject(World world) {
		super(world);
		objectRepresentation = new GameObjectRepresentation(this);
	}
	
	public GameType getType() {
		return gameType;
	}
}
