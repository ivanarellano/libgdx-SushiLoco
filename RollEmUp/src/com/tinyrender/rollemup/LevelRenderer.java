package com.tinyrender.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.tinyrender.rollemup.screen.PlayScreen;

public class LevelRenderer {
	public Level level;
	public Box2DDebugRenderer renderer;
	
	public LevelRenderer(PlayScreen screen) {
		level = screen.level;
		renderer = new Box2DDebugRenderer();
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
	}
	
	public void render(float deltaTime) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		level.cam.update();
		level.cam.apply(Gdx.gl11);
		
		Assets.batch.setProjectionMatrix(level.cam.combined);
		Assets.batch.begin();
			for (GameObject obj : level.objects)
				obj.objectRepresentation.draw();
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
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
		if (null == renderer)
			renderer = new Box2DDebugRenderer();
	}
}
