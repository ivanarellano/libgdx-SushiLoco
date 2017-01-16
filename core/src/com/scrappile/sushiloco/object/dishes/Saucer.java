package com.scrappile.sushiloco.object.dishes;

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

public class Saucer implements ObjectFactory {

	private Array<Vector2[]> verts = new Array<Vector2[]>(3);
	private TextureRegion texture = Assets.atlas.findRegion("saucer");
	private Vector2 body[] = {
			new Vector2(-72.3f / Level.PTM_RATIO, 8.0f / Level.PTM_RATIO),
			new Vector2(-48.6f / Level.PTM_RATIO, -0.5f / Level.PTM_RATIO),
			new Vector2(48.6f / Level.PTM_RATIO, -0.5f / Level.PTM_RATIO),
			new Vector2(72.3f / Level.PTM_RATIO, 8.0f / Level.PTM_RATIO)
		};
	private Vector2 leg_l[] = {
			new Vector2(-48.2f / Level.PTM_RATIO, -0.8f / Level.PTM_RATIO),
			new Vector2(-45.7f / Level.PTM_RATIO, -14.2f / Level.PTM_RATIO),
			new Vector2(-27.8f / Level.PTM_RATIO, -14.2f / Level.PTM_RATIO),
			new Vector2(-21.5f / Level.PTM_RATIO, -0.8f / Level.PTM_RATIO)
		};
	private Vector2 leg_r[] = {
			new Vector2(21.5f / Level.PTM_RATIO, -0.8f / Level.PTM_RATIO),
			new Vector2(27.8f / Level.PTM_RATIO, -14.2f / Level.PTM_RATIO),
			new Vector2(45.7f / Level.PTM_RATIO, -14.2f / Level.PTM_RATIO),
			new Vector2(48.2f / Level.PTM_RATIO, -0.8f / Level.PTM_RATIO)
		};
	
	Saucer() {
		verts.add(body);
		verts.add(leg_l);
		verts.add(leg_r);
	}

	@Override
	public GameObject build(float screenPosX, float screenPosY) {
		screenPosX /= Level.PTM_RATIO;
		screenPosY /= Level.PTM_RATIO;
		GameObject saucer = new GameObject();
		
		saucer.level = 2;
		saucer.points = 6;
		saucer.type = Type.ROLLABLE;

		saucer.objRep.setTexture(texture);
		
		screenPosY += saucer.objRep.getHalfHeight() / Level.PTM_RATIO;
				
		saucer.body = BodyFactory.createPoly(verts, screenPosX, screenPosY, 1.1f, 1.2f, BodyType.DynamicBody, saucer);
		
		return saucer;
	}
}
