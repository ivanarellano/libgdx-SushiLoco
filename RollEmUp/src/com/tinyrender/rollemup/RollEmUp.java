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
	public ScreenStack screenStack;
	
	@Override
	public void create() {
		Settings.load();
		Assets.create();
		Gdx.app.log("AssetManagerDiagnostics", "\n" + Assets.manager.getDiagonistics() + "\n" + Texture.getManagedStatus());
		
		screenStack = new ScreenStack(this);
		screenStack.add(new SplashScreen(this));
		
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void dispose() {
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
		
		// FPS counter
		String fps = Integer.toString(Gdx.graphics.getFramesPerSecond());
		Assets.batch.begin();
			Assets.droidsans.draw(Assets.batch,
								  fps, 
								  RollEmUp.TARGET_WIDTH - Assets.droidsans.getBounds(fps).width - 20.0f,
								  RollEmUp.TARGET_HEIGHT - 20.0f);
		Assets.batch.end();
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
