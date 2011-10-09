package com.tinyrender.rollemup;

import com.badlogic.gdx.Gdx;

public class Timer {
    public int time = 0;
    public float accumulatedTime = 0.0f;
    
    public void update() {
    	accumulatedTime += Gdx.graphics.getDeltaTime();
    	
    	if (accumulatedTime >= 60.0f) {
    		time++;
    		accumulatedTime = Gdx.graphics.getDeltaTime();
    	}
	}
    
    public void reset() {
    	time = 0;
    	accumulatedTime = 0.0f;
    }
}
