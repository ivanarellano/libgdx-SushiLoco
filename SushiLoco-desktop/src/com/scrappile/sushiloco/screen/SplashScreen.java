package com.scrappile.sushiloco.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.GameScreen;
import com.scrappile.sushiloco.SushiLoco;

public class SplashScreen extends GameScreen {
	static final float NEXT_SCREEN = 5.0f;
	float totalScreenTime = 0;
	float splashAlpha = 1.0f;
	boolean diagnosed = false;
	
	public TextureRegion splashScreen;
		
	public SplashScreen(SushiLoco game) {
		super(game);
		
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
			Assets.droidSansFont = Assets.getBitmapFont("data/droidsans.fnt", "droidsans");
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
			
			if(!(totalScreenTime <= NEXT_SCREEN)) {
				game.screenStack.add(new MainMenuScreen(game));
				Assets.batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
			}
			
			totalScreenTime += deltaTime;
		}
	}

	@Override
	public void show() {
	}
	
	@Override public void dispose() {}
	@Override public void hide() {}
	@Override public void pause() {}
	@Override public void resize(int width, int height) {}
	@Override public void resume() {}
}
