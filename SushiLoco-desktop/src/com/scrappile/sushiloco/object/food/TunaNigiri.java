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

public class TunaNigiri implements ObjectFactory {
	Vector2 poly1[] = {
			new Vector2(-48.6f / Level.PTM_RATIO, -19.3f / Level.PTM_RATIO),
			new Vector2(14.7f / Level.PTM_RATIO, -27.4f / Level.PTM_RATIO),
			new Vector2(50.7f / Level.PTM_RATIO, -10.1f / Level.PTM_RATIO),
			new Vector2(29.5f / Level.PTM_RATIO, 21.0f / Level.PTM_RATIO),
			new Vector2(-9.0f / Level.PTM_RATIO, 28.8f / Level.PTM_RATIO),
			new Vector2(-44.7f / Level.PTM_RATIO, 7.6f / Level.PTM_RATIO)
		};
	
	public Array<Vector2[]> verts = new Array<Vector2[]>(1);
	public TextureRegion texture = Assets.atlas.findRegion("tunanigiri");
	
	public TunaNigiri() {
		verts.add(poly1);
	}

	@Override
	public GameObject build(float screenPosX, float screenPosY) {
		screenPosX /= Level.PTM_RATIO;
		screenPosY /= Level.PTM_RATIO;
		GameObject tunaNigiri = new GameObject();
		
		tunaNigiri.level = 1;
		tunaNigiri.points = 7;
		tunaNigiri.type = Type.ROLLABLE;

		tunaNigiri.objRep.setTexture(texture);
		
		screenPosY += tunaNigiri.objRep.halfHeight / Level.PTM_RATIO;
				
		tunaNigiri.body = BodyFactory.createPoly(verts, screenPosX, screenPosY,
				1.1f, 1.2f, BodyType.DynamicBody, tunaNigiri);
		
		return tunaNigiri;
	}	
}
