package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Sensor extends GameObject {

	public Sensor(PhysicsWorld world) {
		super(world);
	}
	
	public Sensor(float x, float y, float radius, BodyType bodyType, PhysicsWorld world) {
		super(world);
		createBody(x, y, radius, bodyType);
	}

	public void createBody(float x, float y, float radius, BodyType bodyType) {
		body = Utils.createCircle(bodyType, x, y, radius, 0.0f, 0.0f, true, world.b2world);
		body.setUserData(this);
	}		
	
	@Override
	public void enterContact(GameObject collidesWith) {
	}

	@Override
	public void leaveContact() {		
	}
	
	@Override
	public Type objectType() {
		return Type.SENSOR;
	}
}
