package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ObjectRepresentation implements Drawable {	
	public float x;
	public float y;
	public float width;
	public float height;
	public TextureRegion texture;
	
	public void setTexture(TextureRegion texture) {
		this.texture = texture;
		width = texture.getRegionWidth();
		height = texture.getRegionHeight();
	}

	@Override
	public void draw() {
		// SpriteBatch.draw(textureRegion, x, y, width, height)
		Assets.batch.draw(texture, x-width/2.0f, y-height/2.0f, width, height);
	}
}