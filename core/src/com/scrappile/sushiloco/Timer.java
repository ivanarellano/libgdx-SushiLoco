package com.scrappile.sushiloco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Timer implements Drawable {
    public int time = 0;
    public String timeString;
    public float accumulatedTime = 0.0f;
    public BitmapFont font;

    public Timer() {
    	font = Assets.getBitmapFont("data/droidsans.fnt", "droidsans");
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
		font.draw(Assets.batch,
				timeString, 
				SushiLoco.SCREEN_WIDTH - font.getBounds(timeString).width - 20.0f,
				SushiLoco.SCREEN_HEIGHT - 20.0f);
    }
}
