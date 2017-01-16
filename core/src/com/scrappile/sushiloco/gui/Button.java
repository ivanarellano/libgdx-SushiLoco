package com.scrappile.sushiloco.gui;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.scrappile.sushiloco.Assets;

public class Button {

	private Sound sound;
	private Rectangle bounds;
	private float margin = 12.0f;
	
	public Button() {
		bounds = new Rectangle();
		sound = Assets.getSound("click.ogg");
	}
	
	public boolean intersectsWith(Vector3 touchPoint) {
		if (bounds.contains(touchPoint.x, touchPoint.y)) {
			Assets.playSound(sound);
			return true;
		}

		return false;
	}
	
	public void resetMargin(float margin) {
		this.margin = margin;
		setBounds(bounds);
	}
	
	public void setBounds(Rectangle rect) {
		bounds.set(rect.x - margin, rect.y - margin, rect.width + margin * 2.0f, rect.height + margin * 2.0f);
	}
	
	public void setBounds(float x, float y, float width, float height) {
		bounds.set(x - margin, y - margin, width + margin * 2.0f, height + margin * 2.0f);
	}

	public float getWidth() {
		return bounds.getWidth();
	}

	public float getHeight() {
		return bounds.getHeight();
	}
}
