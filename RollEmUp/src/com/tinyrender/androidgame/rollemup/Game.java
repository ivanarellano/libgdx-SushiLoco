package com.tinyrender.androidgame.rollemup;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public abstract class Game implements ApplicationListener {
	Screen screen;

	public void setScreen(Screen screen) {
		screen.pause();
		screen.dispose();
		this.screen = screen;
	}
	
	public abstract Screen getStartScreen();
	
	@Override
	public void create() {
		screen = getStartScreen();
	}

	@Override
	public void dispose() {
		screen.dispose();
	}

	@Override
	public void pause() {
		screen.pause();
	}

	@Override
	public void render() {
		screen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int arg0, int arg1) {

	}

	@Override
	public void resume() {
		screen.resume();
	}

}
