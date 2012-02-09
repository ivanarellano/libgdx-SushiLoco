package com.scrappile.sushiloco.object;

import com.badlogic.gdx.physics.box2d.World;
import com.scrappile.sushiloco.GameObject;
import com.scrappile.sushiloco.box2d.BodyFactory;

public class Ground extends GameObject {

	public Ground(World world) {
		super(world);
	}
	
	public Ground(float x1, float y1, float x2, float y2, float friction, World world) {
		super(world);
		
		this.type = Type.STATIC;
		
		body = BodyFactory.createEdge(x1, y1, x2, y2, friction, world);
		body.setUserData(this);
	}
	
	@Override public void update() { }
}
