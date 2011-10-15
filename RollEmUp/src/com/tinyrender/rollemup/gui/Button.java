package com.tinyrender.rollemup.gui;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.Drawable;

public class Button implements Drawable {
	public Rectangle bounds;
	
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

	@Override
	public void draw() {		
	}
}
