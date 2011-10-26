package com.tinyrender.rollemup.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;

public class JointFactory {
	static WeldJointDef wjd = new WeldJointDef();
	static RevoluteJointDef rjd = new RevoluteJointDef();
	static Vector2 anchor = new Vector2();

	public static Joint weld(Body bodyA, Body bodyB, float anchorX, float anchorY, World b2world) {
		anchor.set(anchorX, anchorY);
		wjd.initialize(bodyA, bodyB, anchor);
		return b2world.createJoint(wjd);
	}
	
	public static Joint weld(Body bodyA, Body bodyB, Vector2 vecAnchor, World b2world) {
		wjd.initialize(bodyA, bodyB, vecAnchor);
		return b2world.createJoint(wjd);
	}
	
	public static Joint revolute(Body bodyA, Body bodyB, float anchorX, float anchorY, World b2world) {
		anchor.set(anchorX, anchorY);
		rjd.initialize(bodyA, bodyB, anchor);
		return b2world.createJoint(rjd);
	}
	
	public static Joint revolute(Body bodyA, Body bodyB, Vector2 vecAnchor, World b2world) {
		rjd.initialize(bodyA, bodyB, vecAnchor);
		return b2world.createJoint(rjd);
	}
}
