package com.tinyrender.rollemup.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.Drawable;

public class Button implements Drawable {
	public BitmapFontCache fontCache;
	public Rectangle bounds;
	
	public Button(BitmapFont font, String string) {
		fontCache = new BitmapFontCache(font);
		bounds = new Rectangle();
		
		fontCache.setText(string, 0.0f, 0.0f);
		setPositionAndBounds(0.0f, 0.0f);
	}
	
	public Button(BitmapFont font, String string, float x, float y) {
		fontCache = new BitmapFontCache(font);
		bounds = new Rectangle();
		
		fontCache.setText(string, 0.0f, 0.0f);
		setPositionAndBounds(x, y);
	}

	public void setPositionAndBounds(float x, float y) {
		fontCache.setPosition(x, y);
		bounds.set(fontCache.getX(),
				fontCache.getY()-fontCache.getBounds().height,
				fontCache.getBounds().width,
				fontCache.getBounds().height);
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
		fontCache.draw(Assets.batch);
	}
}