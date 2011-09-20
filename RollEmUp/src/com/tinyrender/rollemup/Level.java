package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class Level extends PhysicsWorld {
	public OrthographicCamera cam;

	public Level() {
		cam = new OrthographicCamera(48.0f, 32.0f);
	}
	
	public abstract void show();
	public abstract void update(float deltaTime);
	public abstract void render(float deltaTime);
	
	// hook for gamescreen input
	public abstract void touchDown();
	public abstract void touchUp();
}
