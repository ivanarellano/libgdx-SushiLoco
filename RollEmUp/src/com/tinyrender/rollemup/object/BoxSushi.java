package com.tinyrender.rollemup.object;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.box2d.BodyFactory;

public class BoxSushi extends GameObject {
	public BoxSushi(World world) {
		super(world);
	}
	
	public BoxSushi(float x, float y, float density, World world) {
		super(world);
		gameType = GameObjectType.ROLLABLE;
		objectRepresentation.setTexture(Assets.boxSushi);
		
		float hx = objectRepresentation.width / 2.0f / Level.PTM_RATIO;
		float hy = objectRepresentation.height / 2.0f / Level.PTM_RATIO;
		
		size = hx;
		body = BodyFactory.createBox(x, y, hx, hy, density, BodyType.DynamicBody, world);
		body.setUserData(this);
	}
	
	@Override
	public void update() {
		pos = body.getPosition();
		rotation = body.getAngle() * 180.0f/(float) Math.PI;
	}
}
