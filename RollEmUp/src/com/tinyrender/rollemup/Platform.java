package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Platform extends GameObject {

	public Platform(PhysicsWorld world) {
		super(world);
	}
	
	public Platform(float x, float y, float hx, float hy, float density, PhysicsWorld world) {
		super(world);
		type = Type.PLATFORM;
		createBody(x, y, hx, hy, density);
		body.setUserData(this);
	}
	
	public void createBody(float x, float y, float hx, float hy, float density) {
		body = Utils.createBox(BodyType.StaticBody, x, y, hx, hy, density, world.b2world);
	}
	
	@Override
	public void update() {		
	}
	
	@Override
	public void draw() {
		
	}
}
