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
	
	public RollEmUp game;
	public Level level;
	public LevelRenderer renderer;

	public PlayScreen(RollEmUp g) {
		game = g;
		level = new TestLevel();
		renderer = new LevelRenderer(this);
		state = GAME_READY;
	}

	@Override
	public void dispose() {
		level.disposeWorld();
		renderer.dispose();
	}

	@Override
	public void hide() {
		inputMultiplexer.removeProcessor(this);
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float deltaTime) {
		level.update(deltaTime); 		// updates world physics and level logic
		renderer.render(deltaTime); 	// draws world and level
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
		level.resumeWorld();
		renderer.resume();
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