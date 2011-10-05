package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class CircleSushi extends GameObject {
	public float radius;

	public CircleSushi(PhysicsWorld world) {
		super(world);
	}
	
	public CircleSushi(float x, float y, float density, float angle, PhysicsWorld world) {
		super(world);
		radius = (Assets.circleSushi.getWidth()/2.0f)/Level.PTM_RATIO;
		size = radius;
		createBody(x, y, density, angle);
	}
	
	public void createBody(float x, float y, float density, float angle) {
		body = Utils.createCircle(x, y, radius, density, angle, false, BodyType.DynamicBody, world.b2world);
		body.setUserData(this);
	}

	@Override
	public void update() {
		pos = body.getPosition();
	}
	
	@Override
	public void draw() {
		Assets.batch.draw(Assets.circleSushi,
				(pos.x-radius) * Level.PTM_RATIO, (pos.y-radius) * Level.PTM_RATIO,
				radius * Level.PTM_RATIO, radius * Level.PTM_RATIO,
				(radius+radius) * Level.PTM_RATIO, (radius+radius) * Level.PTM_RATIO,
				1.0f, 1.0f,
				body.getAngle()*180.0f/(float) Math.PI);
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
