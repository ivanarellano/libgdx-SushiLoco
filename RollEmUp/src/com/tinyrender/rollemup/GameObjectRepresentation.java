package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameObjectRepresentation {
	public GameObject gameObject;
	public TextureRegion texture;
	public float pixelWidth;
	public float pixelHeight;
	
	public GameObjectRepresentation(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	public void setTexture(TextureRegion texture) {
		this.texture = texture;
		pixelWidth = texture.getRegionWidth();
		pixelHeight = texture.getRegionHeight();
	}

	/*
	 *	SpriteBatch.draw()
	 *  params: textureRegion, x, y, originX, originY, width, height, scaleX, scaleY, rotation
	 */
	public void draw() {
		Assets.batch.draw(texture,
				(gameObject.pos.x - (pixelWidth/2.0f/Level.PTM_RATIO)) * Level.PTM_RATIO, (gameObject.pos.y - (pixelHeight/2.0f/Level.PTM_RATIO)) * Level.PTM_RATIO,
				pixelWidth/2.0f, pixelHeight/2.0f,
				pixelWidth, pixelHeight,
				1.0f, 1.0f,
				gameObject.rotation);
	}
}