package com.scrappile.sushiloco;

public class GameObjectRepresentation extends ObjectRepresentation {
	public GameObject gameObj;

	public GameObjectRepresentation(GameObject gameObj) {
		this.gameObj = gameObj;
	}

	@Override
	public void draw() {
		if (gameObj.isDead) {
			Assets.batch.draw(texture, 
					(gameObj.pos.x * Level.PTM_RATIO) + gameObj.rolledPos.x,
					(gameObj.pos.y * Level.PTM_RATIO) + gameObj.rolledPos.y,
					-gameObj.rolledPos.x, -gameObj.rolledPos.y,
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