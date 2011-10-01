package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.g2d.Sprite;
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
		
		sprite = new Sprite();
		sprite.set(Assets.boxSushi);
		hx = (sprite.getWidth()/2) / Level.PTM_RATIO;
		hy = (sprite.getHeight()/2) / Level.PTM_RATIO;
		
		createBody(x, y, density);
	}
	
	public void createBody(float x, float y, float density) {
		body = Utils.createBox(BodyType.DynamicBody, x, y, hx, hy, density, world.b2world);
		body.setUserData(this);
	}
	
	@Override
	public void update() {
		sprite.setPosition((body.getPosition().x-hx) * Level.PTM_RATIO, (body.getPosition().y-hy) * Level.PTM_RATIO);
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
