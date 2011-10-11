package com.tinyrender.rollemup.screen;

import com.badlogic.gdx.Input.Keys;
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

	public PlayScreen(RollEmUp game) {
		super(game);
		level = new TestLevel();
		levelRenderer = new LevelRenderer(this);
		
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
		if (keyCode == Keys.BACK)
			game.screenStack.setPrevious();
		else if (keyCode == Keys.BACKSPACE)
			game.screenStack.setPrevious();
		else 
			level.keyDown(keyCode);
		return false;
	}
	
	@Override
	public boolean keyUp(int keyCode) {
		level.keyUp(keyCode);
		return false;
	}
}