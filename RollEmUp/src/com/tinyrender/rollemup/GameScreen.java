package com.tinyrender.rollemup;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;

public abstract class GameScreen extends InputAdapter implements Screen {
	public RollEmUp game;
	
	public GameScreen(RollEmUp game) {
		this.game = game;
	}
}