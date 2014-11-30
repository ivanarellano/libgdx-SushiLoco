package com.scrappile.sushiloco;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ObjectRepresentation extends Renderable {
	public int width;
	public int height;
	public float halfWidth;
	public float halfHeight;
		
	public int getLargestDimension() {
		if (width > height)
			return width;
		else
			return height;
	}

	public void setTexture(TextureRegion texture) {}
	public void setTexture(boolean isHorizontal, TextureRegion... texture) {}
}
