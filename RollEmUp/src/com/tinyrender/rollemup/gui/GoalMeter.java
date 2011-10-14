package com.tinyrender.rollemup.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.ObjectRepresentation;
import com.tinyrender.rollemup.RollEmUp;

public class GoalMeter extends ObjectRepresentation {
	public float scale;
	
	public GoalMeter() {
		
	}
	
	public GoalMeter(TextureRegion texture) {
		setTexture(texture);
	}
	
	@Override
	public void draw() {
		Assets.batch.draw(texture,
				(-width * scale) /2.0f, RollEmUp.TARGET_HEIGHT -(height * scale) / 2.0f,
				width * scale / 2.0f, height * scale / 2.0f,
				width * scale, 
				height * scale,
				1.0f, 1.0f,
				0.0f);
	}
}