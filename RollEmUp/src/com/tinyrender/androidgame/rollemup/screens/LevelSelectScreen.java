package com.tinyrender.androidgame.rollemup.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;
import com.tinyrender.androidgame.rollemup.Assets;
import com.tinyrender.androidgame.rollemup.Gui;
import com.tinyrender.androidgame.rollemup.RollEmUp;

public class LevelSelectScreen implements Screen {
	RollEmUp game;
	Gui gui;
	
	public LevelSelectScreen(RollEmUp g) {
		game = g;
		gui = new Gui();
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
	
	public void update() {
		if(Gdx.input.justTouched())
			game.setScreen(game.gameScreen);
	}

	@Override
	public void render(float deltaTime) {
		update();
		
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);

		Assets.batch.begin();
			Assets.levelSelectMap.draw(Assets.batch);
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
	}

}
