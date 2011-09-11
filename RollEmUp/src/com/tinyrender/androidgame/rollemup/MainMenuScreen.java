package com.tinyrender.androidgame.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MainMenuScreen implements Screen {
	boolean diagnosed = false;
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
	public void render(float arg0) {
		boolean result = Assets.manager.update();
		if (result & !diagnosed) {
			Gdx.app.log("AssetManagerTest", "\n" + Assets.manager.getDiagonistics() + "\n" + Texture.getManagedStatus());
			diagnosed = true;
		}
		
		Assets.batch.begin();
			Assets.batch.draw(Assets.titleLogo,
							  Gdx.graphics.getWidth()/2 - Assets.titleLogo.packedWidth/2,
							  Gdx.graphics.getHeight()/2 + 75);
			Assets.batch.draw(Assets.start,
							  Gdx.graphics.getWidth()/2 - Assets.start.packedWidth/2,
							  Gdx.graphics.getHeight()/2 - 150);
		Assets.batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void show() {
		
	}

}
