package com.scrappile.sushiloco.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.scrappile.sushiloco.GameObject;
import com.scrappile.sushiloco.Level;
import com.scrappile.sushiloco.box2d.BodyFactory;

public class CircleObject extends GameObject {
	public CircleObject(World world) {
		super(world);
	}
	
	public CircleObject(TextureRegion texture, float x, float y, float angle, float density, 
			int level, int points, boolean isSensor, boolean doUpdate, Type type, World world) {
		super(world);
		
		this.points = points;
		this.level = level;
		this.type = type;

		objRep.setTexture(texture);
		
		float radius = objRep.width / 2.0f / Level.PTM_RATIO;
		
		body = BodyFactory.createCircle(x, y, radius, density, angle, 1.0f, isSensor, BodyType.DynamicBody, world);
		body.setUserData(this);
	}
}
