package com.scrappile.sushiloco;

import java.util.Stack;

import com.badlogic.gdx.Gdx;

public class ScreenStack {
	SushiLoco game;
	public Stack<GameScreen> list = new Stack<GameScreen>();
	
	public ScreenStack(SushiLoco game) {
		this.game = game;
	}
	
	public void add(GameScreen screen) {
		list.add(screen);
		setScreen(screen);
	}
	
	public void setPrevious() {
		if (!list.empty())
			list.pop();
		
		if (!(list.size() <= 1)) {
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
