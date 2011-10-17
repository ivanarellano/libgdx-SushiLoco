package com.tinyrender.rollemup.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.box2d.BodyFactory;

public class SoySauce extends GameObject {

	Vector2 poly1[] = {
		new Vector2(60.5f / Level.PTM_RATIO, 185.3f / Level.PTM_RATIO),		// 1
		new Vector2(-73.2f / Level.PTM_RATIO, 186.0f / Level.PTM_RATIO),	// 2
		new Vector2(-54.1f / Level.PTM_RATIO, 118.1f / Level.PTM_RATIO),	// 3
		new Vector2(54.1f / Level.PTM_RATIO, 116.7f / Level.PTM_RATIO)		// 11
	};
	
	Vector2 poly2[] = {
		new Vector2(54.1f / Level.PTM_RATIO, 116.7f / Level.PTM_RATIO),		// 11
		new Vector2(-54.1f / Level.PTM_RATIO, 118.1f / Level.PTM_RATIO),	// 3
		new Vector2(-54.1f / Level.PTM_RATIO, 0.0f / Level.PTM_RATIO),		// 4
		new Vector2(54.8f / Level.PTM_RATIO, -1.4f / Level.PTM_RATIO)		// 10
	};
	
	Vector2 poly3[] = {
		new Vector2(54.8f / Level.PTM_RATIO, -1.4f / Level.PTM_RATIO),		// 10
		new Vector2(-54.1f / Level.PTM_RATIO, 0.0f / Level.PTM_RATIO),		// 4
		new Vector2(-71.8f / Level.PTM_RATIO, -88.4f / Level.PTM_RATIO),	// 5
		new Vector2(70.4f / Level.PTM_RATIO, -86.3f / Level.PTM_RATIO)		// 9
	};
	
	Vector2 poly4[] = {
		new Vector2(70.4f / Level.PTM_RATIO, -86.3f / Level.PTM_RATIO),		// 9
		new Vector2(-71.8f / Level.PTM_RATIO, -88.4f / Level.PTM_RATIO),	// 5
		new Vector2(-76.7f / Level.PTM_RATIO, -181.0f / Level.PTM_RATIO),	// 6
		new Vector2(79.5f / Level.PTM_RATIO, -174.7f / Level.PTM_RATIO)		// 8
	};

	public SoySauce(float x, float y, BodyType bodyType, World world) {
		super(world);
		gameType = GameObjectType.PLATFORM;
		objectRepresentation.setTexture(Assets.atlas.findRegion("soy"));
		
		size = (objectRepresentation.width * objectRepresentation.height) / Level.PTM_RATIO;
		body = BodyFactory.createPoly(x, y, 0.5f, 1.0f, bodyType, world, poly1, poly2, poly3, poly4);
		body.setUserData(this);
	}

	@Override
	public void update() {
		pos = body.getPosition();
		rotation = body.getAngle() * 180.0f/(float) Math.PI;
	}
}