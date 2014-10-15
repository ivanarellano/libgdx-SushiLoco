package com.scrappile.sushiloco.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.SingleTexture;
import com.scrappile.sushiloco.SushiLoco;

public class GoalMeter extends SingleTexture {
	public float scale;
	
	public GoalMeter() {
		
	}
	
	public GoalMeter(TextureRegion texture) {
		setTexture(texture);
	}
	
	@Override
	public void draw() {
		Assets.batch.draw(texture,
				(-width * scale) /2.0f, SushiLoco.SCREEN_HEIGHT -(height * scale) / 2.0f,
				width * scale / 2.0f, height * scale / 2.0f,
				width * scale, 
				height * scale,
				1.0f, 1.0f,
				0.0f);
	}
}