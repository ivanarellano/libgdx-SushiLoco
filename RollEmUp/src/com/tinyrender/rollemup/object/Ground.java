package com.tinyrender.rollemup.object;

import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.box2d.BodyFactory;
import com.tinyrender.rollemup.box2d.PhysicsWorld;

public class Ground extends GameObject {

	public Ground(PhysicsWorld world) {
		super(world);
	}
	
	public Ground(float x1, float y1, float x2, float y2, float friction, PhysicsWorld world) {
		super(world);
		createBody(x1, y1, x2, y2, friction);
		gameType = GameType.PLATFORM;
		body.setUserData(this);
	}

	public void createBody(float x1, float y1, float x2, float y2, float friction) {
		body = BodyFactory.createEdge(x1, y1, x2, y2, friction, world.b2world);
	}
	
	@Override
	public void update() {		
	}
}
