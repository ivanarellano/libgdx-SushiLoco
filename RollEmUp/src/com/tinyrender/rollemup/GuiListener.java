package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.g2d.TextureRegion;


public interface GuiListener {	
	//public void setTimer(Timer timer);
	public TextureRegion getGoalMeterTexture();
	public float updateGoalMeterScale();
}
