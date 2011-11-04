package com.tinyrender.rollemup;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tinyrender.rollemup.box2d.PhysicsObject;

public class GameObject extends PhysicsObject {
	public enum GameObjectType {
		PLAYER, ROLLABLE, PLATFORM;
	}
	
	public float rotation;
	public Vector2 pos;
	public Vector2 vel;
	
	public GameObjectType gameType;
	public GameObjectRepresentation objectRepresentation;
	public Array<GameObject> subObjects;
	
	public GameObject(World world) {
		super(world);
		objectRepresentation = new GameObjectRepresentation(this);
		subObjects = new Array<GameObject>();
		subObjects.shrink();
	}
	
	public GameObjectType getType() {
		return gameType;
	}

	@Override public void update() {
		pos = body.getPosition();
		rotation = body.getAngle() * MathUtils.radiansToDegrees;
		
		for (int i = 0; i < subObjects.size; i++) {
			subObjects.get(i).pos = subObjects.get(i).body.getPosition();
			subObjects.get(i).rotation = subObjects.get(i).body.getAngle() * MathUtils.radiansToDegrees;
		}
	}
}
