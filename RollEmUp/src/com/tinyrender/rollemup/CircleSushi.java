package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class CircleSushi extends GameObject {

	public CircleSushi(PhysicsWorld world) {
		super(world);
	}
	
	public CircleSushi(float x, float y, float radius, float density, float angle, PhysicsWorld world) {
		super(world);
		createBody(x, y, radius, density, angle);
		pointsWorth = 2;
	}
	
	public void createBody(float x, float y, float radius, float density, float angle) {
		body = Utils.createCircle(x, y, radius, density, angle, false, BodyType.DynamicBody, world.b2world);
		body.setUserData(this);
	}

	@Override
	public void update() {

	}
	
	@Override
	public void enterContact(PhysicsObject collidesWith) {

	}	
	
	@Override
	public void leaveContact(PhysicsObject leftCollisionWith) {		
	}

	@Override
	public Type getType() {
		return Type.SUSHI;
	}
}
