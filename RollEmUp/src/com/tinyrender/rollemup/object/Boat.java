package com.tinyrender.rollemup.object;


public class Boat {
	/*
	new Vector2(359.2f / Level.PTM_RATIO, 52.3f / Level.PTM_RATIO),		// 1
	new Vector2(192.3f / Level.PTM_RATIO, 55.2f / Level.PTM_RATIO),		// 2
	new Vector2(193.0f / Level.PTM_RATIO, 39.6f / Level.PTM_RATIO),		// 3
	new Vector2(-144.2f / Level.PTM_RATIO, 45.3f / Level.PTM_RATIO),	// 4
	new Vector2(-145.0f / Level.PTM_RATIO, 55.9f / Level.PTM_RATIO),	// 5
	new Vector2(-163.3f / Level.PTM_RATIO, 55.9f / Level.PTM_RATIO),	// 6
	new Vector2(-162.6f / Level.PTM_RATIO, 46.0f / Level.PTM_RATIO),	// 7
	new Vector2(-359.9f / Level.PTM_RATIO, 54.4f / Level.PTM_RATIO),	// 8
	new Vector2(-304.1f / Level.PTM_RATIO, -43.1f / Level.PTM_RATIO),	// 9
	new Vector2(-14.1f / Level.PTM_RATIO, -54.4f / Level.PTM_RATIO),	// 10
	new Vector2(314.0f / Level.PTM_RATIO, -53.0f / Level.PTM_RATIO),	// 11
	new Vector2(336.6f / Level.PTM_RATIO, 17.0f / Level.PTM_RATIO),		// 12
	new Vector2(359.2f / Level.PTM_RATIO, 18.4f / Level.PTM_RATIO)		// 13
	
	public Boat(float x, float y, BodyType bodyType, World world) {
		super(world);
		gameType = GameObjectType.PLATFORM;
		objectRepresentation.setTexture(Assets.soySauce);
		
		size = (objectRepresentation.width * objectRepresentation.height) / Level.PTM_RATIO;
		body = BodyFactory.createPoly(x, y, 0.5f, bodyType, world, poly1, poly2, poly3, poly4);
		body.setUserData(this);
	}

	@Override
	public void update() {
		pos = body.getPosition();
		rotation = body.getAngle() * 180.0f/(float) Math.PI;
	}
	*/
}
