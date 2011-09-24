package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class CircleSushi extends GameObject {

	public CircleSushi(PhysicsWorld world) {
		super(world);
	}
	
	public CircleSushi(float x, float y, float radius, float density, float angle, PhysicsWorld world) {
		super(world);
		createBody(x, y, radius, density, angle);
		body.setUserData("gameobject");
	}
	
	public void createBody(float x, float y, float radius, float density, float angle) {
		body = Utils.createCircle(BodyType.DynamicBody, x, y, radius, density, angle, world.b2world);
	}

}
