package com.tinyrender.rollemup;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class Level extends PhysicsWorld {
	public final static int PTM_RATIO = 64;
	public OrthographicCamera cam;
	public OrthographicCamera box2dcam;
	
	public ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public Level() {
		cam = new OrthographicCamera((float)RollEmUp.TARGET_WIDTH, (float)RollEmUp.TARGET_HEIGHT);
		cam.setToOrtho(false, (float)RollEmUp.TARGET_WIDTH, (float)RollEmUp.TARGET_HEIGHT);
		
		box2dcam = new OrthographicCamera((float)RollEmUp.TARGET_WIDTH/Level.PTM_RATIO, (float)RollEmUp.TARGET_HEIGHT/Level.PTM_RATIO);
		box2dcam.setToOrtho(false, (float)RollEmUp.TARGET_WIDTH/Level.PTM_RATIO, (float)RollEmUp.TARGET_HEIGHT/Level.PTM_RATIO);
	}
	
	public abstract void create();
	public abstract void update(float deltaTime);
	
	// hook for gamescreen input
	public abstract void touchDown();
	public abstract void touchUp();
}
