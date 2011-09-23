package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Utils {
	public static Body createBox(BodyType type, float x, float y, float hx, float hy, float density, World b2world) {
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

	public static Body createEdge(float x1, float y1, float x2, float y2, float friction, World b2world) {
        BodyDef bd = new BodyDef();
        Body body = b2world.createBody(bd);

        EdgeShape shape = new EdgeShape();
        shape.set(x1, y1, x2, y2);
        
        FixtureDef fd = new FixtureDef();
        fd.friction = friction;
        fd.shape = shape;
        body.createFixture(fd);
        
        shape.dispose();
        
		return body;
	}
	
	public static Body createCircle(BodyType type, float radius, float density, World b2world) {
		BodyDef bd = new BodyDef();
		bd.type = type;
		Body body = b2world.createBody(bd);
 
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		body.createFixture(shape, density);
		shape.dispose();
 
		return body;
	}
}
