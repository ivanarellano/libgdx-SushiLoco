package com.tinyrender.rollemup;

public interface LevelState {	
	public void ready(float deltaTime);
	public void running(float deltaTime);
	public void paused(float deltaTime);
	public void levelEnd(float deltaTime);
	public void gameOver(float deltaTime);
}
