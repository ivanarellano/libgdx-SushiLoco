package com.tinyrender.rollemup;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BodyFactory {
	public static Body createBody(float x, float y, BodyType bodyType, World b2world) {
		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = bodyType;
		Body body = b2world.createBody(bd);
		
		return body;
	}
	
	public static Body createBox(float x, float y, float hx, float hy, float density, BodyType bodyType, World b2world) {
		Body body = createBody(x, y, bodyType, b2world);
		
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
	
	public static Body createCircle(float x, float y, float radius, float density, float angle, float friction, boolean isSensor, BodyType bodyType, World b2world) {
		Body body = createBody(x, y, bodyType, b2world);
 
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		
		FixtureDef fd = new FixtureDef();
		fd.density = density;
		fd.friction = friction;
		fd.isSensor = isSensor;
		fd.shape = shape;
		body.createFixture(fd);
		shape.dispose();
 
		return body;
	}
}
