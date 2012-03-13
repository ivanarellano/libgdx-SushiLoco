package com.scrappile.sushiloco.level;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.Level;
import com.scrappile.sushiloco.box2d.PhysicsObject.Type;
import com.scrappile.sushiloco.object.Boat;
import com.scrappile.sushiloco.object.Counter;
import com.scrappile.sushiloco.object.GameBoxObject;
import com.scrappile.sushiloco.object.GameCircleObject;
import com.scrappile.sushiloco.object.Ground;
import com.scrappile.sushiloco.object.Player;
import com.scrappile.sushiloco.object.SoySauce;

public class TestLevel extends Level {
	Boat boat = new Boat();
	SoySauce soySauce = new SoySauce();
	Counter counter = new Counter();

	TextureRegion boxSushiTex = Assets.atlas.findRegion("boxsushi");
	TextureRegion circleSushiTex = Assets.atlas.findRegion("circlesushi");

	public TestLevel() {
		player = new Player(this);
		player.xp.populate(player.xp.new Level(1, 17), player.xp.new Level(2,
				35), player.xp.new Level(3, 75), player.xp.new Level(4, 100));

		time = 3;

		gui.timer.reset(time);
		gui.goalMeter.setTexture(player.objRep.texture);
	}

	@Override
	public void createWorld() {
		// Left edge
		new Ground(0.0f, 0.0f, 0.0f, (480.0f * 2.0f), 1.0f);
		
		// Right edge
		new Ground((854.0f * 8.0f), 0.0f, (854.0f * 8.0f), (480.0f * 2.0f), 1.0f);
		
		for (int i = 0; i < 7168; i+= 2560)
			counter.build(512.0f + i, 0.0f);

		float offsetX = 0;

		// boat
		offsetX += 2600.0f;
		for (int i = 0; i < 3; i++) {
			boat.build(offsetX, 0.0f);
			offsetX += 1500.0f + (float) Math.random() * 850.0f;
		}

		// soy bottles
		offsetX = 2800.0f;
		for (int i = 0; i < 4; i++) {
			soySauce.build(offsetX, 55.0f);
			offsetX += 200.0f + (float) Math.random() * 1000.0f;
		}

		// boxes
		for (int i = 0; i < 60; i++) {
			new GameBoxObject(boxSushiTex,
					((float) Math.random() * 4000.0f + 325.0f),
					((float) Math.random() * 150.0f + 230.0f),
					0.4f, 1, 1, false, true, Type.ROLLABLE);
		}

		// circles
		for (int i = 0; i < 50; i++) {
			new GameCircleObject(circleSushiTex,
					((float) Math.random() * 4000.0f + 325.0f),
					((float) Math.random() * 150.0f + 230.0f),
					(float) (Math.random() * 2.0f * MathUtils.PI), 0.4f, 1, 1,
					false, true, Type.ROLLABLE);
		}

	}

	@Override
	public void running(float deltaTime) {
		setCameraPosition(player.pos.x, player.pos.y + 1.25f);

		updateBodies();

		if (player.xp.justLeveledUp())
			zoomCamera(0.375f);

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
			zoomCamera(0.1f);
		else if (keyCode == Keys.UP)
			zoomCamera(-0.1f);

		return false;
	}

	@Override
	public boolean keyUp(int keyCode) {
		player.controller.keyUp(keyCode);
		return false;
	}
}
