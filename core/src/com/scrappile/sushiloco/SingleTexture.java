package com.scrappile.sushiloco;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SingleTexture extends ObjectRepresentation {

	public TextureRegion texture;

	public SingleTexture() {
		// No-op
	}
	
	public SingleTexture(TextureRegion texture) {
		setTexture(texture);
	}
	
	@Override
	public void setTexture(TextureRegion texture) {
		this.texture = texture;
		width = texture.getRegionWidth();
		height = texture.getRegionHeight();
		halfWidth = width / 2.0f;
		halfHeight = height / 2.0f;		
	}

	@Override
	public void draw() {
		Assets.batch.draw(texture, pos.x, pos.y, width, height);
	}
}
