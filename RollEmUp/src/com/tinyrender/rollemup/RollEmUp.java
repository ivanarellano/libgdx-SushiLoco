package com.tinyrender.rollemup;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tinyrender.rollemup.screen.SplashScreen;

public class RollEmUp extends Game implements ApplicationListener {
	boolean assetsDiagnosed = false;
	
	@Override
	public void create() {
		Settings.load();
		Assets.create();
		
		if (!assetsDiagnosed) {
			//Gdx.app.log("AssetManagerDiagnostics", "\n" + Assets.manager.getDiagonistics() + "\n" + Texture.getManagedStatus());
			assetsDiagnosed = true;
		}
		
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
	
	/** Assumes Gdx.input.justTouched() is true */
	public static void logTouchedInput() {
		Gdx.app.log("touchLog", "x: " + Integer.toString(Gdx.input.getX()) + " / y: " + Integer.toString(Gdx.input.getY()));
	}
}
