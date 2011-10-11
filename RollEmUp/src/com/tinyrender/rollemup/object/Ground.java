package com.tinyrender.rollemup.object;

import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.box2d.BodyFactory;

public class Ground extends GameObject {

	public Ground(World world) {
		super(world);
	}
	
	public Ground(float x1, float y1, float x2, float y2, float friction, World world) {
		super(world);
		createBody(x1, y1, x2, y2, friction);
		gameType = GameType.PLATFORM;
		body.setUserData(this);
	}

	public void createBody(float x1, float y1, float x2, float y2, float friction) {
		body = BodyFactory.createEdge(x1, y1, x2, y2, friction, world);
	}
	
	@Override
	public void update() {		
	}
}
