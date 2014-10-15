package com.scrappile.sushiloco.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;

public class JointFactory {
	static WeldJointDef wjd = new WeldJointDef();
	static RevoluteJointDef rjd = new RevoluteJointDef();
	static Vector2 anchor = new Vector2();

	public static Joint weld(Body bodyA, Body bodyB, float anchorX, float anchorY) {
		anchor.set(anchorX, anchorY);
		wjd.initialize(bodyA, bodyB, anchor);
		return PhysicsWorld.b2world.createJoint(wjd);
	}
	
	public static Joint weld(Body bodyA, Body bodyB, Vector2 vecAnchor) {
		wjd.initialize(bodyA, bodyB, vecAnchor);
		return PhysicsWorld.b2world.createJoint(wjd);
	}
	
	public static Joint revolute(Body bodyA, Body bodyB, float anchorX, float anchorY) {
		anchor.set(anchorX, anchorY);
		rjd.initialize(bodyA, bodyB, anchor);
		return PhysicsWorld.b2world.createJoint(rjd);
	}
	
	public static Joint revolute(Body bodyA, Body bodyB, Vector2 vecAnchor) {
		rjd.initialize(bodyA, bodyB, vecAnchor);
		return PhysicsWorld.b2world.createJoint(rjd);
	}
}
