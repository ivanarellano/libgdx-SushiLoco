package com.tinyrender.rollemup.controller;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.Controller;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.box2d.JointFactory;
import com.tinyrender.rollemup.box2d.PhysicsObject;
import com.tinyrender.rollemup.object.Player;

public class PlayerController implements Controller {
	Player player;
	Fixture fixture;
	Shape.Type shapeType;
	Filter filter = new Filter();
	Vector2 vecPosOffset = new Vector2();
	MassData massData = new MassData();
	
	public PlayerController(Player player) {
		this.player = player;
		filter.maskBits = PhysicsObject.MASK_NO_COLLISION;
	}
	
	public void rollObject(GameObject other, World world) {
		// Move rolled object from level's list to player's
		player.level.objects.removeValue(other, true); // TODO: O(N) linear
		player.subObj.add(other);
		
		// Destroy rolled object's joint
		if (other.joint != null)
			world.destroyJoint(other.joint);
		
		// Connect rolled object with player
		other.joint = JointFactory.weld(player.body, other.body, 
				player.pos.x, player.pos.y, player.world);
		
		// Set rolled object's collision detection to none
		for (int i = 0; i < other.body.getFixtureList().size(); i++)
			other.body.getFixtureList().get(i).setFilterData(filter);

		other.rolled = true;
		
		player.body.resetMassData();
	}

	@Override
	public void jump(GameObject object, float velocity) {
		object.body.applyLinearImpulse(0, velocity, object.pos.x, object.pos.y);
	}
	
	public void scaleCircle(PhysicsObject object, float scale, float offsetX, float offsetY) {
		fixture = object.body.getFixtureList().get(0);
		shapeType = fixture.getType();
		
		if (shapeType == Shape.Type.Circle) {
			CircleShape shape = (CircleShape) fixture.getShape();
			float radius = shape.getRadius();
			
			vecPosOffset.set(offsetX, offsetY);
			radius *= scale;
			shape.setPosition(vecPosOffset);
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
