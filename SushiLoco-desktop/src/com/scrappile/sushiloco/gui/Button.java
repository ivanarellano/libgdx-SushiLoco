package com.scrappile.sushiloco.gui;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.scrappile.sushiloco.Assets;

public class Button {
	public Sound hitSound;
	public Rectangle bounds;
	public float boundsMargin = 12.0f;
	
	public Button() {
		bounds = new Rectangle();
		hitSound = Assets.getSound("data/click.ogg");
	}
	
	public boolean justHit(Vector3 touchPoint) {
		if (bounds.contains(touchPoint.x, touchPoint.y)) {
			Assets.playSound(hitSound);
			return true;
		}
		return false;
	}
	
	public void resetBoundsMargin(float margin) {
		boundsMargin = margin;
		setBounds(bounds);
	}
	
	public void setBounds(Rectangle rect) {
		bounds.set(rect.x - boundsMargin, rect.y - boundsMargin,
				rect.width + boundsMargin*2.0f, rect.height + boundsMargin*2.0f);
	}
	
	public void setBounds(float x, float y, float width, float height) {
		bounds.set(x - boundsMargin, y - boundsMargin, width + boundsMargin*2.0f, height + boundsMargin*2.0f);
	}
}
