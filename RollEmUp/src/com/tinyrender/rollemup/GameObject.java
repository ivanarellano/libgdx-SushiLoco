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
	public int level;
	
	public float rot;
	public Vector2 pos = new Vector2();
	public Vector2 rolledPos = new Vector2();
	
	public GameObjectType gameObjType;
	public GameObjectRepresentation objRep = new GameObjectRepresentation(this);
	
	public GameObject parentObj;
	public Array<GameObject> childObj = new Array<GameObject>();
	
	public GameObject(World world) {
		super(world);
		parentObj = new GameObject(world);
		childObj.shrink();
	}
	
	public GameObjectType getType() {
		return gameObjType;
	}

	public void update() {
		if (!isRolled) {
			pos = body.getPosition();
			rot = body.getAngle() * MathUtils.radiansToDegrees;
				
			for (int i = 0; i < childObj.size; i++) {
				childObj.get(i).pos = childObj.get(i).body.getPosition();
				childObj.get(i).rot = childObj.get(i).body.getAngle() * MathUtils.radiansToDegrees;
			}
		}
		
	}
	
}
