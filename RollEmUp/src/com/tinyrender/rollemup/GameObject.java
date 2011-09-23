package com.tinyrender.rollemup;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class GameObject {
	public Body body;
	public Vector2 vel;
	public Vector2 pos;
	
	World b2world;
	
	public GameObject(World world) {
		b2world = world;
	}
	
	public void setBody(Body body) {
		this.body = body;
	}
}
