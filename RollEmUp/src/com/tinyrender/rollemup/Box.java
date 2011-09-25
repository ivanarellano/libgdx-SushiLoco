package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Box extends GameObject {

	public Box(PhysicsWorld world) {
		super(world);
	}
	
	public Box(float x, float y, float hx, float hy, float density, PhysicsWorld world) {
		super(world);
		createBody(x, y, hx, hy, density);
	}
	
	public void createBody(float x, float y, float hx, float hy, float density) {
		body = Utils.createBox(BodyType.StaticBody, x, y, hx, hy, density, world.b2world);
		body.setUserData(this);
	}

	@Override
	public void enterContact(GameObject collidesWith) {		
	}

	@Override
	public void leaveContact() {		
	}
}
