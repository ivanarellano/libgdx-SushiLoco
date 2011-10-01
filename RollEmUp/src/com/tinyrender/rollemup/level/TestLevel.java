package com.tinyrender.rollemup.level;

import com.badlogic.gdx.Input.Keys;
import com.tinyrender.rollemup.BoxSushi;
import com.tinyrender.rollemup.CircleSushi;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Ground;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.Platform;
import com.tinyrender.rollemup.Player;
import com.tinyrender.rollemup.RollEmUp;

public class TestLevel extends Level {
	Player player;
	
	@Override
	public void create() {
		player = new Player(world);
		objects.add(player);
		
		new Ground(0.0f, 0.0f,
				   854.0f/Level.PTM_RATIO, 0.0f,
				   0.4f, world);
		new Ground(0.0f, 0.0f,
				   0.0f, 480.0f/Level.PTM_RATIO,
				   0.4f, world);
		new Ground(854.0f/Level.PTM_RATIO, 0.0f, 
				   854.0f/Level.PTM_RATIO, 480.0f/Level.PTM_RATIO,
				   0.4f, world);
		
		// floating platform
		new Platform(427.0f/Level.PTM_RATIO, 180.0f/Level.PTM_RATIO, 128.0f/Level.PTM_RATIO, 16.0f/Level.PTM_RATIO, 0.0f, world);
		
		// boxes
		for (int i = 0; i < 4; i++) {
			objects.add(new BoxSushi(((float)Math.random() * 320.0f + (float)Math.random() * 320.0f)/Level.PTM_RATIO,
											((float)Math.random() * 320.0f)/Level.PTM_RATIO, 0.1f, world));
		}
		
		// circles
		for (int i = 0; i < 20; i++) {
			objects.add(new CircleSushi(((float)Math.random() * 50f - (float)Math.random() * 50f)/Level.PTM_RATIO, 
										((float)Math.random() * 10 + 6)/Level.PTM_RATIO,
										0.2f, (float)(Math.random() * 2 * Math.PI), world));
		}
	}
	
	@Override
	public void update(float deltaTime) {
		cam.position.set((float)RollEmUp.SCREEN_HALF_WIDTH, (float)RollEmUp.SCREEN_HALF_HEIGHT, 0);
		box2dcam.position.set((float)RollEmUp.SCREEN_HALF_WIDTH/Level.PTM_RATIO, (float)RollEmUp.SCREEN_HALF_HEIGHT/Level.PTM_RATIO, 0);
		
		for (GameObject obj : objects)
			obj.update();
		
		physicsStep(deltaTime);
	}
	
	@Override
	public void touchDown() {
		player.isJumping = true;
	}

	@Override
	public void touchUp() {
		player.isJumping = false;
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		if (keyCode == Keys.SPACE)
			player.isJumping = true;
		return false;
	}
	
	@Override
	public boolean keyUp(int keyCode) {
		if (keyCode == Keys.SPACE)
			player.isJumping = false;
		return false;
	}
}
