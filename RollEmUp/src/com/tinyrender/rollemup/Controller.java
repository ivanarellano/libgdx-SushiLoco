package com.tinyrender.rollemup;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Shape;

public class Controller {
	public void jump() {
		
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
