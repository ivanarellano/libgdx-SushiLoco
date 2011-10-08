package com.tinyrender.rollemup.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameScreen;
import com.tinyrender.rollemup.Gui;
import com.tinyrender.rollemup.RollEmUp;

public class SplashScreen extends GameScreen {
	public static final float NEXT_SCREEN = 5.0f;
	public float totalScreenTime = 0;
	public float splashAlpha = 1.0f;
	public boolean diagnosed = false;
		
	public SplashScreen(RollEmUp game) {
		super(game);
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
		
		Assets.batch.begin();
			if ((totalScreenTime >= 3.5f) && (splashAlpha >= 0)) {
				splashAlpha -= deltaTime;
				if(splashAlpha <= 0)
					splashAlpha = 0;
			}
			
			Assets.splashScreen.draw(Assets.batch, splashAlpha);
		Assets.batch.end();
		
		if(!(totalScreenTime <= NEXT_SCREEN))
			game.screenStack.add(new MainMenuScreen(game));
		
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
		// Remove splash from screen navigation
		game.screenStack.list.pop();
		
		Assets.batch.setProjectionMatrix(Gui.cam.combined);
	}
}
