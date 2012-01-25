package com.tinyrender.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.tinyrender.rollemup.screen.PlayScreen;

public class LevelRenderer {
	public Level level;
	Box2DDebugRenderer renderer = new Box2DDebugRenderer();
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	public LevelRenderer(PlayScreen screen) {
		level = screen.level;
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
	}
	
	public void render(float deltaTime) {
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		level.getLevelCamera().update();
		level.getLevelCamera().apply(Gdx.gl11);
		
		Assets.batch.setProjectionMatrix(level.getLevelCamera().combined);
		Assets.batch.begin();
		
			level.bodiesList = level.getWorldBodies();
			while (level.bodiesList.hasNext()) {
				level.nextWorldGameObj = (GameObject) level.bodiesList.next().getUserData();
				
				if (level.nextWorldGameObj.objRep.texture != null)
						level.nextWorldGameObj.objRep.draw();
				
				for (int i = 0; i < level.nextWorldGameObj.children.size; i++) {
					if (level.nextWorldGameObj.children.get(i).objRep.texture != null)
						level.nextWorldGameObj.children.get(i).objRep.draw();
				}
				
			}
			
			// Draw Player with attached objects and sub-objects.
			level.player.objRep.draw();
			for (int i = 0; i < level.player.children.size; i++) {
				level.player.children.get(i).objRep.draw();
				
				for (int j = 0; j < level.player.children.get(i).children.size; j++)
					level.player.children.get(i).children.get(j).objRep.draw();
			}
			
		Assets.batch.end();
		
		if (Settings.debugEnabled) {
			level.getBox2dCamera().update();
			renderer.render(level.b2world, level.getBox2dCamera().combined);
			
			shapeRenderer.setProjectionMatrix(level.getLevelCamera().combined);
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
