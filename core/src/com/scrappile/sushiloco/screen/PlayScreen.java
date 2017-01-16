package com.scrappile.sushiloco.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;
import com.scrappile.sushiloco.GameScreen;
import com.scrappile.sushiloco.Level;
import com.scrappile.sushiloco.LevelRenderer;
import com.scrappile.sushiloco.SushiLoco;
import com.scrappile.sushiloco.level.TestLevel;

import static com.scrappile.sushiloco.gui.LevelGui.QUIT;

public class PlayScreen extends GameScreen {

	public static final int GAME_READY = 0;
	public static final int GAME_RUNNING = 1;
	public static final int GAME_PAUSED = 2;
	public static final int GAME_LEVEL_END = 3;
	public static final int GAME_OVER = 4;

	private int state;
	private Level level;
	private LevelRenderer levelRenderer;
	private Vector3 touchPoint;

	public PlayScreen(SushiLoco game) {
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
		level.disposeWorld();
		levelRenderer.dispose();
	}

	@Override
	public void pause() {
		// No-op
	}

	@Override
	public void render(float deltaTime) {
		update(state, deltaTime);
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
				if (QUIT.intersectsWith(touchPoint)) {
					game.screenStack.setPrevious();
				}
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
				if (keyCode == Keys.MENU || keyCode == Keys.BACK || keyCode == Keys.BACKSPACE || keyCode == Keys.ESCAPE) {
					state = GAME_PAUSED;
				} else {
					level.keyDown(keyCode);
				}
				
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

	public void update(int state, float deltaTime) {
		level.update(state, deltaTime);
		level.gui.update(state, level.player.getXp().getTotalScore());
	}

	public Level getLevel() {
		return level;
	}
}