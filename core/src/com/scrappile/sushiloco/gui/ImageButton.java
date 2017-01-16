package com.scrappile.sushiloco.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.scrappile.sushiloco.SingleTexture;

public class ImageButton extends SingleTexture {

	private Button button;

	public ImageButton(TextureRegion texture) {
		super(texture);

		button = new Button();
		pos.x = pos.y = 0.0f;
	}
	
	public void setPosition(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
		button.setBounds(x, y, this.width, this.height);
	}

	public void replaceTexture(TextureRegion texture) {
		setTexture(texture);
		setPosition(this.pos.x, this.pos.y);
	}

	public boolean intersectsWith(Vector3 touchPoint) {
		return button.intersectsWith(touchPoint);
	}
}
