package com.tinyrender.androidgame.rollemup;

import com.badlogic.gdx.Screen;

public class RollEmUp extends Game {
	boolean firstTimeCreate = true;
	
	@Override
	public Screen getStartScreen() {
		return new MainMenuScreen();
	}
	
	@Override
	public void create() {
		Settings.load();
		Assets.create();
		super.create();
	}

}
