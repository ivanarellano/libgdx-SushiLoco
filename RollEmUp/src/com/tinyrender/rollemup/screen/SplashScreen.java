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
	public Gui gui;
		
	public SplashScreen(RollEmUp game) {
		super(game);
		gui = new Gui();
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
			
			Assets.batch.setColor(1.0f, 1.0f, 1.0f, splashAlpha);
			Assets.batch.draw(Assets.splashScreen, 0.0f, 0.0f);
		Assets.batch.end();
		
		if(!(totalScreenTime <= NEXT_SCREEN))
			game.screenStack.add(new MainMenuScreen(game));
		
		totalScreenTime += deltaTime;
	}

	@Override
	public void show() {
		// Remove splash from screen navigation
		game.screenStack.list.pop();
		
		Assets.batch.setProjectionMatrix(gui.cam.combined);
	}
	
	@Override public void dispose() {}
	@Override public void hide() {}
	@Override public void pause() {}
	@Override public void resize(int width, int height) {}
	@Override public void resume() {}
}
