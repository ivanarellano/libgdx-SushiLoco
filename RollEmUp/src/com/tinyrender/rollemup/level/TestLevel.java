package com.tinyrender.rollemup.level;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameObject.GameObjectType;
import com.tinyrender.rollemup.Level;
import com.tinyrender.rollemup.Settings;
import com.tinyrender.rollemup.object.Boat;
import com.tinyrender.rollemup.object.BoxObject;
import com.tinyrender.rollemup.object.CircleObject;
import com.tinyrender.rollemup.object.Ground;
import com.tinyrender.rollemup.object.Player;
import com.tinyrender.rollemup.object.SoySauce;

public class TestLevel extends Level {
	TextureRegion boxSushiTex = Assets.atlas.findRegion("boxsushi");
	TextureRegion circleSushiTex = Assets.atlas.findRegion("circlesushi");
	SoySauce soySauce = new SoySauce();
	Boat boat = new Boat();
	
	public TestLevel() {
		player = new Player(this, b2world);
		levelTime = 3;
		gui.timer.reset(levelTime);
		gui.goalMeter.setTexture(player.objectRepresentation.texture);
	}
	
	@Override
	public void create() {
		new Ground(0.0f, 0.0f,
				   (854.0f*11.0f) / Level.PTM_RATIO, 0.0f,
				   1.0f, b2world);
		new Ground(0.0f, 0.0f,
				   0.0f, (480.0f*2.0f) / Level.PTM_RATIO,
				   1.0f, b2world);
		new Ground((854.0f*11.0f) / Level.PTM_RATIO, 0.0f,
				   (854.0f*11.0f) / Level.PTM_RATIO, (480.0f*2.0f) / Level.PTM_RATIO,
				   1.0f, b2world);
		
		float offsetX = 0;
		
		// boat
		offsetX += 1400.0f;
		for (int i = 0; i < 2; i++) {
			objects.add(boat.build(offsetX / Level.PTM_RATIO, 0.0f, b2world));
			offsetX += 3400.0f;
		}
		
		// soy bottles
		offsetX = 2500.0f;
		for (int i = 0; i < 4; i++) {
			objects.add(soySauce.build(offsetX / Level.PTM_RATIO, 0.0f, b2world));
			offsetX += 500.0f + (float) Math.random() * 2500.0f;
		}
		
		// boxes
		for (int i = 0; i < 40; i++) {
			objects.add(new BoxObject(boxSushiTex,
									 ((float) Math.random() * 4200.0f + 600.0f) / Level.PTM_RATIO,
									 ((float) Math.random() * 100.0f + 200.0f) / Level.PTM_RATIO,
									 0.4f, 1, 1,
									 GameObjectType.ROLLABLE,
									 b2world));
		}

		// circles
		for (int i = 0; i < 100; i++) {
			objects.add(new CircleObject(circleSushiTex,
										((float) Math.random() * 4200.0f + 600.0f) / Level.PTM_RATIO, 
										((float) Math.random() * 100.0f + 200.0f) / Level.PTM_RATIO,
										(float) (Math.random() * 2.0f * MathUtils.PI),
										0.4f,
										1, 1,
										GameObjectType.ROLLABLE,
										b2world));
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
		
		for (int i = 0; i < objects.size; i++)
			objects.get(i).update();
		
		player.update();
		for (int i = 0; i < player.subObjects.size; i++)
			player.subObjects.get(i).update();
				
		gui.goalMeter.scale = player.score * 0.5f;
		
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
