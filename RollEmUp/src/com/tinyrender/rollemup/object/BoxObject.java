package com.tinyrender.rollemup.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.box2d.BodyFactory;

public class BoxObject extends GameObject {
	public BoxObject(World world) {
		super(world);
	}
	
	public BoxObject(TextureRegion texture, float x, float y, float density, int size,
			int score, GameObjectType gameObjType, World world) {
		super(world);
		
		this.score = score;
		this.size = size;
		this.gameObjType = gameObjType;
		objRep.setTexture(texture);
		
		float hx = objRep.width / 2.0f / Level.PTM_RATIO;
		float hy = objRep.height / 2.0f / Level.PTM_RATIO;
		
		body = BodyFactory.createBox(x, y, hx, hy, density, BodyType.DynamicBody, world);
		body.setUserData(this);
	}
}
