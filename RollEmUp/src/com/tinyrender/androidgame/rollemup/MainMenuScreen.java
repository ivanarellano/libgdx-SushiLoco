package com.tinyrender.androidgame.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen {
	public int screenHalfWidth = Gdx.graphics.getWidth()/2;
	public int screenHalfHeight = Gdx.graphics.getHeight()/2;
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
		Assets.soundOff.setPosition(25, 25);
		Assets.soundOn.setPosition(25, 25);
		
		startBounds = Assets.start.getBoundingRectangle();
		soundBounds = Assets.soundOff.getBoundingRectangle();
		
		touchPoint = new Vector3();		
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
			gui.camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			Gdx.app.log("touchLog", "x: " + Integer.toString(Gdx.input.getX()) + " / y: " + Integer.toString(Gdx.input.getY()));
			
			if (OverlapTester.pointInRectangle(startBounds, touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.hitSound);
				Gdx.app.log("hitStart", startBounds.x + " " + startBounds.y);
				//game.setScreen(new LevelSelectScreen(game));
				return;
			}
			
			if (OverlapTester.pointInRectangle(soundBounds, touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.hitSound);
				Settings.soundEnabled = !Settings.soundEnabled;
				Gdx.app.log("touchLog", "Hit sound button");
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

		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1.0f);
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
		if(Settings.musicEnabled)
            Assets.music.play();
	}

}
