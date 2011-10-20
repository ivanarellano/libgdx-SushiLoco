package com.tinyrender.rollemup.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.box2d.BodyFactory;
import com.tinyrender.rollemup.box2d.JointFactory;

public class Boat extends GameObject {
	Vector2 boatbodyVec1[] = {
		new Vector2(359.2f / Level.PTM_RATIO, 52.3f / Level.PTM_RATIO),		// 1
		new Vector2(192.3f / Level.PTM_RATIO, 55.2f / Level.PTM_RATIO),		// 2
		new Vector2(193.0f / Level.PTM_RATIO, 39.6f / Level.PTM_RATIO),		// 3
		new Vector2(336.6f / Level.PTM_RATIO, 17.0f / Level.PTM_RATIO),		// 12
		new Vector2(359.2f / Level.PTM_RATIO, 18.4f / Level.PTM_RATIO)		// 13
	};
	
	Vector2 boatbodyVec2[] = {
		new Vector2(336.6f / Level.PTM_RATIO, 17.0f / Level.PTM_RATIO),		// 12
		new Vector2(193.0f / Level.PTM_RATIO, 39.6f / Level.PTM_RATIO),		// 3
		new Vector2(-144.2f / Level.PTM_RATIO, 45.3f / Level.PTM_RATIO),	// 4
		new Vector2(-14.1f / Level.PTM_RATIO, -54.4f / Level.PTM_RATIO),	// 10
		new Vector2(314.0f / Level.PTM_RATIO, -53.0f / Level.PTM_RATIO)		// 11
	};
	
	Vector2 boatbodyVec3[] = {
		new Vector2(-144.2f / Level.PTM_RATIO, 45.3f / Level.PTM_RATIO),	// 4
		new Vector2(-145.0f / Level.PTM_RATIO, 55.9f / Level.PTM_RATIO),	// 5
		new Vector2(-163.3f / Level.PTM_RATIO, 55.9f / Level.PTM_RATIO),	// 6
		new Vector2(-162.6f / Level.PTM_RATIO, 46.0f / Level.PTM_RATIO)		// 7
	};
	
	Vector2 boatbodyVec4[] = {
		new Vector2(-162.6f / Level.PTM_RATIO, 46.0f / Level.PTM_RATIO),	// 7
		new Vector2(-359.9f / Level.PTM_RATIO, 54.4f / Level.PTM_RATIO),	// 8
		new Vector2(-304.1f / Level.PTM_RATIO, -43.1f / Level.PTM_RATIO),	// 9
		new Vector2(-14.1f / Level.PTM_RATIO, -54.4f / Level.PTM_RATIO)		// 10
	};
	
	Vector2 boatfrontVec[] = {
		new Vector2(74.5f / Level.PTM_RATIO, -92.5f / Level.PTM_RATIO),
		new Vector2(-32.0f / Level.PTM_RATIO, 93.5f / Level.PTM_RATIO),
		new Vector2(-74.5f / Level.PTM_RATIO, 94.5f / Level.PTM_RATIO),
		new Vector2(33.0f / Level.PTM_RATIO, -93.5f / Level.PTM_RATIO)
	};
	
	Vector2 boatflagVec1[] = {
		new Vector2(-21.0f / Level.PTM_RATIO, 144.0f / Level.PTM_RATIO),
		new Vector2(-31.5f / Level.PTM_RATIO, 144.5f / Level.PTM_RATIO),
		new Vector2(-31.5f / Level.PTM_RATIO, -144.0f / Level.PTM_RATIO),
		new Vector2(-21.0f / Level.PTM_RATIO, -143.5f / Level.PTM_RATIO)
	};
	
	Vector2 boatflagVec2[] = {
		new Vector2(30.5f / Level.PTM_RATIO, 121.0f / Level.PTM_RATIO),
		new Vector2(-19.5f / Level.PTM_RATIO, 121.5f / Level.PTM_RATIO),
		new Vector2(-19.5f / Level.PTM_RATIO, 20.0f / Level.PTM_RATIO),
		new Vector2(27.5f / Level.PTM_RATIO, 20.5f / Level.PTM_RATIO)
	};

	Vector2 boatnetVec1[] = {
		new Vector2(112.4f / Level.PTM_RATIO, 189.2f / Level.PTM_RATIO),
		new Vector2(105.4f / Level.PTM_RATIO, 188.4f / Level.PTM_RATIO),
		new Vector2(103.9f / Level.PTM_RATIO, -142.5f / Level.PTM_RATIO),
		new Vector2(114.6f / Level.PTM_RATIO, -141.1f / Level.PTM_RATIO)
	};
	
	Vector2 boatnetVec2[] = {
		new Vector2(11.3f / Level.PTM_RATIO, -30.8f / Level.PTM_RATIO),		// 1
		new Vector2(-33.9f / Level.PTM_RATIO, -83.8f / Level.PTM_RATIO),	// 2
		new Vector2(-75.7f / Level.PTM_RATIO, -95.1f / Level.PTM_RATIO),	// 3
		new Vector2(-6.4f / Level.PTM_RATIO, -124.8f / Level.PTM_RATIO)		// 11
	};
	
	Vector2 boatnetVec3[] = {
		new Vector2(-75.7f / Level.PTM_RATIO, -95.1f / Level.PTM_RATIO),	// 3
		new Vector2(-112.4f / Level.PTM_RATIO, -86.6f / Level.PTM_RATIO),	// 4
		new Vector2(-99.7f / Level.PTM_RATIO, -137.5f / Level.PTM_RATIO),	// 5
		new Vector2(-72.8f / Level.PTM_RATIO, -186.3f / Level.PTM_RATIO)	// 7
	};
	
