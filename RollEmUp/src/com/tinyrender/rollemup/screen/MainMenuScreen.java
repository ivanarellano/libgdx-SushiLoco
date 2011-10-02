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
	RollEmUp game;
	Gui gui;
	
	Rectangle startBounds;
	Rectangle soundBounds;
	Rectangle debugBounds;
	Vector3 touchPoint;
	boolean setMoreText = true;
	
	public MainMenuScreen(RollEmUp g) {
		game = g;
		gui = new Gui();
		
		Assets.titleLogo.setPosition(RollEmUp.SCREEN_HALF_WIDTH - Assets.titleLogo.getWidth()/2.0f, RollEmUp.SCREEN_HALF_HEIGHT + 75.0f);		
		Assets.start.setPosition(RollEmUp.SCREEN_HALF_WIDTH - Assets.start.getWidth()/2.0f, RollEmUp.SCREEN_HALF_HEIGHT - 150.0f);
		Assets.soundOff.setPosition(50.0f, 50.0f);
		Assets.soundOn.setPosition(50.0f, 50.0f);
		
		startBounds = Assets.start.getBoundingRectangle();
		soundBounds = Assets.soundOff.getBoundingRectangle();
		touchPoint = new Vector3();
		
		debugBounds = new Rectangle();
		debugBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
		Settings.write();
	}
	
	public void update() {
		if (Gdx.input.justTouched()) {
			gui.camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0.0f));
			
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

			if (debugBounds.contains(touchPoint.x, touchPoint.y)) {
				Settings.debugEnabled = !Settings.debugEnabled;
				setMoreText = true;
			}
		}
	}

	@Override
	public void render(float deltaTime) {
		update();
		
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		Assets.batch.begin();
			Assets.titleScreen.draw(Assets.batch);
			Assets.titleLogo.draw(Assets.batch);
			Assets.start.draw(Assets.batch);
			
			if (Settings.soundEnabled)
				Assets.soundOn.draw(Assets.batch);
			else
				Assets.soundOff.draw(Assets.batch);
			
			if (setMoreText) {
				setMoreText = false;
				
				if (Settings.debugEnabled)
					Assets.droidFontCache.setText("Debug: On", 0.0f, 0.0f);
				else
					Assets.droidFontCache.setText("Debug: Off", 0.0f, 0.0f);
				
				Assets.droidFontCache.setPosition(RollEmUp.SCREEN_HALF_WIDTH - Assets.droidFontCache.getBounds().width/2.0f,
						  RollEmUp.SCREEN_HALF_HEIGHT - 200.0f);
				
				debugBounds.set(Assets.droidFontCache.getX(),
						Assets.droidFontCache.getY()-Assets.droidFontCache.getBounds().height,
						Assets.droidFontCache.getBounds().width,
						Assets.droidFontCache.getBounds().height);
			}
			
			Assets.droidFontCache.draw(Assets.batch);
			
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
		Assets.batch.setProjectionMatrix(gui.camera.combined);
		Assets.batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		if(Settings.soundEnabled) // FIXME: CHANGE TO musicEnabled WHEN READY
            Assets.music.play();
	}

}
