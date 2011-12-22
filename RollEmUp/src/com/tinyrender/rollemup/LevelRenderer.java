package com.tinyrender.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.tinyrender.rollemup.screen.PlayScreen;

public class LevelRenderer {
	public Level level;
	public Box2DDebugRenderer renderer = new Box2DDebugRenderer();
	public ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	public LevelRenderer(PlayScreen screen) {
		level = screen.level;
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
	}
	
	public void render(float deltaTime) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		level.cam.update();
		level.cam.apply(Gdx.gl11);
		
		Assets.batch.setProjectionMatrix(level.cam.combined);
		Assets.batch.begin();
		
			// Draw level objects and their sub-objects.
			for (int i = 0; i < level.objects.size; i++) {
				level.objects.get(i).objRep.draw();

				for (int j = 0; j < level.objects.get(i).childObj.size; j++)
					level.objects.get(i).childObj.get(j).objRep.draw();
			}
			
			// Draw Player with attached objects and sub-objects.
			level.player.objRep.draw();
			for (int i = 0; i < level.player.childObj.size; i++) {
				level.player.childObj.get(i).objRep.draw();
				
				for (int j = 0; j < level.player.childObj.get(i).childObj.size; j++)
					level.player.childObj.get(i).childObj.get(j).objRep.draw();
			}
			
		Assets.batch.end();
		
		if (Settings.debugEnabled) {
			level.box2dcam.update();
			renderer.render(level.b2world, level.box2dcam.combined);
			
			shapeRenderer.setProjectionMatrix(level.cam.combined);
			shapeRenderer.begin(ShapeType.Rectangle);
			shapeRenderer.setColor(1.0f, 1.0f, 0.0f, 1.0f);
			shapeRenderer.rect(level.player.groundSensor.rect.x * Level.PTM_RATIO,
							   level.player.groundSensor.rect.y * Level.PTM_RATIO,
							   level.player.groundSensor.rect.width * Level.PTM_RATIO,
							   level.player.groundSensor.rect.height * Level.PTM_RATIO);
			shapeRenderer.end();
		}
	}
	
	public void dispose() {
		renderer.dispose();
		renderer = null;
	}
	
	public void resume() {
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		if (null == renderer)
			renderer = new Box2DDebugRenderer();
	}
}
