package com.tinyrender.rollemup.level;

import java.util.ArrayList;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tinyrender.rollemup.BoxSushi;
import com.tinyrender.rollemup.CircleSushi;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.Player;
import com.tinyrender.rollemup.Utils;

public class TestLevel extends Level {
	Player player;
	
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	@Override
	public void create() {
		player = new Player(b2world);
				
		Utils.createEdge(-100.0f, 0.0f, 100.0f, 0.0f, 0.4f, b2world);
		
		Utils.createBox(BodyType.StaticBody, -60.0f, 9.0f, 10.0f, 1.0f, 0.0f, b2world);
		Utils.createBox(BodyType.StaticBody, -20.0f, 9.0f, 10.0f, 1.0f, 0.0f, b2world);
		Utils.createBox(BodyType.StaticBody, 20.0f, 9.0f, 10.0f, 1.0f, 0.0f, b2world);
		Utils.createBox(BodyType.StaticBody, 60.0f, 9.0f, 10.0f, 1.0f, 0.0f, b2world);
		
		objects.add(new BoxSushi((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, 2.0f, b2world));
		objects.add(new BoxSushi((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, 2.0f, b2world));
		objects.add(new BoxSushi((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, 2.0f, b2world));
		objects.add(new BoxSushi((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, 2.0f, b2world));
		
		for (int i = 0; i < 10; i++) {
			objects.add(new CircleSushi((float)Math.random() * 10f - (float)Math.random() * 10f, 
										(float)Math.random() * 10 + 6,
										(float)Math.random() * 0.9f + 0.3f,
										3.0f, (float)(Math.random() * 2 * Math.PI), b2world));
		}
	}
	
	@Override
	public void update(float deltaTime) {
		cam.position.set(player.body.getPosition().x, player.body.getPosition().y, 0); // cheap hack
		player.update();
		
		physicsStep(deltaTime);
	}

	@Override
	public void render(float deltaTime) {
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
