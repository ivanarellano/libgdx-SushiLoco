package com.tinyrender.androidgame.rollemup;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tinyrender.androidgame.rollemup.screens.GameScreen;
import com.tinyrender.androidgame.rollemup.screens.LevelSelectScreen;
import com.tinyrender.androidgame.rollemup.screens.MainMenuScreen;
import com.tinyrender.androidgame.rollemup.screens.SplashScreen;

public class RollEmUp extends Game implements ApplicationListener {
	public SplashScreen splashScreen;
	public MainMenuScreen mainMenuScreen;
	public LevelSelectScreen levelSelectScreen;
	boolean assetsDiagnosed = false;
	
	@Override
	public void create() {
		Settings.load();
		Assets.create();
		
		splashScreen = new SplashScreen(this);
		mainMenuScreen = new MainMenuScreen(this);
		levelSelectScreen = new LevelSelectScreen(this);
		
		if (!assetsDiagnosed) {
			//Gdx.app.log("AssetManagerDiagnostics", "\n" + Assets.manager.getDiagonistics() + "\n" + Texture.getManagedStatus());
			assetsDiagnosed = true;
		}
		
		setScreen(splashScreen);
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
	
	/** Assumes Gdx.input.justTouched() is true */
	public static void logTouchedInput() {
		Gdx.app.log("touchLog", "x: " + Integer.toString(Gdx.input.getX()) + " / y: " + Integer.toString(Gdx.input.getY()));
	}
}
