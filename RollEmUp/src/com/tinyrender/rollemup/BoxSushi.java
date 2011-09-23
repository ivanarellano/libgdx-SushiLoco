package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class BoxSushi extends GameObject {

	public BoxSushi(World world) {
		super(world);
	}
	
	public BoxSushi(float x, float y, float density, World world) {
		super(world);
		createBody(x, y, density);
		body.setUserData("boxsushi");
	}
	
	public void createBody(float x, float y, float density) {
		body = Utils.createBox(BodyType.DynamicBody, x, y, 1.0f, 0.25f, density, b2world);
	}

}
