package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.tinyrender.rollemup.box2d.PhysicsWorld;
import com.tinyrender.rollemup.gui.LevelGui;
import com.tinyrender.rollemup.object.Player;

public abstract class Level extends PhysicsWorld implements LevelStateUpdater {
	public int time = 5;
	public float zoom = 1.0f;
	
	public Player player;
	public Array<GameObject> objects = new Array<GameObject>(150);
	
	public LevelGui gui = new LevelGui();
	
	public OrthographicCamera cam = new OrthographicCamera((float) RollEmUp.TARGET_WIDTH,
														   (float) RollEmUp.TARGET_HEIGHT);
	public OrthographicCamera box2dcam = new OrthographicCamera((float) RollEmUp.TARGET_WIDTH/Level.PTM_RATIO,
																(float) RollEmUp.TARGET_HEIGHT/Level.PTM_RATIO);

	public Level() {
		cam.setToOrtho(false, (float) RollEmUp.TARGET_WIDTH, (float) RollEmUp.TARGET_HEIGHT);		
		box2dcam.setToOrtho(false, (float) RollEmUp.TARGET_WIDTH/Level.PTM_RATIO, (float) RollEmUp.TARGET_HEIGHT/Level.PTM_RATIO);		
	}
	
	public abstract void create();
	
	// hook for gamescreen input
	public abstract void touchDown();
	public abstract void touchUp();
	public abstract boolean keyDown(int keyCode);
	public abstract boolean keyUp(int keyCode);
}
