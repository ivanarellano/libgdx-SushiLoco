package com.tinyrender.rollemup;

import com.badlogic.gdx.Gdx;

public class Timer {
	public boolean enabled = false;
    public int time = 0;
    public String timeString;
    public float accumulatedTime = 0.0f;
    
    public void update() {
    	accumulatedTime += Gdx.graphics.getDeltaTime();
    	
    	// Minutes
    	if (accumulatedTime >= 60.0f) {
    		time--;
    		timeString = Integer.toString(time);
    		accumulatedTime = Gdx.graphics.getDeltaTime();
    	}
	}
    
    public void reset(int desiredCountdown) {
    	time = desiredCountdown;
    	timeString = Integer.toString(time);
    	accumulatedTime = 0.0f;
    }
}
