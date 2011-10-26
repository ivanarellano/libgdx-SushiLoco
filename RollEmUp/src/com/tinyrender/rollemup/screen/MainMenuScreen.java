package com.tinyrender.rollemup.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.math.Vector3;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameScreen;
import com.tinyrender.rollemup.RollEmUp;
import com.tinyrender.rollemup.Settings;
import com.tinyrender.rollemup.gui.MainMenuGui;

public class MainMenuScreen extends GameScreen {
	Music music;
	Vector3 touchPoint;
	MainMenuGui gui;
	
	public MainMenuScreen(RollEmUp game) {
		super(game);
		touchPoint = new Vector3();
		gui = new MainMenuGui();
		
		if (Assets.manager.isLoaded("data/music.mp3")) {
			music = Assets.getMusic("data/music.mp3");
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
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		gui.render();
	}

	@Override
	public void show() {		
		if(Settings.soundEnabled) // FIXME: CHANGE TO "musicEnabled" WHEN READY
			music.play();
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
		
		if (gui.start.justHit(touchPoint)) {
			game.screenStack.add(new LevelSelectScreen(game));
		}
		
		if (gui.sound.justHit(touchPoint)) {
			Settings.soundEnabled = !Settings.soundEnabled;
			if (Settings.soundEnabled) {
				music.play();
				gui.sound.replaceTexture(Assets.atlas.findRegion("soundon"));
			} else {
				music.pause();
				gui.sound.replaceTexture(Assets.atlas.findRegion("soundoff"));
			}
		}
		
		if (gui.debug.justHit(touchPoint)) {
			Settings.debugEnabled = !Settings.debugEnabled;
			if (Settings.debugEnabled)
				gui.debug.replaceText("debug: on");
			else
				gui.debug.replaceText("debug: off");
		}
		
		return false;
	}
	
	@Override public void dispose() {}
	@Override public void hide() {}
	@Override public void resume() {}
	@Override public void resize(int width, int height) {}
}
