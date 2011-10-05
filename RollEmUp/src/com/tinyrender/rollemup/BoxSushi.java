package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class BoxSushi extends GameObject {
	public float hx;
	public float hy;
	
	public BoxSushi(PhysicsWorld world) {
		super(world);
	}
	
	public BoxSushi(float x, float y, float density, PhysicsWorld world) {
		super(world);
		size = 4;
		
		hx = (Assets.boxSushi.getRegionWidth()/2) / Level.PTM_RATIO;
		hy = (Assets.boxSushi.getRegionHeight()/2) / Level.PTM_RATIO;
		
		createBody(x, y, density);
	}
	
	public void createBody(float x, float y, float density) {
		body = Utils.createBox(BodyType.DynamicBody, x, y, hx, hy, density, world.b2world);
		body.setUserData(this);
	}
	
	@Override
	public void update() {
		pos.x = body.getPosition().x;
		pos.y = body.getPosition().y;		
	}
	
	@Override
	public void draw() {
		Assets.batch.draw(Assets.boxSushi,
							pos.x * Level.PTM_RATIO, pos.y * Level.PTM_RATIO,
							0.0f, 0.0f,
							Assets.boxSushi.getRegionWidth(), Assets.boxSushi.getRegionHeight(),
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
