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
	}

	@Override
	public void pause() {
	}

	@Override
	public void render() {
		getScreen().render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int arg0, int arg1) {
	}

	@Override
	public void resume() {
	}	
}
