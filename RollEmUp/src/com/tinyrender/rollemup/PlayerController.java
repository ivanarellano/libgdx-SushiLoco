package com.tinyrender.rollemup;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

public class PlayerController extends Controller {
	public Player player;
	
	public PlayerController(Player player) {
		this.player = player;
	}
	
	public void rollObject(GameObject other) {
		other.isRolled = true;
		player.objectsRolled.add(other);
		other.body.setAngularVelocity(0.0f);
		
		JointFactory.weld(player.body, other.body, new Vector2(player.pos.x, player.pos.y), player.world.b2world);
		
		Fixture otherFix = other.body.getFixtureList().get(0);
		Filter filter = new Filter();
		filter.maskBits = GameObject.CATEGORY_NO_COLLISION;
		otherFix.setSensor(true);
		otherFix.setFilterData(filter);
		
		player.body.resetMassData();
		
		player.isGrowing = true;
	}
}
