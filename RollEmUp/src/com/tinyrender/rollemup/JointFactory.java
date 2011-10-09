package com.tinyrender.rollemup;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;

public class JointFactory {
	public static void weld(Body bodyA, Body bodyB, Vector2 anchorA, World b2world) {
		WeldJointDef wjd = new WeldJointDef();
		wjd.initialize(bodyA, bodyB, anchorA);
		b2world.createJoint(wjd);
	}
	
	public static void revolute(Body bodyA, Body bodyB, Vector2 anchorA, World b2world) {
		RevoluteJointDef rjd = new RevoluteJointDef();
		rjd.initialize(bodyA, bodyB, anchorA);
		b2world.createJoint(rjd);
	}
}
