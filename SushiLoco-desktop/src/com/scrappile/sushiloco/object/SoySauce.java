package com.scrappile.sushiloco.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.GameObject;
import com.scrappile.sushiloco.Level;
import com.scrappile.sushiloco.ObjectFactory;
import com.scrappile.sushiloco.box2d.BodyFactory;
import com.scrappile.sushiloco.box2d.PhysicsObject.Type;

public class SoySauce implements ObjectFactory {
	Vector2 poly1[] = {
		new Vector2(60.5f / Level.PTM_RATIO, 185.3f / Level.PTM_RATIO),		// 1
		new Vector2(-73.2f / Level.PTM_RATIO, 186.0f / Level.PTM_RATIO),	// 2
		new Vector2(-54.1f / Level.PTM_RATIO, 0.0f / Level.PTM_RATIO),		// 4
		new Vector2(54.8f / Level.PTM_RATIO, -1.4f / Level.PTM_RATIO)		// 10
		//new Vector2(-54.1f / Level.PTM_RATIO, 118.1f / Level.PTM_RATIO),	// 3
		//new Vector2(54.1f / Level.PTM_RATIO, 116.7f / Level.PTM_RATIO)	// 11
	};
	/*
	Vector2 poly2[] = {
		new Vector2(54.1f / Level.PTM_RATIO, 116.7f / Level.PTM_RATIO),		// 11
		new Vector2(-54.1f / Level.PTM_RATIO, 118.1f / Level.PTM_RATIO),	// 3
		new Vector2(-54.1f / Level.PTM_RATIO, 0.0f / Level.PTM_RATIO),		// 4
		new Vector2(54.8f / Level.PTM_RATIO, -1.4f / Level.PTM_RATIO)		// 10
	};
	*/
	Vector2 poly3[] = {
		new Vector2(54.8f / Level.PTM_RATIO, -1.4f / Level.PTM_RATIO),		// 10
		new Vector2(-54.1f / Level.PTM_RATIO, 0.0f / Level.PTM_RATIO),		// 4
		new Vector2(-76.7f / Level.PTM_RATIO, -181.0f / Level.PTM_RATIO),	// 6
		new Vector2(79.5f / Level.PTM_RATIO, -174.7f / Level.PTM_RATIO)		// 8
		//new Vector2(-71.8f / Level.PTM_RATIO, -88.4f / Level.PTM_RATIO),	// 5
		//new Vector2(70.4f / Level.PTM_RATIO, -86.3f / Level.PTM_RATIO)	// 9
	};
	/*	
	Vector2 poly4[] = {
		new Vector2(70.4f / Level.PTM_RATIO, -86.3f / Level.PTM_RATIO),		// 9
		new Vector2(-71.8f / Level.PTM_RATIO, -88.4f / Level.PTM_RATIO),	// 5
		new Vector2(-76.7f / Level.PTM_RATIO, -181.0f / Level.PTM_RATIO),	// 6
		new Vector2(79.5f / Level.PTM_RATIO, -174.7f / Level.PTM_RATIO)		// 8
	};
	*/
	
	public Array<Vector2[]> verts = new Array<Vector2[]>(2);
	public TextureRegion texture = Assets.atlas.findRegion("soy");
	
	public SoySauce() {		
		verts.add(poly1);
		//verts.add(poly2);
		verts.add(poly3);
		//verts.add(poly4);		
	}

	@Override
	public GameObject build(float screenPosX, float screenPosY) {
		screenPosX /= Level.PTM_RATIO;
		screenPosY /= Level.PTM_RATIO;
		GameObject soyObj = new GameObject();
		
		soyObj.level = 3;
		soyObj.points = 9;
		soyObj.type = Type.ROLLABLE;

		soyObj.objRep.setTexture(texture);
		
		screenPosY += soyObj.objRep.texture.getRegionHeight() / 2.0f / Level.PTM_RATIO;
				
		soyObj.body = BodyFactory.createPoly(verts, screenPosX, screenPosY,
				1.5f, 1.0f, BodyType.DynamicBody, soyObj);
						
		return soyObj;
	}
}
