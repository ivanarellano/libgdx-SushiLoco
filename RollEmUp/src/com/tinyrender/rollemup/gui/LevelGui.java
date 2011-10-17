package com.tinyrender.rollemup.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.Gui;
import com.tinyrender.rollemup.LevelStateUpdater;
import com.tinyrender.rollemup.RollEmUp;
import com.tinyrender.rollemup.Timer;

public class LevelGui extends Gui implements LevelStateUpdater {
	public LabelButton paused;
	public LabelButton quit;
	public GoalMeter goalMeter;
    public Timer timer;
    public BitmapFont droidSans;

    public LevelGui() {
    	droidSans = Assets.getBitmapFont("data/droidsans.fnt", "droidsans");
    	timer = new Timer();
    	goalMeter = new GoalMeter();
    	
    	paused = new LabelButton(droidSans, "Paused");
    	paused.setPosition(RollEmUp.SCREEN_HALF_WIDTH-paused.bounds.width/2.0f, RollEmUp.SCREEN_HALF_HEIGHT+150.0f);
    	
    	quit = new LabelButton(droidSans, "Quit");
    	quit.setPosition(RollEmUp.SCREEN_HALF_WIDTH-quit.bounds.width/2.0f, RollEmUp.SCREEN_HALF_HEIGHT+80.0f);
    }
    
	@Override
	public void ready(float deltaTime) {
	}
	
	@Override
	public void running(float deltaTime) {
		timer.update();
		
		render(timer, goalMeter);
	}

	@Override
	public void paused(float deltaTime) {
		render(paused, quit);
	}

	@Override
	public void levelEnd(float deltaTime) {		
	}

	@Override
	public void gameOver(float deltaTime) {		
	}
}
