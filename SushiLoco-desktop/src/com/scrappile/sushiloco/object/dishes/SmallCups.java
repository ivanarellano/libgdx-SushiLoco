package com.scrappile.sushiloco.object.dishes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.GameObject;
import com.scrappile.sushiloco.Level;
import com.scrappile.sushiloco.box2d.BodyFactory;
import com.scrappile.sushiloco.box2d.PhysicsObject.Type;

public class SmallCups {
	Vector2 cup1_poly[] = {
			new Vector2(-44.5f / Level.PTM_RATIO, 46.0f / Level.PTM_RATIO),
			new Vector2(-42.4f / Level.PTM_RATIO, -48.4f / Level.PTM_RATIO),
			new Vector2(43.1f / Level.PTM_RATIO, -48.1f / Level.PTM_RATIO),
			new Vector2(42.8f / Level.PTM_RATIO, 45.6f / Level.PTM_RATIO),
			new Vector2(30.4f / Level.PTM_RATIO, 52.3f / Level.PTM_RATIO),
			new Vector2(-30.1f / Level.PTM_RATIO, 51.3f / Level.PTM_RATIO)
		};
	
	Vector2 cup2_poly[] = {
			new Vector2(-48.8f / Level.PTM_RATIO, 14.5f / Level.PTM_RATIO),
			new Vector2(-30.4f / Level.PTM_RATIO, -39.2f / Level.PTM_RATIO),
			new Vector2(32.9f / Level.PTM_RATIO, -38.2f / Level.PTM_RATIO),
			new Vector2(49.5f / Level.PTM_RATIO, 13.8f / Level.PTM_RATIO),
			new Vector2(38.9f / Level.PTM_RATIO, 39.6f / Level.PTM_RATIO),
			new Vector2(-38.5f / Level.PTM_RATIO, 38.9f / Level.PTM_RATIO)
		};
	
	public Array<Vector2[]> verts = new Array<Vector2[]>(1);
	
	public GameObject build1(float screenPosX, float screenPosY) {
		screenPosX /= Level.PTM_RATIO;
		screenPosY /= Level.PTM_RATIO;
		GameObject cup1 = new GameObject();
		
		cup1.level = 2;
		cup1.points = 6;
		cup1.type = Type.ROLLABLE;

		cup1.objRep.setTexture(Assets.atlas.findRegion("cup1"));
		
		screenPosY += cup1.objRep.halfHeight / Level.PTM_RATIO;
				
		verts.clear();
		verts.add(cup1_poly);
		cup1.body = BodyFactory.createPoly(verts, screenPosX, screenPosY,
				1.1f, 1.2f, BodyType.DynamicBody, cup1);
		
		return cup1;
	}
	
	public GameObject build2(float screenPosX, float screenPosY) {
		screenPosX /= Level.PTM_RATIO;
		screenPosY /= Level.PTM_RATIO;
		GameObject cup2 = new GameObject();
		
		cup2.level = 2;
		cup2.points = 6;
		cup2.type = Type.ROLLABLE;

		cup2.objRep.setTexture(Assets.atlas.findRegion("cup2"));
		
		screenPosY += cup2.objRep.halfHeight / Level.PTM_RATIO;
				
		verts.clear();
		verts.add(cup2_poly);
		cup2.body = BodyFactory.createPoly(verts, screenPosX, screenPosY,
				1.1f, 1.2f, BodyType.DynamicBody, cup2);
		
		return cup2;
	}
}
