package com.tinyrender.rollemup.level;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.GameObject;
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
	float newZoom = 1.0f;
	TextureRegion boatBodyTex;
	TextureRegion soySauceTex;
	TextureRegion boxSushiTex;
	TextureRegion circleSushiTex;
	
	public TestLevel() {
		boatBodyTex = Assets.atlas.findRegion("boatbody");
		soySauceTex = Assets.atlas.findRegion("soy");
		boxSushiTex = Assets.atlas.findRegion("boxsushi");
		circleSushiTex = Assets.atlas.findRegion("circlesushi");
		
		player = new Player(b2world);
		levelTime = 3;
		
		gui.timer.reset(levelTime);
		gui.goalMeter.setTexture(player.objectRepresentation.texture);
	}
	
	@Override
	public void create() {		
		new Ground(0.0f, 0.0f,
				   (854.0f*9.0f) / Level.PTM_RATIO, 0.0f,
				   0.4f, b2world);
		new Ground(0.0f, 0.0f,
				   0.0f, (480.0f*2.0f) / Level.PTM_RATIO,
				   0.4f, b2world);
		new Ground((854.0f*9.0f) / Level.PTM_RATIO, 0.0f,
				   (854.0f*9.0f) / Level.PTM_RATIO, (480.0f*2.0f) / Level.PTM_RATIO,
				   0.4f, b2world);
		
		// boxes
		for (int i = 0; i < 1; i++) {
			objects.add(new BoxObject((1000.0f + (float)Math.random() * 120.0f)/Level.PTM_RATIO,
										((float)Math.random() * 420.0f + 65.0f)/Level.PTM_RATIO,
										(boxSushiTex.getRegionWidth()/2.0f)/Level.PTM_RATIO,
										GameObjectType.ROLLABLE,
										boxSushiTex,
										b2world));
		}

		
		// circles
		for (int i = 0; i < 125; i++) {
			objects.add(new CircleObject((1100.0f + (float)Math.random() * 320f)/Level.PTM_RATIO, 
										((float)Math.random() * 10.0f + 65.0f)/Level.PTM_RATIO,
										(circleSushiTex.getRegionWidth()/2.0f)/Level.PTM_RATIO,
										(float)(Math.random() * 2.0f * Math.PI),
										GameObjectType.ROLLABLE,
										circleSushiTex,
										b2world));
		}
		/*
		objects.add(new SoySauce((2650.0f + (float)Math.random() * 400.0f) / Level.PTM_RATIO,
									(soySauceTex.getRegionHeight()/2.0f) / Level.PTM_RATIO,
									BodyType.DynamicBody,
									b2world));
		
		objects.add(new Boat((1750.0f + (float)Math.random() * 200.0f) / Level.PTM_RATIO,
				(boatBodyTex.getRegionWidth()/2.0f) / Level.PTM_RATIO,
				BodyType.DynamicBody,
				b2world));
		*/
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
		
		player.update();
		for (GameObject obj : player.subObjects)
			obj.update();
				
		gui.goalMeter.scale = player.mass * 0.15f;
		
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
