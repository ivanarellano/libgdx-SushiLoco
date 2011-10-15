package com.tinyrender.rollemup.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.tinyrender.rollemup.ObjectRepresentation;

public class ImageButton extends ObjectRepresentation {
	public Button button;
	
	public ImageButton(TextureRegion texture) {
		button = new Button();
		setTexture(texture);
		x = y = 0.0f;
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		button.setBounds(x, y, this.width, this.height);
	}
	
	public void replaceTexture(TextureRegion texture) {
		setTexture(texture);
		setPosition(this.x, this.y);
	}
	
	public boolean justHit(Vector3 touchPoint) {
		return button.justHit(touchPoint);
	}
}
