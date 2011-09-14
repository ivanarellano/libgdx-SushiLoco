package com.tinyrender.androidgame.rollemup;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class RollEmUp extends Game implements ApplicationListener {	
	MainMenuScreen mainMenuScreen;
	SplashScreen splashScreen;
	
	@Override
	public void create() {
		Settings.load();
		Assets.create();
		
		mainMenuScreen = new MainMenuScreen(this);
		splashScreen = new SplashScreen(this);
		
		setScreen(splashScreen);
	}

	@Override
	public void dispose() {
		getScreen().dispose();
		Assets.manager.dispose();
		Assets.batch.dispose();
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
		getScreen().resume();
	}	
}
