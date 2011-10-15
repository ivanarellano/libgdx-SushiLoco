package com.tinyrender.rollemup.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.Drawable;

public class LabelButton extends Button implements Drawable {
	public BitmapFontCache fontCache;
	
	public LabelButton(BitmapFont font, String string) {
		fontCache = new BitmapFontCache(font);
		
		fontCache.setText(string, 0.0f, 0.0f);
		setPosition(0.0f, 0.0f);
	}
	
	public LabelButton(BitmapFont font, String string, float x, float y) {
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
	
	public void replaceText(String string) {
		float prevX = fontCache.getX();
		float prevY = fontCache.getY();
		
		fontCache.setText(string, 0.0f, 0.0f);
		
		setPosition(prevX, prevY);
	}

	@Override
	public void draw() {
		fontCache.draw(Assets.batch);
	}
}