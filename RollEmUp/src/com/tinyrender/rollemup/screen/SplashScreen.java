package com.tinyrender.rollemup.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameScreen;
import com.tinyrender.rollemup.Gui;
import com.tinyrender.rollemup.RollEmUp;

public class SplashScreen extends GameScreen {
	static final float NEXT_SCREEN = 5.0f;
	float totalScreenTime = 0;
	float splashAlpha = 1.0f;
	boolean diagnosed = false;
	
	public Gui gui;
	public TextureRegion splashScreen;
		
	public SplashScreen(RollEmUp game) {
		super(game);
		gui = new Gui();
		
		Assets.loadTexture("data/scratch.png");
		Assets.loadBitmapFont("data/droidsans.fnt");
		Assets.loadMusic("data/music.mp3");
		Assets.loadSound("data/click.ogg");
	}

	@Override
	public void render(float deltaTime) {
		if (Assets.manager.getProgress() != 1.0f) {
			Assets.manager.update();
			Gdx.app.log("progress", Float.toString(Assets.manager.getProgress()));
		}
		
		if (Assets.manager.getProgress() == 1.0f && !diagnosed) {
			game.finishedLoading = true;
			diagnosed = true;
			Gdx.app.log("AssetManagerDiagnostics", "\n" + Assets.manager.getDiagnostics() + "\n" + Texture.getManagedStatus());
			
			splashScreen = Assets.atlas.findRegion("tuna");
			game.font = Assets.getBitmapFont("data/droidsans.fnt", "droidsans");
		}
		
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		if (game.finishedLoading) {
			Assets.batch.begin();
				if ((totalScreenTime >= 3.5f) && (splashAlpha >= 0)) {
					splashAlpha -= deltaTime;
					if(splashAlpha <= 0)
						splashAlpha = 0;
				}
				
				Assets.batch.setColor(1.0f, 1.0f, 1.0f, splashAlpha);
				Assets.batch.draw(splashScreen, 0.0f, 0.0f);
			Assets.batch.end();
			
			if(!(totalScreenTime <= NEXT_SCREEN))
				game.screenStack.add(new MainMenuScreen(game));
			
			totalScreenTime += deltaTime;
		}
	}

	@Override
	public void show() {		
		Assets.batch.setProjectionMatrix(gui.cam.combined);
	}
	
	@Override public void dispose() {}
	@Override public void hide() {}
	@Override public void pause() {}
	@Override public void resize(int width, int height) {}
	@Override public void resume() {}
}
