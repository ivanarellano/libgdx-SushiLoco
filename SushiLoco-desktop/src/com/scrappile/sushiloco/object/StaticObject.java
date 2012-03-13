package com.scrappile.sushiloco.object;

import com.scrappile.sushiloco.Level;
import com.scrappile.sushiloco.ObjectRepresentation;
import com.scrappile.sushiloco.box2d.PhysicsObject;

public abstract class StaticObject extends PhysicsObject {
	public ObjectRepresentation objRep = new ObjectRepresentation();
	public boolean hidden;

	public void setPos(float screenPosX, float screenPosY, float angle) {
		body.setTransform(screenPosX, screenPosY, angle);
		setTexturePos(screenPosX, screenPosY);
	}

	public void setTexturePos(float relativeScreenOffsetX, float relativeScreenOffsetY) {
		if (body != null) {
			float x = (body.getPosition().x - objRep.halfWidth / Level.PTM_RATIO)
					- relativeScreenOffsetX / Level.PTM_RATIO;
			float y = (body.getPosition().y - objRep.halfHeight / Level.PTM_RATIO)
					- relativeScreenOffsetY / Level.PTM_RATIO;

			objRep.pos.set(x, y);
			objRep.pos.mul(Level.PTM_RATIO);
		}
	}

	protected void buildEdge(float x1, float x2, float y1, float y2) {
	}
}
