package com.tinyrender.rollemup.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL11;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameScreen;
import com.tinyrender.rollemup.RollEmUp;

public class LevelSelectScreen extends GameScreen {
	RollEmUp game;
	
	public LevelSelectScreen(RollEmUp g) {
		game = g;
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public void hide() {
		inputMultiplexer.removeProcessor(this);
	}

	@Override
	public void pause() {		
	}
	
	public void update() {
		if (Gdx.input.justTouched())
			game.screenStack.add(new PlayScreen(game));
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
		inputMultiplexer.addProcessor(this);
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Keys.BACK)
			game.screenStack.setPrevious();
		else if (keyCode == Keys.BACKSPACE)
			game.screenStack.setPrevious();
		return false;
	}
}
