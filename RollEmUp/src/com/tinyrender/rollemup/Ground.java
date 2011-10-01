package com.tinyrender.rollemup;

public class Ground extends GameObject {

	public Ground(PhysicsWorld world) {
		super(world);
	}
	
	public Ground(float x1, float y1, float x2, float y2, float friction, PhysicsWorld world) {
		super(world);
		createBody(x1, y1, x2, y2, friction);
		body.setUserData(this);
	}

	public void createBody(float x1, float y1, float x2, float y2, float friction) {
		body = Utils.createEdge(x1, y1, x2, y2, friction, world.b2world);
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
		return Type.PLATFORM;
	}
}
