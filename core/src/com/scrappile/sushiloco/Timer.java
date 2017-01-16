package com.scrappile.sushiloco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Timer implements Drawable {

    private int time = 0;
	private String timeString;
	private float accumulatedTime = 0.0f;
	private BitmapFont font;
	private GlyphLayout timeLayout;

    public Timer() {
    	font = Assets.getBitmapFont("droidsans.fnt", "droidsans");
    	timeLayout = new GlyphLayout();
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
    	timeLayout.setText(font, timeString);

		font.draw(Assets.batch, timeString,
				SushiLoco.SCREEN_WIDTH - timeLayout.width - 20.0f,
				SushiLoco.SCREEN_HEIGHT - 20.0f);
    }
}
