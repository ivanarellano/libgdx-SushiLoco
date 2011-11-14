package com.tinyrender.rollemup;

public class GameObjectRepresentation extends ObjectRepresentation {
	public GameObject gameObj;
	
	public GameObjectRepresentation(GameObject gameObj) {
		this.gameObj = gameObj;
	}

	@Override
	public void draw() {
		// SpriteBatch.draw(textureRegion, x, y, originX, originY, width, height, scaleX, scaleY, rotation)
		if (gameObj.rolled) {
			Assets.batch.draw(texture, 
					gameObj.pos.x * Level.PTM_RATIO,
					gameObj.pos.y * Level.PTM_RATIO,
					width/2.0f, height/2.0f,
					width, height,
					1.0f, 1.0f,
					gameObj.rot);
		} else {
			Assets.batch.draw(texture,
					(gameObj.pos.x - (width/2.0f/Level.PTM_RATIO)) * Level.PTM_RATIO,
					(gameObj.pos.y - (height/2.0f/Level.PTM_RATIO)) * Level.PTM_RATIO,
					width/2.0f, height/2.0f,
					width, height,
					1.0f, 1.0f,
					gameObj.rot);
		}
	}
}