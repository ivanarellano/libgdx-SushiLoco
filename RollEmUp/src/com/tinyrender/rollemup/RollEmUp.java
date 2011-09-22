package com.tinyrender.rollemup;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.tinyrender.rollemup.screen.SplashScreen;

public class RollEmUp extends Game implements ApplicationListener {
	public final static int TARGET_WIDTH = 854;
	public final static int TARGET_HEIGHT = 480;
	public final static int SCREEN_HALF_WIDTH = (int) Math.ceil(TARGET_WIDTH / 2);
	public final static int SCREEN_HALF_HEIGHT = (int) Math.ceil(TARGET_HEIGHT /2);
	public static float ASPECT_RATIO;
	public static float SCALE_FACTOR_X;
	public static float SCALE_FACTOR_Y;
	
	@Override
	public void create() {		
		SCALE_FACTOR_X = (float)Gdx.graphics.getWidth() / TARGET_WIDTH;
		SCALE_FACTOR_Y = (float)Gdx.graphics.getHeight() / TARGET_HEIGHT;
		ASPECT_RATIO = (float)Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
		
		Settings.load();
		Assets.create();

		Gdx.app.log("AssetManagerDiagnostics", "\n" + Assets.manager.getDiagonistics() + "\n" + Texture.getManagedStatus());
		
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		Assets.manager.dispose();
		Assets.batch.dispose();
		
		getScreen().dispose();
	}

	@Override
	public void pause() {
		getScreen().pause();
	}

	@Override
	public void render() {
		getScreen().render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		getScreen().resize(width, height);
	}

	@Override
	public void resume() {
		Assets.manager.update();
		Assets.manager.finishLoading();
		
		getScreen().resume();
	}
}
