package com.tinyrender.rollemup.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.tinyrender.rollemup.Assets;

public class Label extends Button {
	public BitmapFontCache fontCache;
	
	public Label(BitmapFont font, String string) {
		fontCache = new BitmapFontCache(font);
		
		fontCache.setText(string, 0.0f, 0.0f);
		setPosition(0.0f, 0.0f);
	}
	
	public Label(BitmapFont font, String string, float x, float y) {
		fontCache = new BitmapFontCache(font);
		
		fontCache.setText(string, 0.0f, 0.0f);
		setPosition(x, y);
	}

	public void setPosition(float x, float y) {
		fontCache.setPosition(x, y);
		
		setBounds(fontCache.getX(),
				  fontCache.getY() - fontCache.getBounds().height,
				  fontCache.getBounds().width,
				  fontCache.getBounds().height);
	}

	@Override
	public void draw() {
		fontCache.draw(Assets.batch);
	}
}