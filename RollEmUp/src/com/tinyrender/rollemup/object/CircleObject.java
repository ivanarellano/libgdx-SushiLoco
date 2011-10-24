package com.tinyrender.rollemup.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.box2d.BodyFactory;

public class CircleObject extends GameObject {
	public CircleObject(World world) {
		super(world);
	}
	
	public CircleObject(float x, float y, float density, float angle, GameObjectType gameObjectType, TextureRegion texture, World world) {
		super(world);
		gameType = gameObjectType;
		objectRepresentation.setTexture(texture);
		
		float radius = objectRepresentation.width / 2.0f / Level.PTM_RATIO;
		
		body = BodyFactory.createCircle(x, y, radius, density, angle, 1.0f, false, BodyType.DynamicBody, world);
		body.setUserData(this);
	}

	@Override
	public void update() {
		pos = body.getPosition();
		rotation = body.getAngle() * 180.0f/(float) Math.PI;
	}
}
