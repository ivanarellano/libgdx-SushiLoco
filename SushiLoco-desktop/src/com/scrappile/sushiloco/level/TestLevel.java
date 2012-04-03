package com.scrappile.sushiloco.level;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.Level;
import com.scrappile.sushiloco.box2d.PhysicsObject.Type;
import com.scrappile.sushiloco.object.GameBoxObject;
import com.scrappile.sushiloco.object.GameCircleObject;
import com.scrappile.sushiloco.object.Ground;
import com.scrappile.sushiloco.object.Player;
import com.scrappile.sushiloco.object.decor.Boat;
import com.scrappile.sushiloco.object.decor.Counter;
import com.scrappile.sushiloco.object.dishes.Dishes;
import com.scrappile.sushiloco.object.food.FoodFactory;

public class TestLevel extends Level {
	Boat boat = new Boat();
	Counter counter = new Counter();
	FoodFactory food = new FoodFactory();
	Dishes dishes = new Dishes();

	TextureRegion testBoxTex = Assets.atlas.findRegion("boxsushi");
	TextureRegion testCircleTex = Assets.atlas.findRegion("circlesushi");

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
		
		for (int i = 0; i < 7168; i += 1016)
			unreachableObjects.add(counter.buildPanel(i, -490.0f));

		for (int i = 0; i < 3; i++) {
			
			food.eggNigiri.build((float) Math.random() * 2500.0f + 950.0f, 
					(float) Math.random() * 225.0f + 200.0f);
			food.mackarelNigiri.build((float) Math.random() * 2700.0f + 1350.0f, 
					(float) Math.random() * 225.0f + 200.0f);
			food.tunaNigiri.build((float) Math.random() * 2900.0f + 1750.0f, 
					(float) Math.random() * 225.0f + 200.0f);
			food.crabNigiri.build((float) Math.random() * 3100.0f + 2150.0f, 
					(float) Math.random() * 225.0f + 200.0f);
			food.dumpling.build((float) Math.random() * 3200.0f + 2650.0f, 
					(float) Math.random() * 225.0f + 200.0f);
			
			dishes.smallCups.build1((float) Math.random() * 2500.0f + 950.0f, 
					(float) Math.random() * 225.0f + 200.0f);
			
			dishes.smallCups.build2((float) Math.random() * 2000.0f + 850.0f, 
					(float) Math.random() * 225.0f + 200.0f);
			
			dishes.woodenPlate.build((float) Math.random() * 2300.0f + 1350.0f, 
					(float) Math.random() * 225.0f + 200.0f);
			
			
			dishes.saucer.build((float) Math.random() * 2900.0f + 1750.0f, 
					(float) Math.random() * 225.0f + 200.0f);
			
		}
		
		float offsetX = 0;

		// boat
		offsetX += 2600.0f;
		for (int i = 0; i < 2; i++) {
			boat.build(offsetX, 0.0f);
			offsetX += 1500.0f + (float) Math.random() * 650.0f;
		}

		// soy bottles
		offsetX = 2800.0f;
		for (int i = 0; i < 4; i++) {
			food.soySauce.build(offsetX, 55.0f);
			offsetX += 1000.0f + (float) Math.random() * 2000.0f;
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
