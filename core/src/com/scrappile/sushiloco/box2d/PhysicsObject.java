package com.scrappile.sushiloco.box2d;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;

public class PhysicsObject {
    public Type type;
    public ContactResolver contactResolver;
    public Body body;
    public Joint joint;
    public boolean isDead;

    public PhysicsObject() {
        contactResolver = new ContactResolver() {
            @Override
            public void enterContact(PhysicsObject collidesWith) {
            }

            @Override
            public void leaveContact(PhysicsObject leftCollisionWith) {
            }
        };
    }

    public void update() {
    }

    public static enum Type {
        PLAYER, ROLLABLE, STATIC
	}

    public interface ContactResolver {
		public void enterContact(PhysicsObject collidesWith);
		public void leaveContact(PhysicsObject leftCollisionWith);
	}
	
	public static class Category {
        public final static short PLAYER = 0x0001;
        public final static short OBJECT = 0x0002;
        public final static short SUB_OBJECT = 0x0004;
        public final static short FRUSTRUM = 0x0006;
    }

    public static class Mask {
        public final static short COLLIDE_NONE = 0;
        public final static short COLLIDE_ALL = -1;
        public final static short PLAYER = Category.OBJECT | Category.SUB_OBJECT;
        public final static short OBJECT = Mask.COLLIDE_ALL & ~Category.SUB_OBJECT;
        public final static short SUB_OBJECT = Category.PLAYER | Category.OBJECT;
    }
}
