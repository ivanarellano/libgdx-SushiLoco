package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class CircleSushi extends GameObject {
	public float radius;

	public CircleSushi(PhysicsWorld world) {
		super(world);
	}
	
	public CircleSushi(float x, float y, float density, float angle, PhysicsWorld world) {
		super(world);
		size = 2;
		
		sprite = new Sprite();
		sprite.set(Assets.circleSushi);
		radius = (sprite.getWidth()/2)/Level.PTM_RATIO;
		createBody(x, y, density, angle);
	}
	
	public void createBody(float x, float y, float density, float angle) {
		body = Utils.createCircle(x, y, radius, density, angle, false, BodyType.DynamicBody, world.b2world);
		body.setUserData(this);
	}

	@Override
	public void update() {
		sprite.setPosition((body.getPosition().x-radius)*Level.PTM_RATIO, (body.getPosition().y-radius)*Level.PTM_RATIO);
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
