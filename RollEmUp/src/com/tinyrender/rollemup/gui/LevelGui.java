package com.tinyrender.rollemup.gui;

import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.Drawable;
import com.tinyrender.rollemup.Gui;
import com.tinyrender.rollemup.RollEmUp;
import com.tinyrender.rollemup.Timer;

public class LevelGui extends Gui {
	public Label paused;
	public Label quit;
	public GoalMeter goalMeter;
    public Timer timer;

    public LevelGui() {
    	timer = new Timer();
    	goalMeter = new GoalMeter();
    	
    	paused = new Label(Assets.droidsans, "Paused");
    	paused.setPosition(RollEmUp.SCREEN_HALF_WIDTH-paused.bounds.width/2.0f, RollEmUp.SCREEN_HALF_HEIGHT+150.0f);
    	
    	quit = new Label(Assets.droidsans, "Quit");
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
	
	public void render(Drawable...drawables) {
		Assets.batch.setProjectionMatrix(cam.combined);
		Assets.batch.begin();
			for (Drawable obj : drawables)
				obj.draw();
		Assets.batch.end();
	}
}
