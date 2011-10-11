package com.tinyrender.rollemup.object;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.box2d.BodyFactory;
import com.tinyrender.rollemup.box2d.PhysicsWorld;

public class BoxSushi extends GameObject {
	public BoxSushi(PhysicsWorld world) {
		super(world);
	}
	
	public BoxSushi(float x, float y, float density, PhysicsWorld world) {
		super(world);
		objectRepresentation.setTexture(Assets.boxSushi);
		
		float hx = objectRepresentation.pixelWidth / 2.0f / Level.PTM_RATIO;
		float hy = objectRepresentation.pixelHeight / 2.0f / Level.PTM_RATIO;
		
		size = hx;
		gameType = GameType.SUSHI;
		createBody(x, y, hx, hy, density);
		body.setUserData(this);
	}
	
	public void createBody(float x, float y, float hx, float hy, float density) {
		body = BodyFactory.createBox(x, y, hx, hy, density, BodyType.DynamicBody, world.b2world);
	}
	
	@Override
	public void update() {
		pos = body.getPosition();
		rotation = body.getAngle() * 180.0f/(float) Math.PI;
	}
}
