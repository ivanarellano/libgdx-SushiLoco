package com.tinyrender.rollemup.object;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.box2d.PhysicsObject;

class PlayerSensor extends Sensor {
	public int numContacts;
	public Player player;
	
	PlayerSensor(float x, float y, float radius, final Player player, BodyType bodyType, World world) {
		super(x, y, radius, bodyType, world);
		
		this.player = player;
		
		contactResolver = new ContactResolver() {
			@Override
			public void enterContact(PhysicsObject collidesWith) {
				GameObject otherObj = (GameObject) collidesWith.body.getUserData();
								
				if (!player.isRollable(otherObj))
					numContacts++;
			}

			@Override
			public void leaveContact(PhysicsObject leftCollisionWith) {
				numContacts--;
			}
		};
	}
}