package com.tinyrender.rollemup.level;

import java.util.ArrayList;

import com.tinyrender.rollemup.Box;
import com.tinyrender.rollemup.BoxSushi;
import com.tinyrender.rollemup.CircleSushi;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Ground;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.Player;

public class TestLevel extends Level {
	Player player;	
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	@Override
	public void create() {
		player = new Player(world);
		objects.add(player);
		
		new Ground(-100.0f, 0.0f, 100.0f, 0.0f, 0.4f, world);
		
		// floating platforms
		float distX = -60.0f;
		for(int i = 0; i < 3; i++) {
			new Box(distX, 9.0f, 10.0f, 1.0f, 0.0f, world);
			distX += 40.0f;
		}
		
		// boxes
		objects.add(new BoxSushi((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, 0.1f, world));
		objects.add(new BoxSushi((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, 0.1f, world));
		objects.add(new BoxSushi((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, 0.1f, world));
		objects.add(new BoxSushi((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, 0.1f, world));
		
		// circles
		for (int i = 0; i < 10; i++) {
			objects.add(new CircleSushi((float)Math.random() * 50f - (float)Math.random() * 50f, 
										(float)Math.random() * 10 + 6,
										(float)Math.random() * 1.0f + 0.1f,
										0.2f, (float)(Math.random() * 2 * Math.PI), world));
		}
	}
	
	@Override
	public void update(float deltaTime) {
		cam.position.set(player.body.getPosition().x, player.body.getPosition().y, 0); // cheap hack
		
		for (GameObject obj : objects)
			obj.update();
		
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
