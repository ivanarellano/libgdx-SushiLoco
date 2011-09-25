package com.tinyrender.rollemup;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class GameObject {
	public enum Type {
		PLAYER, SUSHI, PLATFORM, SENSOR;
	}
	
	public Body body;
	public Vector2 vel;
	public Vector2 pos;
	public int numContacts;
	
	public PhysicsWorld world;
	
	public GameObject(PhysicsWorld world) {
		this.world = world;
	}
	
	public void setBody(Body body) {
		this.body = body;
	}
	
	public abstract void enterContact(GameObject collidesWith);
	public abstract void leaveContact();
	public abstract Type objectType();
}
