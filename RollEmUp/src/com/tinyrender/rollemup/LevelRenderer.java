package com.tinyrender.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.tinyrender.rollemup.screen.PlayScreen;

public class LevelRenderer {
	public Level level;
	public Box2DDebugRenderer renderer = new Box2DDebugRenderer();
	
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
				level.objects.get(i).objectRepresentation.draw();

				for (int j = 0; j < level.objects.get(i).subObjects.size; j++)
					level.objects.get(i).subObjects.get(j).objectRepresentation.draw();
			}
			
			// Draw Player with attached objects and sub-objects.
			level.player.objectRepresentation.draw();
			for (int i = 0; i < level.player.subObjects.size; i++) {
				level.player.subObjects.get(i).objectRepresentation.draw();
				
				for (int j = 0; j < level.player.subObjects.get(i).subObjects.size; j++)
					level.player.subObjects.get(i).subObjects.get(j).objectRepresentation.draw();
			
			}
			
		Assets.batch.end();
		
		if (Settings.debugEnabled) {
			level.box2dcam.update();
			renderer.render(level.b2world, level.box2dcam.combined);
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
