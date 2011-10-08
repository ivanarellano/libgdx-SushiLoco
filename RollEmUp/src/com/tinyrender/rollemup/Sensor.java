package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Sensor extends PhysicsObject {
	public enum Type {
		PLAYER, SUSHI, PLATFORM;
	}
	
	Type type;
	
	public Sensor(PhysicsWorld world) {
		super(world);
	}
	
	public Sensor(float x, float y, float radius, BodyType bodyType, PhysicsWorld world) {
		super(world);
		createBody(x, y, radius, bodyType);
	}

	public void createBody(float x, float y, float radius, BodyType bodyType) {
		body = Utils.createCircle(x, y, radius, 0.0f, 0.0f, true, bodyType, world.b2world);
		body.setUserData(this);
	}
	
	@Override
	public void update() {		
	}
}
