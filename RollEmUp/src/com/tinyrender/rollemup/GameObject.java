package com.tinyrender.rollemup;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tinyrender.rollemup.box2d.PhysicsObject;

public class GameObject extends PhysicsObject {
	public enum GameObjectType {
		PLAYER, ROLLABLE, STATIC;
	}
	
	public boolean isRolled;
	
	public int score;
	public int size;
	
	public float rot;
	public Vector2 pos = new Vector2();
	public Vector2 rolledPos = new Vector2();
	
	public GameObjectType gameObjType;
	public GameObjectRepresentation objRep = new GameObjectRepresentation(this);
	
	public Array<GameObject> subObj = new Array<GameObject>();
	
	public GameObject(World world) {
		super(world);
		subObj.shrink();
	}
	
	public GameObjectType getType() {
		return gameObjType;
	}

	public void update() {
		if (!isRolled) {
			pos = body.getPosition();
			rot = body.getAngle() * MathUtils.radiansToDegrees;
				
			for (int i = 0; i < subObj.size; i++) {
				subObj.get(i).pos = subObj.get(i).body.getPosition();
				subObj.get(i).rot = subObj.get(i).body.getAngle() * MathUtils.radiansToDegrees;
			}
		}
		
	}
	
}
