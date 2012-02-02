package com.tinyrender.rollemup;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tinyrender.rollemup.gui.Gui;
import com.tinyrender.rollemup.screen.SplashScreen;

public class RollEmUp extends Game implements ApplicationListener {
	public final static float TARGET_WIDTH = 854.0f;
	public final static float TARGET_HEIGHT = 480.0f;
	public final static float TARGET_HALF_WIDTH = (float) Math.ceil(TARGET_WIDTH / 2);
	public final static float TARGET_HALF_HEIGHT = (float) Math.ceil(TARGET_HEIGHT /2);
	public final static float TARGET_WIDTH_BOX2D = TARGET_WIDTH / Level.PTM_RATIO;
	public final static float TARGET_HEIGHT_BOX2D = TARGET_HEIGHT / Level.PTM_RATIO;
	public final static float TARGET_HALF_WIDTH_BOX2D = TARGET_HALF_WIDTH / Level.PTM_RATIO;
	public final static float TARGET_HALF_HEIGHT_BOX2D = TARGET_HALF_HEIGHT / Level.PTM_RATIO;
	public boolean finishedLoading = false;
	public ScreenStack screenStack;
	public Gui gui;
	
	@Override
	public void create() {
		Gdx.app.log("libGdx ver", Integer.toString(Gdx.app.getVersion()));
		Settings.load();
		Assets.create();
		
		screenStack = new ScreenStack(this);
		screenStack.add(new SplashScreen(this));
		
		Gdx.graphics.setVSync(true);
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
		
		//Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		gui = new Gui();
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
				Assets.droidSansFont.draw(Assets.batch,
										  fps, 
										  RollEmUp.TARGET_WIDTH - Assets.droidSansFont.getBounds(fps).width - 20.0f,
										  Assets.droidSansFont.getBounds(fps).height + 20.0f);
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
