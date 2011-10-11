package com.tinyrender.rollemup.object;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.box2d.BodyFactory;
import com.tinyrender.rollemup.box2d.PhysicsWorld;

public class Platform extends GameObject {

	public Platform(PhysicsWorld world) {
		super(world);
	}
	
	public Platform(float x, float y, float hx, float hy, float density, PhysicsWorld world) {
		super(world);
		gameType = GameType.PLATFORM;
		createBody(x, y, hx, hy, density);
		body.setUserData(this);
	}
	
	public void createBody(float x, float y, float hx, float hy, float density) {
		body = BodyFactory.createBox(x, y, hx, hy, density, BodyType.StaticBody, world.b2world);
	}
	
	@Override
	public void update() {		
	}
}
