package com.tinyrender.rollemup.object;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.BodyFactory;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.PhysicsWorld;

public class CircleSushi extends GameObject {
	public float radius;

	public CircleSushi(PhysicsWorld world) {
		super(world);
	}
	
	public CircleSushi(float x, float y, float density, float angle, PhysicsWorld world) {
		super(world);
		radius = (Assets.circleSushi.getWidth()/2.0f)/Level.PTM_RATIO;
		size = radius;
		gameType = GameType.SUSHI;
		
		body = BodyFactory.createCircle(x, y, radius, density, angle, 0.0f, false, BodyType.DynamicBody, world.b2world);
		body.setUserData(this);
	}

	@Override
	public void update() {
		pos = body.getPosition();
	}
	
	@Override
	public void draw() {
		Assets.batch.draw(Assets.circleSushi,
				(pos.x-radius) * Level.PTM_RATIO, (pos.y-radius) * Level.PTM_RATIO,
				radius * Level.PTM_RATIO, radius * Level.PTM_RATIO,
				(radius+radius) * Level.PTM_RATIO, (radius+radius) * Level.PTM_RATIO,
				1.0f, 1.0f,
				body.getAngle()*180.0f/(float) Math.PI);
	}
}
