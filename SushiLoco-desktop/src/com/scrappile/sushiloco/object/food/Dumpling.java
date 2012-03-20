package com.scrappile.sushiloco.object.food;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.GameObject;
import com.scrappile.sushiloco.Level;
import com.scrappile.sushiloco.box2d.BodyFactory;
import com.scrappile.sushiloco.box2d.PhysicsObject.Type;
import com.scrappile.sushiloco.object.ObjectFactory;

public class Dumpling implements ObjectFactory {
	Vector2 poly1[] = {
			new Vector2(-5.8f / Level.PTM_RATIO, 53.2f / Level.PTM_RATIO),
			new Vector2(-53.6f / Level.PTM_RATIO, 32.7f / Level.PTM_RATIO),
			new Vector2(-70.9f / Level.PTM_RATIO, -9.0f / Level.PTM_RATIO),
			new Vector2(-54.3f / Level.PTM_RATIO, -44.4f / Level.PTM_RATIO),
			new Vector2(-15.0f / Level.PTM_RATIO, -55.7f / Level.PTM_RATIO)
		};
	
	Vector2 poly2[] = {
			new Vector2(-15.0f / Level.PTM_RATIO, -55.7f / Level.PTM_RATIO),
			new Vector2(29.9f / Level.PTM_RATIO, -53.9f / Level.PTM_RATIO),
			new Vector2(62.0f / Level.PTM_RATIO, -38.7f / Level.PTM_RATIO),
			new Vector2(69.5f / Level.PTM_RATIO, 2.3f / Level.PTM_RATIO),
			new Vector2(40.5f / Level.PTM_RATIO, 39.4f / Level.PTM_RATIO),
			new Vector2(-5.8f / Level.PTM_RATIO, 53.2f / Level.PTM_RATIO)
		};
	
	public Array<Vector2[]> verts = new Array<Vector2[]>(2);
	public TextureRegion texture = Assets.atlas.findRegion("dumpling");
	
	public Dumpling() {
		verts.add(poly1);
		verts.add(poly2);
	}
	
	@Override
	public GameObject build(float screenPosX, float screenPosY) {
		screenPosX /= Level.PTM_RATIO;
		screenPosY /= Level.PTM_RATIO;
		GameObject dumplingObj = new GameObject();
		
		dumplingObj.level = 3;
		dumplingObj.points = 9;
		dumplingObj.type = Type.ROLLABLE;

		dumplingObj.objRep.setTexture(texture);
		
		screenPosY += dumplingObj.objRep.halfHeight / Level.PTM_RATIO;
				
		dumplingObj.body = BodyFactory.createPoly(verts, screenPosX, screenPosY,
				1.2f, 1.0f, BodyType.DynamicBody, dumplingObj);
		
		return dumplingObj;
	}

}
