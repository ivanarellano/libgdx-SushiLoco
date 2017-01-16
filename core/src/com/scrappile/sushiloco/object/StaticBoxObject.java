package com.scrappile.sushiloco.object;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.scrappile.sushiloco.Level;
import com.scrappile.sushiloco.box2d.BodyFactory;

public class StaticBoxObject extends StaticObject {

	public StaticBoxObject() {
		type = Type.STATIC;
	}

	/**
	 * Builds a static box2d box.
	 */
	public void build(float screenPosX, float screenPosY, float screenWidth, float screenHeight) {
		screenPosX /= Level.PTM_RATIO;
		screenPosY /= Level.PTM_RATIO;
		float halfWidth = screenWidth / 2.0f / Level.PTM_RATIO;
		float halfHeight = screenHeight / 2.0f / Level.PTM_RATIO;

		body = BodyFactory.createBox(screenPosX, screenPosY, halfWidth, halfHeight, 0.0f, 1.0f, false, BodyType.StaticBody, this);
	}
}
