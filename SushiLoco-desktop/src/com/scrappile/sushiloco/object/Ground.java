package com.scrappile.sushiloco.object;

import com.scrappile.sushiloco.Level;
import com.scrappile.sushiloco.box2d.BodyFactory;

public class Ground extends StaticObject {
	float friction;

	public Ground(float screenPosX1, float screenPosY1, float screenPosX2,
			float screenPosY2, float friction) {
		this.type = Type.STATIC;
		this.friction = friction;
		buildEdge(screenPosX1, screenPosY1, screenPosX2, screenPosY2);
	}

	@Override
	protected void buildEdge(float screenPosX1, float screenPosY1,
			float screenPosX2, float screenPosY2) {
		float x1 = screenPosX1 / Level.PTM_RATIO;
		float y1 = screenPosY1 / Level.PTM_RATIO;
		float x2 = screenPosX2 / Level.PTM_RATIO;
		float y2 = screenPosY2 / Level.PTM_RATIO;

		body = BodyFactory.createEdge(x1, y1, x2, y2, friction);
		body.setUserData(this);
	}

}
