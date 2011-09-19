package com.tinyrender.rollemup.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.Gui;
import com.tinyrender.rollemup.RollEmUp;
import com.tinyrender.rollemup.Settings;

public class MainMenuScreen implements Screen {
	public final int screenHalfWidth = Gdx.graphics.getWidth()/2;
	public final int screenHalfHeight = Gdx.graphics.getHeight()/2;
	
	RollEmUp game;
	Gui gui;
	
	Rectangle startBounds;
	Rectangle soundBounds;
	Vector3 touchPoint;
	
	public MainMenuScreen(RollEmUp g) {
		game = g;
		gui = new Gui();
		
		Assets.titleLogo.setPosition(screenHalfWidth - Assets.titleLogo.getWidth()/2, screenHalfHeight + 75);		
		Assets.start.setPosition(screenHalfWidth - Assets.start.getWidth()/2, screenHalfHeight - 150);
		Assets.soundOff.setPosition(50, 50);
		Assets.soundOn.setPosition(50, 50);
		
		startBounds = Assets.start.getBoundingRectangle();
		soundBounds = Assets.soundOff.getBoundingRectangle();
		touchPoint = new Vector3();		
	}
	
	@Override
	public void dispose() {
		Gdx.app.log("MainMenuScreen", "dispose()");
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
		Settings.write();
		Gdx.app.log("MainMenuScreen", "pause()");
	}
	
	public void update() {
		if (Gdx.input.justTouched()) {
			gui.camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			if (startBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.hitSound);
				game.setScreen(new LevelSelectScreen(game));
				return;
			}
			
			if (soundBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.hitSound);
				Settings.soundEnabled = !Settings.soundEnabled;
				if (Settings.soundEnabled)
					Assets.music.play();
				else
					Assets.music.pause();				
			}
		}
	}

	@Override
	public void render(float deltaTime) {
		update();
		
		gui.camera.update();
		Assets.batch.setProjectionMatrix(gui.camera.combined);

		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		Assets.batch.begin();
			Assets.titleLogo.draw(Assets.batch);
			Assets.start.draw(Assets.batch);
			
			if(Settings.soundEnabled)
				Assets.soundOn.draw(Assets.batch);
			else
				Assets.soundOff.draw(Assets.batch);
		Assets.batch.end();		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
		Assets.batch.setColor(1, 1, 1, 1);
		
		if(Settings.soundEnabled) // FIXME: CHANGE TO musicEnabled WHEN READY
            Assets.music.play();
	}

}
