package com.tinyrender.rollemup.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.math.Rectangle;
import com.tinyrender.rollemup.Assets;

public class Label extends Button {
	public BitmapFontCache fontCache;
	public float boundsMargin = 10.0f;
	
	public Label(BitmapFont font, String string) {
		fontCache = new BitmapFontCache(font);
		
		fontCache.setText(string, 0.0f, 0.0f);
		setPositionAndBounds(0.0f, 0.0f);
	}
	
	public Label(BitmapFont font, String string, float x, float y) {
		fontCache = new BitmapFontCache(font);
		bounds = new Rectangle();
		
		fontCache.setText(string, 0.0f, 0.0f);
		setPositionAndBounds(x, y);
	}

	public void setPositionAndBounds(float x, float y) {
		fontCache.setPosition(x, y);
		bounds.set(fontCache.getX() - boundsMargin,
				(fontCache.getY()-fontCache.getBounds().height) - boundsMargin,
				fontCache.getBounds().width + boundsMargin*2.0f,
				fontCache.getBounds().height + boundsMargin*2.0f);
	}

	@Override
	public void draw() {
		fontCache.draw(Assets.batch);
	}
}