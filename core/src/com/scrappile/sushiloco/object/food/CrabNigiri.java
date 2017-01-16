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

public class CrabNigiri implements ObjectFactory {

	private Array<Vector2[]> verts = new Array<Vector2[]>(2);
	private TextureRegion texture = Assets.atlas.findRegion("crabnigiri");
	private Vector2 poly1[] = {
			new Vector2(-51.6f / Level.PTM_RATIO, 27.0f / Level.PTM_RATIO),
			new Vector2(-48.4f / Level.PTM_RATIO, -27.8f / Level.PTM_RATIO),
			new Vector2(14.5f / Level.PTM_RATIO, -34.5f / Level.PTM_RATIO),
			new Vector2(50.6f / Level.PTM_RATIO, -17.1f / Level.PTM_RATIO),
			new Vector2(45.3f / Level.PTM_RATIO, 28.5f / Level.PTM_RATIO),
			new Vector2(-41.7f / Level.PTM_RATIO, 36.2f / Level.PTM_RATIO)
		};
	
	CrabNigiri() {
		verts.add(poly1);
	}
	
	@Override
	public GameObject build(float screenPosX, float screenPosY) {
		screenPosX /= Level.PTM_RATIO;
		screenPosY /= Level.PTM_RATIO;
		GameObject crabNigiri = new GameObject();
		
		crabNigiri.level = 3;
		crabNigiri.points = 9;
		crabNigiri.type = Type.ROLLABLE;

		crabNigiri.objRep.setTexture(texture);
		
		screenPosY += crabNigiri.objRep.getHalfHeight() / Level.PTM_RATIO;
				
		crabNigiri.body = BodyFactory.createPoly(verts, screenPosX, screenPosY, 1.1f, 1.2f, BodyType.DynamicBody, crabNigiri);
		
		return crabNigiri;
	}

}
