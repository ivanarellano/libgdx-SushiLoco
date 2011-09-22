package com.tinyrender.rollemup.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.Player;

public class TestLevel extends Level {
	Player player;
	
	public TestLevel() {
		player = new Player(b2world);
	}
	
	@Override
	public void create() {
		createEdge(-100.0f, 0, 100.0f, 0, 0.4f);
		
		Vector2 anchorA = new Vector2(player.body.getPosition().x, player.body.getPosition().y);
		RevoluteJointDef djd = new RevoluteJointDef();
		
		djd.initialize(player.body, player.groundSensor, anchorA);
		b2world.createJoint(djd);
		
		createBox(BodyType.StaticBody, -60.0f, 9.0f, 10.0f, 1.0f);
		createBox(BodyType.StaticBody, -20.0f, 9.0f, 10.0f, 1.0f);
		createBox(BodyType.StaticBody, 20.0f, 9.0f, 10.0f, 1.0f);
		createBox(BodyType.StaticBody, 60.0f, 9.0f, 10.0f, 1.0f);
		
		for (int i = 0; i < 10; i++) {
			Body circle = createCircle(BodyType.DynamicBody, (float)Math.random() * 0.9f + 0.3f, 3);
			circle.setTransform((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, (float)(Math.random() * 2 * Math.PI));
		}
	}
	
	@Override
	public void update(float deltaTime) {
		physicsStep(deltaTime);
		cam.position.set(player.body.getPosition().x, player.body.getPosition().y, 0); // cheap hack
		player.update();
	}

	@Override
	public void render(float deltaTime) {
	}
	
	private Body createBox(BodyType type, float x, float y, float hx, float hy) {
		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = type;
		Body body = b2world.createBody(bd);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(hx, hy);
		body.createFixture(shape, 0);
		shape.dispose();
		
		return body;
	}

	private Body createEdge(float x1, float y1, float x2, float y2, float friction) {
        BodyDef bd = new BodyDef();
        Body body = b2world.createBody(bd);

        EdgeShape shape = new EdgeShape();
        shape.set(x1, y1, x2, y2);
        
        FixtureDef fd = new FixtureDef();
        fd.friction = friction;
        fd.shape = shape;
        body.createFixture(fd);
        
        shape.dispose();
        
		return body;
	}
	
	private Body createCircle(BodyType type, float radius, float density) {
		BodyDef bd = new BodyDef();
		bd.type = type;
		Body body = b2world.createBody(bd);
 
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		body.createFixture(shape, density);
		shape.dispose();
 
		return body;
	}

	@Override
	public void touchDown() {
		player.isJumping = true;
	}

	@Override
	public void touchUp() {
		player.isJumping = false;
	}
}
