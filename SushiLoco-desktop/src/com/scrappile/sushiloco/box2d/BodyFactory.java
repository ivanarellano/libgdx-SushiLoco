package com.scrappile.sushiloco.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;

public class BodyFactory {
	public static class BuildPattern {
		public final static short HORIZONTAL	= 0x001;
		public final static short VERTICAL		= 0x002;
	}

	public static Body createBody(float x, float y, BodyType bodyType, Object userData) {
		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = bodyType;
		Body body = PhysicsWorld.b2world.createBody(bd);
		
		if (bodyType == BodyType.StaticBody)
			body.setActive(true);
		else
			body.setActive(false);
		
		body.setUserData(userData);

		return body;
	}

	public static Body createPoly(Array<Vector2[]> vertices, float x, float y,
			float density, float friction, BodyType bodyType, Object userData) {
		Body body = createBody(x, y, bodyType, userData);

		PolygonShape shape = new PolygonShape();
		FixtureDef fd = new FixtureDef();

		fd.density = density;
		fd.friction = friction;
		fd.shape = shape;

		for (int i = 0; i < vertices.size; i++) {
			shape.set(vertices.get(i));
			body.createFixture(fd);
		}

		body.resetMassData();
		shape.dispose();

		return body;
	}

	public static Body createBox(float x, float y, float hx, float hy,
			float density, float friction, boolean isSensor, BodyType bodyType, Object userData) {
		Body body = createBody(x, y, bodyType, userData);

		PolygonShape shape = new PolygonShape();
		FixtureDef fd = new FixtureDef();

		fd.friction = friction;
		fd.density = density;
		fd.isSensor = isSensor;
		fd.shape = shape;

		shape.setAsBox(hx, hy);
		body.createFixture(fd);

		body.resetMassData();
		shape.dispose();

		return body;
	}

	public static Body createEdge(float x1, float y1, float x2, float y2,
			float friction) {
		BodyDef bd = new BodyDef();
		Body body = PhysicsWorld.b2world.createBody(bd);

		EdgeShape shape = new EdgeShape();
		FixtureDef fd = new FixtureDef();

		fd.friction = friction;
		fd.shape = shape;

		shape.set(x1, y1, x2, y2);
		body.createFixture(fd);

		body.resetMassData();
		shape.dispose();

		body.setActive(true); // crashes game if false

		return body;
	}

	public static Body createCircle(float x, float y, float radius,
			float density, float angle, float friction, boolean isSensor,
			BodyType bodyType, Object userData) {
		Body body = createBody(x, y, bodyType, userData);

		CircleShape shape = new CircleShape();
		FixtureDef fd = new FixtureDef();

		fd.density = density;
		fd.friction = friction;
		fd.isSensor = isSensor;
		fd.shape = shape;

		shape.setRadius(radius);
		body.createFixture(fd);

		body.resetMassData();
		shape.dispose();

		return body;
	}

}