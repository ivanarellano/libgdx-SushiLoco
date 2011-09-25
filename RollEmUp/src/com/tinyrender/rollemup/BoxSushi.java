package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BoxSushi extends GameObject {

	public BoxSushi(PhysicsWorld world) {
		super(world);
	}
	
	public BoxSushi(float x, float y, float density, PhysicsWorld world) {
		super(world);
		createBody(x, y, density);
	}
	
	public void createBody(float x, float y, float density) {
		body = Utils.createBox(BodyType.DynamicBody, x, y, 1.0f, 0.25f, density, world.b2world);
		body.setUserData(this);
	}
	
	@Override
	public void update() {

	}

	@Override
	public void enterContact(GameObject collidesWith) {
	}	

	@Override
	public void leaveContact(GameObject leftCollisionWith) {		
	}

	@Override
	public Type getType() {
		return Type.SUSHI;
	}
}
