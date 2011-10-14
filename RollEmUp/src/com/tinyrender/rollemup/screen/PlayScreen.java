package com.tinyrender.rollemup.screen;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;
import com.tinyrender.rollemup.GameScreen;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.LevelRenderer;
import com.tinyrender.rollemup.RollEmUp;
import com.tinyrender.rollemup.level.TestLevel;

public class PlayScreen extends GameScreen {
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	
	int state;
	public Level level;
	public LevelRenderer levelRenderer;
    public Vector3 touchPoint;

	public PlayScreen(RollEmUp game) {
		super(game);
		level = new TestLevel();
		levelRenderer = new LevelRenderer(this);
		touchPoint = new Vector3();
		
		gui = level.gui;
		
		state = GAME_RUNNING;
	}

	@Override
	public void dispose() {
		level.disposeWorld();
		levelRenderer.dispose();
	}

	@Override
	public void hide() {
		inputMultiplexer.removeProcessor(this);
	}

	@Override
	public void pause() {
	}
	
	public void update(float deltaTime) {
		switch (state) {
		case GAME_READY:
			level.ready(deltaTime);
			break;
		case GAME_RUNNING:
			level.running(deltaTime);
			break;
		case GAME_PAUSED:
			level.paused(deltaTime);
			break;
		case GAME_LEVEL_END:
			level.levelEnd(deltaTime);
			break;
		case GAME_OVER:
			level.gameOver(deltaTime);
			break;
		}
	}

	@Override
	public void render(float deltaTime) {
		update(deltaTime);
		levelRenderer.render(deltaTime);
		
		switch (state) {
		case GAME_READY:
			gui.ready(deltaTime);
			break;
		case GAME_RUNNING:
			gui.running(deltaTime);
			break;
		case GAME_PAUSED:
			gui.paused(deltaTime);
			break;
		case GAME_LEVEL_END:
			gui.levelEnd(deltaTime);
			break;
		case GAME_OVER:
			gui.gameOver(deltaTime);
			break;
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
		level.resumeWorld();
		levelRenderer.resume();
	}

	@Override
	public void show() {
		inputMultiplexer.addProcessor(this);
		level.create();
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointerId, int button) {
		gui.cam.unproject(touchPoint.set(x, y, 0.0f));
		
		if (level.gui.quit.justHit(touchPoint))
			game.screenStack.setPrevious();
		else
			level.touchDown();
		
		return false;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointerId, int button) {
		level.touchUp();
		return false;
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		
		switch (state) {
		case GAME_READY:
		case GAME_RUNNING:
			if (keyCode == Keys.MENU || keyCode == Keys.ESCAPE)
				state = GAME_PAUSED;
			else
				level.keyDown(keyCode);
			
			break;
		case GAME_PAUSED:
			if (keyCode == Keys.MENU || keyCode == Keys.ESCAPE)
				state = GAME_RUNNING;
			
			break;
		case GAME_LEVEL_END:
		case GAME_OVER:
			break;
		}

		return false;
	}
	
	@Override
	public boolean keyUp(int keyCode) {
		level.keyUp(keyCode);
		return false;
	}
}