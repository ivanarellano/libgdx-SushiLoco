package com.scrappile.sushiloco.object;

import com.scrappile.sushiloco.box2d.BodyFactory;

public class Ground extends StaticObject {
	float friction;
	
	public Ground(float x1, float y1, float x2, float y2, float friction) {		
		this.type = Type.STATIC;
		this.friction = friction;
		buildEdge(x1, x2, y1, y2);
	}
	
	@Override
	protected void buildEdge(float x1, float x2, float y1, float y2) {
		body = BodyFactory.createEdge(x1, y1, x2, y2, friction);
		body.setUserData(this);
	}
	
}
