package com.tinyrender.rollemup;


public class GameObjectRepresentation extends ObjectRepresentation {
	public GameObject gameObject;
	
	public GameObjectRepresentation(GameObject gameObject) {
		this.gameObject = gameObject;
	}

	@Override
	public void draw() {
		// SpriteBatch.draw(textureRegion, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
		Assets.batch.draw(texture,
					(gameObject.pos.x - (width/2.0f/Level.PTM_RATIO)) * Level.PTM_RATIO,
					(gameObject.pos.y - (height/2.0f/Level.PTM_RATIO)) * Level.PTM_RATIO,
					width/2.0f, height/2.0f,
					width, height,
					1.0f, 1.0f,
					gameObject.rotation);
	}
}