package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.box2d.BodyFactory;
import com.tinyrender.rollemup.box2d.PhysicsObject;

public class Sensor extends PhysicsObject {	
	public Sensor(World world) {
		super(world);
	}
	
	public Sensor(float x, float y, float radius, BodyType bodyType, World world) {
		super(world);
		body = BodyFactory.createCircle(x, y, radius, 0.0f, 0.0f, 0.0f, true, bodyType, world);
		body.setUserData(this);
	}
	
	@Override public void update() {}
}