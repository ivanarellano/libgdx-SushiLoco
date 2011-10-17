package com.tinyrender.rollemup.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameScreen;
import com.tinyrender.rollemup.RollEmUp;

public class LevelSelectScreen extends GameScreen {
	TextureRegion levelSelect;
	
	public LevelSelectScreen(RollEmUp game) {
		super(game);
		levelSelect = Assets.atlas.findRegion("levelselect");
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
			Assets.batch.draw(levelSelect, 0.0f, 0.0f);
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
	
	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Keys.BACK || keyCode == Keys.BACKSPACE)
			game.screenStack.setPrevious();
		return false;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointerId, int button) {		
		game.screenStack.add(new PlayScreen(game));
		return false;
	}
}
