package com.tinyrender.rollemup.screen;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;
import com.tinyrender.rollemup.GameScreen;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.LevelRenderer;
import com.tinyrender.rollemup.RollEmUp;
import com.tinyrender.rollemup.level.TestLevel;

public class PlayScreen extends GameScreen {
	public static final int GAME_READY = 0;
	public static final int GAME_RUNNING = 1;
	public static final int GAME_PAUSED = 2;
	public static final int GAME_LEVEL_END = 3;
	public static final int GAME_OVER = 4;
	
	int state;
	public Level level;
	public LevelRenderer levelRenderer;
    public Vector3 touchPoint;

	public PlayScreen(RollEmUp game) {
		super(game);
		level = new TestLevel();
		levelRenderer = new LevelRenderer(this);
		touchPoint = new Vector3();
		
		state = GAME_RUNNING;
	}

	@Override
	public void dispose() {
		level.disposeWorld();
		levelRenderer.dispose();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}
	
	public void update(int state, float deltaTime) {
		level.update(state, deltaTime);
		level.gui.update(state, level.player.xp.getTotalScore());
	}

	@Override
	public void render(float deltaTime) {
		this.update(state, deltaTime);
		
		levelRenderer.render(deltaTime);
		level.gui.render(deltaTime);
	}

	@Override
	public void resize(int width, int height) { }

	@Override
	public void resume() {
		level.resumeWorld();
		levelRenderer.resume();
	}

	@Override
	public void show() {
		level.createWorld();
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointerId, int button) {		
		level.gui.cam.unproject(touchPoint.set(x, y, 0.0f));
		
		switch (state) {
			case GAME_RUNNING:
				level.touchDown();
				break;
			case GAME_PAUSED:
				if (level.gui.quit.justHit(touchPoint))
					game.screenStack.setPrevious();
			case GAME_READY:
			case GAME_LEVEL_END:
			case GAME_OVER:
				break;
		}
		
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
			case GAME_RUNNING:
				if (keyCode == Keys.MENU || keyCode == Keys.BACK ||
					keyCode == Keys.BACKSPACE || keyCode == Keys.ESCAPE)
					state = GAME_PAUSED;
				else
					level.keyDown(keyCode);
				
				break;
			case GAME_PAUSED:
				if (keyCode == Keys.MENU || keyCode == Keys.BACK ||
					keyCode == Keys.BACKSPACE || keyCode == Keys.ESCAPE)
					state = GAME_RUNNING;
				
				break;
			case GAME_READY:
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