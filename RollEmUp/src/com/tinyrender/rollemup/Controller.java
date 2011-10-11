package com.tinyrender.rollemup;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Shape;
import com.tinyrender.rollemup.box2d.PhysicsObject;

public class Controller {
	public void jump(GameObject object, float velocity) {
		object.body.applyLinearImpulse(0, velocity * object.body.getMass(), object.pos.x, object.pos.y);
	}
	
	public void scaleCircle(PhysicsObject object, float scale, Vector2 posOffset) {
		Fixture fixture = object.body.getFixtureList().get(0);
		Shape.Type shapeType = fixture.getType();
		
		if (shapeType == Shape.Type.Circle) {
			CircleShape shape = (CircleShape) fixture.getShape();
			float radius = shape.getRadius();
			
			radius *= scale;
			shape.setPosition(posOffset);
			shape.setRadius(radius);
		}
	}
}
