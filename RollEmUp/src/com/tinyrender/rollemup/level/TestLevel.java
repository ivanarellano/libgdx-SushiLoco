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
	Boat boat = new Boat();
	SoySauce soySauce = new SoySauce();
	TextureRegion boxSushiTex = Assets.atlas.findRegion("boxsushi");
	TextureRegion circleSushiTex = Assets.atlas.findRegion("circlesushi");
	
	public TestLevel() {
		player = new Player(this, b2world);
		player.xp.populate(player.xp.new Level(1, 15), player.xp.new Level(2, 30), 
						   player.xp.new Level(3, 70), player.xp.new Level(4, 100));
		//player.xp.printCurrentLevel();
		
		time = 3;
		
		gui.timer.reset(time);
		gui.goalMeter.setTexture(player.objRep.texture);
	}
	
	@Override
	public void createWorld() {
		new Ground(0.0f, 0.0f,
				   (854.0f*8.0f) / Level.PTM_RATIO, 0.0f,
				   1.0f, b2world);
		new Ground(0.0f, 0.0f,
				   0.0f, (480.0f*2.0f) / Level.PTM_RATIO,
				   1.0f, b2world);
		new Ground((854.0f*8.0f) / Level.PTM_RATIO, 0.0f,
				   (854.0f*8.0f) / Level.PTM_RATIO, (480.0f*2.0f) / Level.PTM_RATIO,
				   1.0f, b2world);
		
		float offsetX = 0;
		
		// boat
		offsetX += 1100.0f;
		for (int i = 0; i < 4; i++) {
			boat.build(offsetX / Level.PTM_RATIO, 0.0f, b2world);
			offsetX += 825.0f + (float) Math.random() * 100.0f;
		}
		
		// soy bottles
		offsetX = 1500.0f;
		for (int i = 0; i < 10; i++) {
			soySauce.build(offsetX / Level.PTM_RATIO, 0.0f, b2world);
			offsetX += 350.0f + (float) Math.random() * 1000.0f;
		}
		
		// boxes
		for (int i = 0; i < 50; i++) {
			new BoxObject(boxSushiTex, 
					((float) Math.random() * 4200.0f + 600.0f) / Level.PTM_RATIO,
					((float) Math.random() * 100.0f + 200.0f) / Level.PTM_RATIO,
					0.4f, 1, 1, false, true,
					GameObjectType.ROLLABLE, b2world);
		}

		// circles
		for (int i = 0; i < 50; i++) {
			new CircleObject(circleSushiTex, 
					((float) Math.random() * 4200.0f + 600.0f) / Level.PTM_RATIO, 
					((float) Math.random() * 100.0f + 200.0f) / Level.PTM_RATIO,
					(float) (Math.random() * 2.0f * MathUtils.PI),
					0.4f, 1, 1, false, true,
					GameObjectType.ROLLABLE, b2world);
		}
	}
	
	@Override
	public void running(float deltaTime) {
		getLevelCamera().position.set(player.pos.x*Level.PTM_RATIO, (player.pos.y+1.25f)*Level.PTM_RATIO, 0);
		getLevelCamera().zoom = camZoom;
		
		if (Settings.debugEnabled) {
			getBox2dCamera().position.set(player.pos.x, (player.pos.y+1.25f), 0);
			getBox2dCamera().zoom = camZoom;
		}

		updateBodies();
		
		if (player.xp.justLeveledUp())
			camZoom += 0.375f;
		
		physicsStep(deltaTime);
	}

	@Override public void ready(float deltaTime) { }
	@Override public void paused(float deltaTime) { }
	@Override public void levelEnd(float deltaTime) { }
	@Override public void gameOver(float deltaTime) { }
	
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
			camZoom += 0.1f;
		else if (keyCode == Keys.UP)
			camZoom -= 0.1f;
		return false;
	}
	
	@Override
	public boolean keyUp(int keyCode) {
		player.controller.keyUp(keyCode);
		return false;
	}
}
