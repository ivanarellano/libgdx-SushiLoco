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
	
	public int score;
	public int size;
	public float rot;
	public Vector2 pos;
	public Vector2 vel;
	
	public GameObjectType gameObjType;
	public GameObjectRepresentation objectRepresentation = new GameObjectRepresentation(this);
	public Array<GameObject> subObjects = new Array<GameObject>();
	
	public GameObject(World world) {
		super(world);
		subObjects.shrink();
	}
	
	public GameObjectType getType() {
		return gameObjType;
	}

	@Override
	public void update() {
		pos = body.getPosition();
		rot = body.getAngle() * MathUtils.radiansToDegrees;
		
		for (int i = 0; i < subObjects.size; i++) {
			subObjects.get(i).pos = subObjects.get(i).body.getPosition();
			subObjects.get(i).rot = subObjects.get(i).body.getAngle() * MathUtils.radiansToDegrees;
		}
	}
}
