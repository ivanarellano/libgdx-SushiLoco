package com.tinyrender.rollemup;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tinyrender.rollemup.box2d.PhysicsObject;

public class GameObject extends PhysicsObject {
	public enum GameObjectType {
		PLAYER, ROLLABLE, PLATFORM;
	}
	
	public boolean isRolled = false;
	public float rotation = 0.0f;
	public Vector2 pos;
	public Vector2 vel;
	
	public GameObjectType gameType;
	public GameObjectRepresentation objectRepresentation;
	public Array<GameObject> subObjects = new Array<GameObject>();
	
	public GameObject(World world) {
		super(world);
		objectRepresentation = new GameObjectRepresentation(this);
		subObjects.shrink();
	}
	
	public GameObjectType getType() {
		return gameType;
	}

	@Override public void update() {}
}
