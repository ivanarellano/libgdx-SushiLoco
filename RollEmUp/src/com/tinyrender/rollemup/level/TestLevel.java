package com.tinyrender.rollemup.level;

import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Ground;
import com.tinyrender.rollemup.Level;
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
		
		// floating platforms
		
		//float distX = (-60.0f*Level.PTM_RATIO)/Level.PTM_RATIO;
		//for(int i = 0; i < 3; i++) {
		//	new Platform(distX, (9.0f*Level.PTM_RATIO)/Level.PTM_RATIO, (10.0f*Level.PTM_RATIO)/Level.PTM_RATIO, (1.0f*Level.PTM_RATIO)/Level.PTM_RATIO, 0.0f, world);
		//	distX += (40.0f*Level.PTM_RATIO)/Level.PTM_RATIO;
		//}
		
		//cam.zoom = 1.5f;
		
		
		// boxes
		/*
		objects.add(new BoxSushi((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, 0.1f, world));
		objects.add(new BoxSushi((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, 0.1f, world));
		objects.add(new BoxSushi((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, 0.1f, world));
		objects.add(new BoxSushi((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, 0.1f, world));
		*/
		
		// circles
		/*
		for (int i = 0; i < 20; i++) {
			objects.add(new CircleSushi((float)Math.random() * 50f - (float)Math.random() * 50f, 
										(float)Math.random() * 10 + 6,
										(float)Math.random() * 0.7f + 0.3f,
										0.2f, (float)(Math.random() * 2 * Math.PI), world));
		}
		*/
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
}
