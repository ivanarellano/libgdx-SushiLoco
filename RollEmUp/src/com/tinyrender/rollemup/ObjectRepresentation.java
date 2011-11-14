package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ObjectRepresentation extends Renderable {
	public int width;
	public int height;
	public float halfWidth;
	public float halfHeight;
	
	// Origin in upper left corner: X-right Y-down
	public TextureRegion texture;
	
	public void setTexture(TextureRegion texture) {
		this.texture = texture;
		width = texture.getRegionWidth();
		height = texture.getRegionHeight();
		
		halfWidth = width / 2.0f;
		halfHeight = height / 2.0f;
	}
	
	@Override
	public void draw() {
		// SpriteBatch.draw(textureRegion, x, y, width, height)
		Assets.batch.draw(texture, pos.x, pos.y, width, height);
	}
	
	public int getLargestDimension() {
		if (width > height)
			return width;
		else
			return height;
	}
}
