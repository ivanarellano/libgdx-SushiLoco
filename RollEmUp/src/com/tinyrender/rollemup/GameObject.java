package com.tinyrender.rollemup;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.box2d.PhysicsObject;

public class GameObject extends PhysicsObject {
	public enum GameObjectType {
		PLAYER, ROLLABLE, PLATFORM;
	}
	
	public boolean isRolled = false;
	public float size = 0.0f;
	public float rotation = 0.0f;
	public Vector2 pos;
	public Vector2 vel;
	
	public GameObjectType gameType;
	public GameObjectRepresentation objectRepresentation;
	public List<GameObject> subObjects = new ArrayList<GameObject>();
	
	public GameObject(World world) {
		super(world);
		objectRepresentation = new GameObjectRepresentation(this);
	}
	
	public GameObjectType getType() {
		return gameType;
	}

	@Override public void update() {}
}
