package com.scrappile.sushiloco.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.scrappile.sushiloco.*;
import com.scrappile.sushiloco.screen.PlayScreen;

public class LevelGui extends Gui implements LevelState {

	private static final BitmapFont DROID_SANS = Assets.getBitmapFont("droidsans.fnt", "droidsans");
	private static final LabelButton PAUSED = new LabelButton(DROID_SANS, "Paused");
	public static final LabelButton QUIT = new LabelButton(DROID_SANS, "Quit");

	private final GoalMeter goalMeter = new GoalMeter();
	private final Timer timer = new Timer();


    public LevelGui(Level level) {    	
    	PAUSED.setPosition(SushiLoco.SCREEN_HALF_WIDTH - PAUSED.getWidth() / 2.0f, SushiLoco.SCREEN_HALF_HEIGHT + 150.0f);
    	QUIT.setPosition(SushiLoco.SCREEN_HALF_WIDTH - QUIT.getWidth() / 2.0f, SushiLoco.SCREEN_HALF_HEIGHT + 80.0f);
    }
    
    public void update(int state, int totalScore) {
    	this.state = state;
    	goalMeter.scale = totalScore * 0.009f;
    }
    
    public void render(float deltaTime) {
		Assets.batch.setProjectionMatrix(cam.combined);
		Assets.batch.begin();
		
		switch (state) {
			case PlayScreen.GAME_READY:
				ready(deltaTime);
				break;
			case PlayScreen.GAME_RUNNING:
				running(deltaTime);
				break;
			case PlayScreen.GAME_PAUSED:
				paused(deltaTime);
				break;
			case PlayScreen.GAME_LEVEL_END:
				levelEnd(deltaTime);
				break;
			case PlayScreen.GAME_OVER:
				gameOver(deltaTime);
				break;
		}
		
		Assets.batch.end();
    }
    
    @Override
	public void ready(float deltaTime) {}
	
    @Override
	public void running(float deltaTime) {
		timer.update();
		timer.draw();
		goalMeter.draw();
	}

    @Override
	public void paused(float deltaTime) {
		PAUSED.draw();
		QUIT.draw();
	}

    @Override
	public void levelEnd(float deltaTime) {
	}

    @Override
	public void gameOver(float deltaTime) {
		// No-op
	}

	public Timer getTimer() {
		return timer;
	}

	public GoalMeter getGoalMeter() {
		return goalMeter;
	}
}
