package com.tinyrender.rollemup;

public class GameObjectRepresentation extends ObjectRepresentation {
	public GameObject gameObj;
	
	public GameObjectRepresentation(GameObject gameObj) {
		this.gameObj = gameObj;
	}

	@Override
	public void draw() {
		if (gameObj.rolled) {
			Assets.batch.draw(texture, 
					gameObj.pos.x - halfWidth + gameObj.orbitRadius,
					gameObj.pos.y - halfHeight + gameObj.orbitRadius,
					halfWidth - gameObj.orbitRadius, halfHeight - gameObj.orbitRadius,
					width, height,
					1.0f, 1.0f,
					gameObj.rot);
		} else {
			Assets.batch.draw(texture,
					(gameObj.pos.x - (halfWidth / Level.PTM_RATIO)) * Level.PTM_RATIO,
					(gameObj.pos.y - (halfHeight / Level.PTM_RATIO)) * Level.PTM_RATIO,
					halfWidth, halfHeight,
					width, height,
					1.0f, 1.0f,
					gameObj.rot);
		}
	}
}