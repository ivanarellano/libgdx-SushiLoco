package com.tinyrender.rollemup.object;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.box2d.BodyFactory;

public class Platform extends GameObject {

	public Platform(World world) {
		super(world);
	}
	
	public Platform(float x, float y, float hx, float hy, float density, World world) {
		super(world);
		gameType = GameObjectType.PLATFORM;
		createBody(x, y, hx, hy, density);
		body.setUserData(this);
	}
	
	public void createBody(float x, float y, float hx, float hy, float density) {
		body = BodyFactory.createBox(x, y, hx, hy, density, BodyType.StaticBody, world);
	}
	
	@Override
	public void update() {		
	}
}
