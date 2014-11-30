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

public class EggNigiri implements ObjectFactory {
	Vector2 poly1[] = {
			new Vector2(-44.0f / Level.PTM_RATIO, 6.9f / Level.PTM_RATIO),
			new Vector2(-44.0f / Level.PTM_RATIO, -20.0f / Level.PTM_RATIO),
			new Vector2(16.4f / Level.PTM_RATIO, -28.8f / Level.PTM_RATIO),
			new Vector2(48.6f / Level.PTM_RATIO, -15.7f / Level.PTM_RATIO),
			new Vector2(49.7f / Level.PTM_RATIO, 7.2f / Level.PTM_RATIO)
		};
	
	Vector2 poly2[] = {
			new Vector2(49.7f / Level.PTM_RATIO, 7.2f / Level.PTM_RATIO),
			new Vector2(61.3f / Level.PTM_RATIO, 16.8f / Level.PTM_RATIO),
			new Vector2(-34.1f / Level.PTM_RATIO, 29.2f / Level.PTM_RATIO),
			new Vector2(-60.6f / Level.PTM_RATIO, 21.4f / Level.PTM_RATIO),
			new Vector2(-58.5f / Level.PTM_RATIO, 10.4f / Level.PTM_RATIO),
			new Vector2(-44.0f / Level.PTM_RATIO, 6.9f / Level.PTM_RATIO)
		};
	
	public Array<Vector2[]> verts = new Array<Vector2[]>(2);
	public TextureRegion texture = Assets.atlas.findRegion("eggnigiri");
	
	public EggNigiri() {
		verts.add(poly1);
		verts.add(poly2);
	}

	@Override
	public GameObject build(float screenPosX, float screenPosY) {
		screenPosX /= Level.PTM_RATIO;
		screenPosY /= Level.PTM_RATIO;
		GameObject eggNigiri = new GameObject();
		
		eggNigiri.level = 3;
		eggNigiri.points = 9;
		eggNigiri.type = Type.ROLLABLE;

		eggNigiri.objRep.setTexture(texture);
		
		screenPosY += eggNigiri.objRep.halfHeight / Level.PTM_RATIO;
				
		eggNigiri.body = BodyFactory.createPoly(verts, screenPosX, screenPosY,
				1.1f, 1.2f, BodyType.DynamicBody, eggNigiri);
		
		return eggNigiri;
	}

}
