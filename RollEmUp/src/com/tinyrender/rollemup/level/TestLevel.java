package com.tinyrender.rollemup.level;

import com.badlogic.gdx.Input.Keys;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameObject;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.Settings;
import com.tinyrender.rollemup.object.BoxSushi;
import com.tinyrender.rollemup.object.CircleSushi;
import com.tinyrender.rollemup.object.Ground;
import com.tinyrender.rollemup.object.Player;

public class TestLevel extends Level {
	Player player;
	float newZoom = 1.0f;
	
	public TestLevel() {
		player = new Player(b2world);
		levelTime = 3;
		
		gui.timer.reset(levelTime);
		gui.goalMeter.setTexture(player.objectRepresentation.texture);
	}
	
	@Override
	public void create() {
		objects.add(player);
		
		new Ground(0.0f, 0.0f,
				   (854.0f*2.0f)/Level.PTM_RATIO, 0.0f,
				   0.4f, b2world);
		new Ground(0.0f, 0.0f,
				   0.0f, 480.0f/Level.PTM_RATIO,
				   0.4f, b2world);
		new Ground((854.0f*2.0f)/Level.PTM_RATIO, 0.0f, 
				   (854.0f*2.0f)/Level.PTM_RATIO, 480.0f/Level.PTM_RATIO,
				   0.4f, b2world);
		
		// boxes
		for (int i = 0; i < 10; i++) {
			objects.add(new BoxSushi(((float)Math.random() * 120.0f + (float)Math.random() * 1100.0f)/Level.PTM_RATIO,
											((float)Math.random() * 320.0f)/Level.PTM_RATIO, (Assets.boxSushi.getRegionWidth()/2.0f)/Level.PTM_RATIO, b2world));
		}
		
		// circles
		for (int i = 0; i < 10; i++) {
			objects.add(new CircleSushi(((float)Math.random() * 150f + (float)Math.random() * 1350f)/Level.PTM_RATIO, 
										((float)Math.random() * 10 + 6)/Level.PTM_RATIO,
										(Assets.circleSushi.getRegionWidth()/2.0f)/Level.PTM_RATIO, (float)(Math.random() * 2 * Math.PI), b2world));
		}
	}
	
	@Override
	public void running(float deltaTime) {
		cam.position.set(player.pos.x*Level.PTM_RATIO, (player.pos.y+1.25f)*Level.PTM_RATIO, 0);
		cam.zoom = newZoom;
		
		if (Settings.debugEnabled) {
			box2dcam.position.set(player.pos.x, (player.pos.y+1.25f), 0);
			box2dcam.zoom = newZoom;
		}
		
		for (GameObject obj : objects)
			obj.update();
				
		gui.goalMeter.scale = player.size * 0.8f;
		
		physicsStep(deltaTime);
	}

	@Override
	public void ready(float deltaTime) {		
	}

	@Override
	public void paused(float deltaTime) {		
	}

	@Override
	public void levelEnd(float deltaTime) {		
	}

	@Override
	public void gameOver(float deltaTime) {		
	}
	
	@Override
	public void touchDown() {
		player.controller.touchDown();
	}

	@Override
	public void touchUp() {
		player.controller.touchUp();
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		player.controller.keyDown(keyCode);
		
		if (keyCode == Keys.DOWN)
			newZoom += 0.1f;
		else if (keyCode == Keys.UP)
			newZoom -= 0.1f;
		return false;
	}
	
	@Override
	public boolean keyUp(int keyCode) {
		player.controller.keyUp(keyCode);
		return false;
	}
}
