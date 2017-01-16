package com.scrappile.sushiloco.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.Drawable;

public class LabelButton extends Button implements Drawable {

	private BitmapFontCache fontCache;
	private GlyphLayout layout;
	
	public LabelButton(BitmapFont font, String string) {
		fontCache = new BitmapFontCache(font);
		layout = fontCache.setText(string, 0.0f, 0.0f);
		setPosition(0.0f, 0.0f);
	}
	
	public LabelButton(BitmapFont font, String string, float x, float y) {
		fontCache = new BitmapFontCache(font);
		fontCache.setText(string, 0.0f, 0.0f);
		setPosition(x, y);
	}

	public void setPosition(float x, float y) {
		fontCache.setPosition(x, y);
	}
	
	public void setText(String string) {
		layout = fontCache.setText(string, fontCache.getX(), fontCache.getY());
		setBounds(fontCache.getX(), fontCache.getY() - layout.height, layout.width, layout.height);
	}

	@Override
	public void draw() {
		fontCache.draw(Assets.batch);
	}
}