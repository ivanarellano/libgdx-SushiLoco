package com.tinyrender.androidgame.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;

public class MainMenuScreen implements Screen {
	RollEmUp game;
	
	public MainMenuScreen(RollEmUp g) {
		game = g;
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
			Assets.batch.draw(Assets.titleLogo,
							  Gdx.graphics.getWidth()/2 - Assets.titleLogo.packedWidth/2,
							  Gdx.graphics.getHeight()/2 + 75);
			Assets.batch.draw(Assets.start,
							  Gdx.graphics.getWidth()/2 - Assets.start.packedWidth/2,
							  Gdx.graphics.getHeight()/2 - 150);
			Assets.batch.draw(Assets.soundOn, 25, 25);
		Assets.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
		Assets.batch.setColor(1, 1, 1, 1);
		if(Settings.musicEnabled)
            Assets.music.play();
	}

}
