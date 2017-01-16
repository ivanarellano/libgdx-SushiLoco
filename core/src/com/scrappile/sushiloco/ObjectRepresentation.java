package com.scrappile.sushiloco;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ObjectRepresentation extends Renderable {

	protected int width;
	protected int height;
	protected float halfWidth;
	protected float halfHeight;
		
	public int getLargestDimension() {
		return width > height ? width : height;
	}

	public void setTexture(TextureRegion texture) {
		// No-op
	};

	public void setTexture(boolean isHorizontal, TextureRegion... texture) {
		// No-op
	};

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getHalfWidth() {
		return halfWidth;
	}

	public float getHalfHeight() {
		return halfHeight;
	}
}
