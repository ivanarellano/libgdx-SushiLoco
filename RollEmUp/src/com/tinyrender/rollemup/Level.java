package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tinyrender.rollemup.box2d.PhysicsWorld;
import com.tinyrender.rollemup.gui.LevelGui;
import com.tinyrender.rollemup.object.Player;
import com.tinyrender.rollemup.screen.PlayScreen;

public abstract class Level extends PhysicsWorld implements LevelState {
	public int time = 5;
	public LevelGui gui;
	public Player player;
	
	OrthographicCamera levelCam = new OrthographicCamera(RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
	OrthographicCamera box2dCam = new OrthographicCamera(RollEmUp.TARGET_WIDTH/Level.PTM_RATIO, RollEmUp.TARGET_HEIGHT/Level.PTM_RATIO);
	
	public GameObject nextWorldGameObj;
	
	public Level() {
		gui = new LevelGui(this);
		
		levelCam.setToOrtho(false, RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);		
		box2dCam.setToOrtho(false, RollEmUp.TARGET_WIDTH/Level.PTM_RATIO, RollEmUp.TARGET_HEIGHT/Level.PTM_RATIO);		
	}
	
	public void update(int state, float deltaTime) {
		switch (state) {
			case PlayScreen.GAME_READY:
				ready(deltaTime);
			break;
			case PlayScreen.GAME_RUNNING:
				running(deltaTime);
			break;
			case PlayScreen.GAME_PAUSED:
				paused(deltaTime);
			break;
			case PlayScreen.GAME_LEVEL_END:
				levelEnd(deltaTime);
			break;
			case PlayScreen.GAME_OVER:
				gameOver(deltaTime);
			break;
		}
	}
	
	public void setCameraPosition(float x, float y) {
		levelCam.position.set(x * Level.PTM_RATIO, y * Level.PTM_RATIO, 0.0f);
		frustrumCulling.setPosition(x, y);
		
		if (Settings.debugEnabled)
			box2dCam.position.set(x, y, 0.0f);
	}
	
	public void zoomCamera(float zoom) {
		levelCam.zoom += zoom;
		box2dCam.zoom += zoom;
		frustrumCulling.scale(zoom);
	}
	
	public OrthographicCamera getBox2dCamera() {
		return box2dCam;
	}
	
	public OrthographicCamera getLevelCamera() {
		return levelCam;
	}
	
	public abstract void createWorld();
	
	public abstract void touchDown();
	public abstract void touchUp();
	public abstract boolean keyDown(int keyCode);
	public abstract boolean keyUp(int keyCode);
}
