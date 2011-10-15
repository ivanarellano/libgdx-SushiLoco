package com.tinyrender.rollemup.gui;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.Drawable;

public class Button implements Drawable {
	public Rectangle bounds;
	public float boundsMargin = 10.0f;
	
	public Button() {
		bounds = new Rectangle();
	}
	
	public boolean justHit(Vector3 touchPoint) {
		if (bounds.contains(touchPoint.x, touchPoint.y)) {
			Assets.playSound(Assets.hitSound);
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

	@Override
	public void draw() {		
	}
}
