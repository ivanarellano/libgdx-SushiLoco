package com.tinyrender.rollemup;

import com.badlogic.gdx.Gdx;

public class Timer implements Drawable {
    public int time = 0;
    public String timeString;
    public float accumulatedTime = 0.0f;

    public Timer() {
    	
    }
    
    public Timer(int levelTime) {
    	reset(levelTime);
    }
    
    public void update() {
    	accumulatedTime += Gdx.graphics.getDeltaTime();
    	
    	// Minutes
    	if (accumulatedTime >= 60.0f) {
    		time--;
    		timeString = Integer.toString(time) + "m";
    		accumulatedTime = Gdx.graphics.getDeltaTime();
    	}
	}
    
    public void reset(int desiredCountdown) {
    	time = desiredCountdown;
    	timeString = Integer.toString(time) + "m";
    	accumulatedTime = 0.0f;
    }
    
    @Override
    public void draw() {
		Assets.droidsans.draw(Assets.batch,
				timeString, 
				RollEmUp.TARGET_WIDTH - Assets.droidsans.getBounds(timeString).width - 20.0f,
				RollEmUp.TARGET_HEIGHT - 20.0f);
    }
}
