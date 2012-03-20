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

public class MackarelNigiri implements ObjectFactory {
	Vector2 poly1[] = {
			new Vector2(-57.6f / Level.PTM_RATIO, 6.7f / Level.PTM_RATIO),
			new Vector2(-46.0f / Level.PTM_RATIO, -17.7f / Level.PTM_RATIO),
			new Vector2(16.6f / Level.PTM_RATIO, -24.7f / Level.PTM_RATIO),
			new Vector2(59.4f / Level.PTM_RATIO, 1.1f / Level.PTM_RATIO),
			new Vector2(9.5f / Level.PTM_RATIO, 26.2f / Level.PTM_RATIO),
			new Vector2(-24.7f / Level.PTM_RATIO, 24.7f / Level.PTM_RATIO)
		};
	
	public Array<Vector2[]> verts = new Array<Vector2[]>(1);
	public TextureRegion texture = Assets.atlas.findRegion("mackarelnigiri");
	
	MackarelNigiri() {
		verts.add(poly1);
	}
	
	@Override
	public GameObject build(float screenPosX, float screenPosY) {
		screenPosX /= Level.PTM_RATIO;
		screenPosY /= Level.PTM_RATIO;
		GameObject mackarelNigiri = new GameObject();
		
		mackarelNigiri.level = 3;
		mackarelNigiri.points = 9;
		mackarelNigiri.type = Type.ROLLABLE;

		mackarelNigiri.objRep.setTexture(texture);
		
		screenPosY += mackarelNigiri.objRep.halfHeight / Level.PTM_RATIO;
				
		mackarelNigiri.body = BodyFactory.createPoly(verts, screenPosX, screenPosY,
				1.1f, 1.2f, BodyType.DynamicBody, mackarelNigiri);
		
		return mackarelNigiri;
	}

}
