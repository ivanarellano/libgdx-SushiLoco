package com.scrappile.sushiloco.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.Drawable;

public class TimerBar implements Drawable {
	TextureRegion barFillBg = Assets.atlas.findRegion("timerfill");
	TextureRegion barFillMask = Assets.atlas.findRegion("timerfillmask");
	TextureRegion barOutline = Assets.atlas.findRegion("timeroutline");
	
	@Override
	public void draw() {
		/*
		 * enableBlending() 
		 * setBlendFunction(int srcFunc, int dstFunc) 
		 * 
		 * timerFillMask
		 */
	}

}
