package com.tinyrender.androidgame.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class SplashScreen implements Screen {
	public static final float NEXT_SCREEN = 3.0f;
	public float totalScreenTime = 0;
	public float splashAlpha = 1.0f;
	public boolean diagnosed = false;
	OrthographicCamera guiCam;
	RollEmUp game;
	
	public SplashScreen(RollEmUp g) {
		game = g;
		guiCam = new OrthographicCamera();
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		boolean result = Assets.manager.update();
		if (result & !diagnosed) {
			Gdx.app.log("AssetManagerTest", "\n" + Assets.manager.getDiagonistics() + "\n" + Texture.getManagedStatus());
			diagnosed = true;
		}
		
		Assets.batch.begin();
			if ((totalScreenTime >= 1.0f) && (splashAlpha >= 0)) {
				Assets.batch.setColor(1, 1, 1, splashAlpha);
				splashAlpha -= deltaTime;
			}
			
			Assets.batch.draw(Assets.splashScreen, 0, 0);
		Assets.batch.end();
		
		if(!(totalScreenTime <= NEXT_SCREEN))
			game.setScreen(game.mainMenuScreen);
		
		totalScreenTime += deltaTime;
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
	}

	//Gdx.app.log("splashAlpha", Float.valueOf(splashAlpha).toString());
}
