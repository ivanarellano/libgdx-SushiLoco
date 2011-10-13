package com.tinyrender.rollemup.object;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.box2d.BodyFactory;

public class CircleSushi extends GameObject {
	public CircleSushi(World world) {
		super(world);
	}
	
	public CircleSushi(float x, float y, float density, float angle, World world) {
		super(world);
		gameType = GameType.ROLLABLE;
		objectRepresentation.setTexture(Assets.circleSushi);
		
		float radius = objectRepresentation.pixelWidth / 2.0f / Level.PTM_RATIO;
		
		size = radius;
		body = BodyFactory.createCircle(x, y, radius, density, angle, 0.0f, false, BodyType.DynamicBody, world);
		body.setUserData(this);
	}

	@Override
	public void update() {
		pos = body.getPosition();
		rotation = body.getAngle()*180.0f/(float) Math.PI;
	}
}
