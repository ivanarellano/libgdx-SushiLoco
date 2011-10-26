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
	Filter filter;
	Fixture fixture;
	Shape.Type shapeType;
	Vector2 vecOffset;
	
	public PlayerController(Player player) {
		this.player = player;
		filter = new Filter();
		filter.maskBits = GameObject.MASK_NO_COLLISION;
		vecOffset = new Vector2();
	}
	
	public void rollObject(GameObject other) {
		other.isRolled = true;
		player.subObjects.add(other);
		other.body.setAngularVelocity(0.0f);
		
		JointFactory.weld(player.body, other.body, player.pos.x, player.pos.y, player.world);
		
		for (int i = 0; i < other.body.getFixtureList().size(); i++) {
			other.body.getFixtureList().get(i).setSensor(true);
			other.body.getFixtureList().get(i).setFilterData(filter);
		}
		
		player.body.resetMassData();
		
		player.isGrowing = true;
	}

	@Override
	public void jump(GameObject object, float velocity) {
		object.body.applyLinearImpulse(0, velocity * object.body.getMass()*1.2f, object.pos.x, object.pos.y);
	}
	
	public void scaleCircle(PhysicsObject object, float scale, float offsetX, float offsetY) {
		fixture = object.body.getFixtureList().get(0);
		shapeType = fixture.getType();
		
		if (shapeType == Shape.Type.Circle) {
			CircleShape shape = (CircleShape) fixture.getShape();
			float radius = shape.getRadius();
			
			vecOffset.set(offsetX, offsetY);
			radius *= scale;
			shape.setPosition(vecOffset);
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
