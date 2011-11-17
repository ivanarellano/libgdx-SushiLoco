package com.tinyrender.rollemup.object;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.box2d.PhysicsObject;

class PlayerSensor extends Sensor {
	public int numContacts;
	
	PlayerSensor(float x, float y, float radius, BodyType bodyType, World world) {
		super(x, y, radius, bodyType, world);
		
		contactResolver = new ContactResolver() {
			@Override
			public void enterContact(PhysicsObject collidesWith) {
				numContacts++;
			}

			@Override
			public void leaveContact(PhysicsObject leftCollisionWith) {
				numContacts--;
			}
		};
	}
}