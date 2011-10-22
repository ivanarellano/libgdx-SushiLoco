package com.tinyrender.rollemup;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.tinyrender.rollemup.screen.SplashScreen;

public class RollEmUp extends Game implements ApplicationListener {
	public final static int TARGET_WIDTH = 854;
	public final static int TARGET_HEIGHT = 480;
	public final static int SCREEN_HALF_WIDTH = (int) Math.ceil(TARGET_WIDTH / 2);
	public final static int SCREEN_HALF_HEIGHT = (int) Math.ceil(TARGET_HEIGHT /2);
	public boolean finishedLoading = false;
	public ScreenStack screenStack;
	public BitmapFont font;
	
	@Override
	public void create() {
		Gdx.app.log("libGdx ver", " "+ Gdx.app.getVersion());
		Settings.load();
		Assets.create();
		
		screenStack = new ScreenStack(this);
		screenStack.add(new SplashScreen(this));
		
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
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
		
		if (finishedLoading) {
			// Persistent FPS counter
			String fps = Integer.toString(Gdx.graphics.getFramesPerSecond());
			Assets.batch.getProjectionMatrix().setToOrtho2D(0.0f, 0.0f, RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
			Assets.batch.begin();
				font.draw(Assets.batch,
									  fps, 
									  RollEmUp.TARGET_WIDTH - font.getBounds(fps).width - 20.0f,
									  font.getBounds(fps).height + 20.0f);
			Assets.batch.end();
		}
		
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
