package com.tinyrender.rollemup.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.LevelRenderer;
import com.tinyrender.rollemup.RollEmUp;
import com.tinyrender.rollemup.level.TestLevel;

public class GameScreen extends InputAdapter implements Screen {
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	
	int state;
	
	RollEmUp game;
	Level level;
	LevelRenderer renderer;

	public GameScreen(RollEmUp g) {
		game = g;
		level = new TestLevel();
		renderer = new LevelRenderer(this);
		
		Gdx.input.setInputProcessor(this);
		state = GAME_READY;
	}

	@Override
	public void dispose() {
		level.disposeWorld();
		renderer.dispose();
	}

	@Override
	public void hide() {
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
		level.show();
		renderer.show();
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
	
	public void setLevel(Level level) {
		this.level = level;
	}

	public Level getLevel() {
		return level;
	}
}