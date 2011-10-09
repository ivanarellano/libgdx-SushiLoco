package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class PolygonObject {
	class Box {
		public float hx;
		public float hy;
	}
	
	public Body createBox(BodyType type, float x, float y, float hx, float hy, float density, World b2world) {
		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = type;
		Body body = b2world.createBody(bd);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(hx, hy);
		body.createFixture(shape, density);
		shape.dispose();
		
		return body;
	}
}
