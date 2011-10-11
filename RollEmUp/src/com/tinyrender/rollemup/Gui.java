package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Gui implements LevelStateUpdater {
    public OrthographicCamera cam;
    
    public Gui() {
		cam = new OrthographicCamera(RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
		cam.setToOrtho(false, RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
		cam.position.set(RollEmUp.SCREEN_HALF_WIDTH, RollEmUp.SCREEN_HALF_HEIGHT, 0);
    }
    
	@Override
	public void ready(float deltaTime) {		
	}

	@Override
	public void running(float deltaTime) {		
	}

	@Override
	public void paused(float deltaTime) {		
	}

	@Override
	public void levelEnd(float deltaTime) {		
	}

	@Override
	public void gameOver(float deltaTime) {		
	}

}
