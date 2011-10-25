package com.tinyrender.rollemup.controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Shape;
import com.tinyrender.rollemup.Controller;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.box2d.JointFactory;
import com.tinyrender.rollemup.box2d.PhysicsObject;
import com.tinyrender.rollemup.object.Player;

public class PlayerController implements Controller {
	Player player;
	
	public PlayerController(Player player) {
		this.player = player;
	}
	
	public void rollObject(GameObject other) {
		other.isRolled = true;
		player.subObjects.add(other);
		other.body.setAngularVelocity(0.0f);
		
		JointFactory.weld(player.body, other.body, new Vector2(player.pos.x, player.pos.y), player.world);
		
		Filter filter = new Filter();
		filter.maskBits = GameObject.MASK_NO_COLLISION;
		for (Fixture otherFix : other.body.getFixtureList()) {
			otherFix.setSensor(true);
			otherFix.setFilterData(filter);
		}
		
		player.body.resetMassData();
		
		player.isGrowing = true;
	}

	@Override
	public void jump(GameObject object, float velocity) {
		object.body.applyLinearImpulse(0, velocity * object.body.getMass()*1.2f, object.pos.x, object.pos.y);
	}
	
	public void scaleCircle(PhysicsObject object, float scale, Vector2 posOffset) {
		Fixture fixture = object.body.getFixtureList().get(0);
		Shape.Type shapeType = fixture.getType();
		
		if (shapeType == Shape.Type.Circle) {
			CircleShape shape = (CircleShape) fixture.getShape();
			float radius = shape.getRadius();
			
			radius *= scale;
			shape.setPosition(posOffset);
			shape.setRadius(radius);
		}
	}
	
	public void keyDown(int keyCode) {
		if (keyCode == Keys.SPACE)
			player.isJumping = true;
	}
	
	public void keyUp(int keyCode) {
		if (keyCode == Keys.SPACE)
			player.isJumping = false;
	}
	
	public void touchDown() {
		player.isJumping = true;
	}
	
	public void touchUp() {
		player.isJumping = false;
	}
}
