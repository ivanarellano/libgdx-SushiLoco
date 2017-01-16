package com.scrappile.sushiloco.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.GameScreen;
import com.scrappile.sushiloco.Settings;
import com.scrappile.sushiloco.SushiLoco;
import com.scrappile.sushiloco.gui.MainMenuGui;

public class MainMenuScreen extends GameScreen {

	private Music music;
	private Vector3 touchPoint;
	private MainMenuGui gui;
	
	public MainMenuScreen(SushiLoco game) {
		super(game);

		touchPoint = new Vector3();
		gui = new MainMenuGui();
		
		if (Assets.manager.isLoaded("music.mp3")) {
			music = Assets.getMusic("music.mp3");
    		music.setLooping(true);
    		music.setVolume(0.25f);
		}
	}

	@Override
	public void pause() {
		Settings.write();
	}

	@Override
	public void render(float deltaTime) {		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gui.render();
	}

	@Override
	public void show() {		
		if (Settings.soundEnabled) { // FIXME: CHANGE TO "musicEnabled" WHEN READY
			music.play();
		}
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Keys.BACK || keyCode == Keys.BACKSPACE)
			game.screenStack.setPrevious();
		return false;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointerId, int button) {
		gui.cam.unproject(touchPoint.set(x, y, 0.0f));
		
		if (gui.getStart().intersectsWith(touchPoint)) {
			game.screenStack.add(new PlayScreen(game));
		}
		
		if (gui.getSound().intersectsWith(touchPoint)) {
			Settings.soundEnabled = !Settings.soundEnabled;
			if (Settings.soundEnabled) {
				music.play();
				gui.getSound().replaceTexture(Assets.atlas.findRegion("soundon"));
			} else {
				music.pause();
				gui.getSound().replaceTexture(Assets.atlas.findRegion("soundoff"));
			}
		}
		
		if (gui.getDebug().intersectsWith(touchPoint)) {
			Settings.debugEnabled = !Settings.debugEnabled;
			if (Settings.debugEnabled) {
				gui.getDebug().setText("debug: on");
			} else {
				gui.getDebug().setText("debug: off");
			}
		}
		
		return false;
	}
	
	@Override public void dispose() {}
	@Override public void hide() {}
	@Override public void resume() {}
	@Override public void resize(int width, int height) {}
}
