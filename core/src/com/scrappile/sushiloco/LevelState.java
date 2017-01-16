package com.scrappile.sushiloco;

public interface LevelState {
	void ready(float deltaTime);
	void running(float deltaTime);
	void paused(float deltaTime);
	void levelEnd(float deltaTime);
	void gameOver(float deltaTime);
}
