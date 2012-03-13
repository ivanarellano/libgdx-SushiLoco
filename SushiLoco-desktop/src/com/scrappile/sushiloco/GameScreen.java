package com.scrappile.sushiloco;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;

public abstract class GameScreen extends InputAdapter implements Screen {
	public SushiLoco game;
	
	public GameScreen(SushiLoco game) {
		this.game = game;
	}
}