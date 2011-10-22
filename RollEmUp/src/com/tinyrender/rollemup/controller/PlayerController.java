package com.tinyrender.rollemup.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.tinyrender.rollemup.Controller;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.box2d.JointFactory;
import com.tinyrender.rollemup.object.Player;

public class PlayerController extends Controller {
	public Player player;
	
	public PlayerController(Player player) {
		this.player = player;
	}
	
	public void rollObject(GameObject other) {
		other.isRolled = true;
		player.subObjects.add(other);
		other.body.setAngularVelocity(0.0f);
		
		JointFactory.weld(player.body, other.body, new Vector2(player.pos.x, player.pos.y), player.world);
		
		Fixture otherFix = other.body.getFixtureList().get(0);
		Filter filter = new Filter();
		filter.maskBits = GameObject.CATEGORY_NO_COLLISION;
		otherFix.setSensor(true);
		otherFix.setFilterData(filter);
		
		player.body.resetMassData();
		
		player.isGrowing = true;
	}
	
	public void keyDown(int keyCode) {
		if (keyCode == Keys.SPACE)
			player.isJumping = true;
		Gdx.app.log("keyDown", "in pc");
	}
	
	public void keyUp(int keyCode) {
		if (keyCode == Keys.SPACE)
			player.isJumping = false;
		Gdx.app.log("keyUp", "in pc");
	}
	
	public void touchDown() {
		player.isJumping = true;
		Gdx.app.log("touchDown", "in pc");
	}
	
	public void touchUp() {
		player.isJumping = false;
		Gdx.app.log("touchUp", "in pc");
	}
}
