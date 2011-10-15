package com.tinyrender.rollemup;

import java.util.Stack;

import com.badlogic.gdx.Gdx;

public class ScreenStack {
	RollEmUp game;
	public Stack<GameScreen> list = new Stack<GameScreen>();
	
	public ScreenStack(RollEmUp game) {
		this.game = game;
	}
	
	public void add(GameScreen screen) {
		list.add(screen);
		setScreen(screen);
	}
	
	public void setPrevious() {
		list.pop();
		if (!(list.size() <= 0)) {
			setScreen(list.peek());
			Gdx.input.setInputProcessor(list.peek());
		}
		else {
			Gdx.app.exit();
		}
	}
	
	private void setScreen(GameScreen screen) {
		game.setScreen(list.peek());
		Gdx.input.setInputProcessor(screen);
	}
}
