package com.tinyrender.androidgame.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class MainMenuScreen implements Screen {
	RollEmUp game;
	Gui gui;
	OrthographicCamera camera;
	GameObject startBounds;
	GameObject soundBounds;
	Vector3 touchPoint;
	
	public MainMenuScreen(RollEmUp g) {
		game = g;
		camera = new OrthographicCamera();
		gui = new Gui();
		
		startBounds = new GameObject(Gdx.graphics.getWidth()/2 - Assets.start.packedWidth/2,
									 Gdx.graphics.getHeight()/2 - 150,
									 Assets.start.packedWidth, Assets.start.packedHeight);
		soundBounds = new GameObject(25, 25,
									 Assets.soundOff.packedWidth, Assets.soundOff.packedHeight);
		
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
			if (OverlapTester.pointInRectangle(startBounds.bounds, touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.hitSound);
				Gdx.app.log("hitStart", startBounds.bounds.x + " " + startBounds.bounds.y);
				//game.setScreen(new LevelSelectScreen(game));
				return;
			}
			
			if (OverlapTester.pointInRectangle(soundBounds.bounds, touchPoint.x, touchPoint.y)) {
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

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		Assets.batch.begin();
			Assets.batch.draw(Assets.titleLogo,
							  Gdx.graphics.getWidth()/2 - Assets.titleLogo.packedWidth/2,
							  Gdx.graphics.getHeight()/2 + 75);
			Assets.batch.draw(Assets.start,
							  startBounds.position.x,
							  startBounds.position.y);
			Assets.batch.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff,
							  soundBounds.position.x,
							  soundBounds.position.y);
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
