package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Sensor extends PhysicsObject {	
	public Sensor(PhysicsWorld world) {
		super(world);
	}
	
	public Sensor(float x, float y, float radius, BodyType bodyType, PhysicsWorld world) {
		super(world);
		body = BodyFactory.createCircle(x, y, radius, 0.0f, 0.0f, 0.0f, true, bodyType, world.b2world);
		body.setUserData(this);
	}
	
	@Override public void update() {}
}