	Vector2 boatnetVec4[] = {
		new Vector2(-6.4f / Level.PTM_RATIO, -124.8f / Level.PTM_RATIO),	// 11
		new Vector2(-72.8f / Level.PTM_RATIO, -186.3f / Level.PTM_RATIO),	// 7
		new Vector2(-41.7f / Level.PTM_RATIO, -183.5f / Level.PTM_RATIO),	// 8
		new Vector2(15.6f / Level.PTM_RATIO, -163.7f / Level.PTM_RATIO),	// 9
		new Vector2(21.2f / Level.PTM_RATIO, -153.8f / Level.PTM_RATIO)		// 10
	};
	
	GameObject boatFront;
	GameObject boatFlag;
	GameObject boatNet;
	GameObject boatBackBar;
	
	public Boat(float x, float y, BodyType bodyType, World world) {
		super(world);
		gameType = GameObjectType.PLATFORM;
		objectRepresentation.setTexture(Assets.atlas.findRegion("boatbody"));
		size = (objectRepresentation.width * objectRepresentation.height) / Level.PTM_RATIO;
		
		body = BodyFactory.createPoly(x, y, 0.65f, 1.0f, bodyType, world, boatbodyVec1, boatbodyVec2, boatbodyVec3, boatbodyVec4);
		body.setUserData(this);
		
		boatFront = new GameObject(world);
		boatFlag = new GameObject(world);
		boatNet = new GameObject(world);
		boatBackBar = new GameObject(world);
		
		
		boatFront.body = BodyFactory.createPoly(x-(381.0f/Level.PTM_RATIO), y+(48.0f/Level.PTM_RATIO),
												0.25f, 1.0f, bodyType, world, boatfrontVec);
		boatFront.gameType = GameObjectType.PLATFORM;
		boatFront.objectRepresentation.setTexture(Assets.atlas.findRegion("boatfront"));
		boatFront.size = (boatFront.objectRepresentation.width * boatFront.objectRepresentation.height) / Level.PTM_RATIO;
		boatFront.body.setUserData(boatFront);
		subObjects.add(boatFront.objectRepresentation);
		JointFactory.weld(body, boatFront.body, body.getWorldCenter(), world);
		
		
		boatFlag.body = BodyFactory.createPoly(x+(237.0f/Level.PTM_RATIO), y+(185.0f/Level.PTM_RATIO),
												0.25f, 1.0f, bodyType, world, boatflagVec1, boatflagVec2);
		boatFlag.gameType = GameObjectType.PLATFORM;
		boatFlag.objectRepresentation.setTexture(Assets.atlas.findRegion("boatflag"));
		boatFlag.size = (boatFlag.objectRepresentation.width * boatFlag.objectRepresentation.height) / Level.PTM_RATIO;
		boatFlag.body.setUserData(boatFlag);
		subObjects.add(boatFlag.objectRepresentation);
		JointFactory.weld(body, boatFlag.body, body.getWorldCenter(), world);
		
		
		boatNet.body = BodyFactory.createPoly(x-(277.0f/Level.PTM_RATIO), y+(185.0f/Level.PTM_RATIO),
												0.25f, 1.0f, bodyType, world, boatnetVec1, boatnetVec2, boatnetVec3, boatnetVec4);
		boatNet.gameType = GameObjectType.PLATFORM;
		boatNet.objectRepresentation.setTexture(Assets.atlas.findRegion("boatnet"));
		boatNet.size = (boatNet.objectRepresentation.width * boatNet.objectRepresentation.height) / Level.PTM_RATIO;
		boatNet.body.setUserData(boatFlag);
		subObjects.add(boatNet.objectRepresentation);
		JointFactory.weld(body, boatNet.body, body.getWorldCenter(), world);
		
		
		boatBackBar.body = BodyFactory.createBox(x+(304.0f/Level.PTM_RATIO), y+(86.0f/Level.PTM_RATIO),
													8.0f/Level.PTM_RATIO, 43.0f/Level.PTM_RATIO, 0.15f, bodyType, world);
		boatBackBar.gameType = GameObjectType.PLATFORM;
		boatBackBar.objectRepresentation.setTexture(Assets.atlas.findRegion("boatbackbar"));
		boatBackBar.size = (boatBackBar.objectRepresentation.width * boatBackBar.objectRepresentation.height) / Level.PTM_RATIO;
		boatBackBar.body.setUserData(boatBackBar);
		subObjects.add(boatBackBar.objectRepresentation);
		JointFactory.weld(body, boatBackBar.body, body.getWorldCenter(), world);
	}

	@Override
	public void update() {
		pos = body.getPosition();
		rotation = body.getAngle() * 180.0f/(float) Math.PI;
		
		boatFront.pos = boatFront.body.getPosition();
		boatFront.rotation = boatFront.body.getAngle() * 180.0f/(float) Math.PI;
		
		boatFlag.pos = boatFlag.body.getPosition();
		boatFlag.rotation = boatFlag.body.getAngle() * 180.0f/(float) Math.PI;
		
		boatNet.pos = boatNet.body.getPosition();
		boatNet.rotation = boatNet.body.getAngle() * 180.0f/(float) Math.PI;
		
		boatBackBar.pos = boatBackBar.body.getPosition();
		boatBackBar.rotation = boatBackBar.body.getAngle() * 180.0f/(float) Math.PI;
	}
}
