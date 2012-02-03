package com.tinyrender.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.tinyrender.rollemup.box2d.PhysicsObject;
import com.tinyrender.rollemup.box2d.PhysicsObject.Type;
import com.tinyrender.rollemup.box2d.PhysicsWorld;
import com.tinyrender.rollemup.screen.PlayScreen;

public class LevelRenderer {
	public Level level;
	Box2DDebugRenderer renderer = new Box2DDebugRenderer(false, false, true);
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
		
			// Draw level objects and their sub-objects
			PhysicsWorld.bodiesList = PhysicsWorld.getWorldBodies();
			while (PhysicsWorld.bodiesList.hasNext()) {
				level.nextWorldPhysicsObj = (PhysicsObject) PhysicsWorld.bodiesList.next().getUserData();
				
				if (level.nextWorldPhysicsObj.type == Type.ROLLABLE && level.nextWorldPhysicsObj.body.isActive()) {
					level.nextWorldGameObj = (GameObject) level.nextWorldPhysicsObj;
					
					if (level.nextWorldGameObj.objRep.texture != null)
							level.nextWorldGameObj.objRep.draw();
					
					for (int i = 0; i < level.nextWorldGameObj.children.size; i++) {
						if (level.nextWorldGameObj.children.get(i).objRep.texture != null)
							level.nextWorldGameObj.children.get(i).objRep.draw();
					}
				}
				
			}
			
			// Draw player
			level.player.objRep.draw();
			
			// Draw player attached objects and their sub-objects
			for (int i = 0; i < level.player.children.size; i++) {
				level.player.children.get(i).objRep.draw();
				
				for (int j = 0; j < level.player.children.get(i).children.size; j++)
					level.player.children.get(i).children.get(j).objRep.draw();
			}
			
		Assets.batch.end();
		
		if (Settings.debugEnabled) {
			level.getBox2dCamera().update();
			renderer.render(PhysicsWorld.b2world, level.getBox2dCamera().combined);
			
			shapeRenderer.setProjectionMatrix(level.getLevelCamera().combined);
			
			// Render player grounding sensor
			shapeRenderer.begin(ShapeType.Rectangle);
			shapeRenderer.setColor(1.0f, 1.0f, 0.0f, 1.0f);
			shapeRenderer.rect(level.player.groundSensor.rect.x * Level.PTM_RATIO,
							   level.player.groundSensor.rect.y * Level.PTM_RATIO,
							   level.player.groundSensor.rect.width * Level.PTM_RATIO,
							   level.player.groundSensor.rect.height * Level.PTM_RATIO);
			shapeRenderer.end();
			
			// Render culling sensor
			shapeRenderer.begin(ShapeType.Rectangle);
			shapeRenderer.setColor(1.0f, 0.0f, 0.5f, 1.0f);
			shapeRenderer.rect(level.getFrustrumCulling().getBounds().x * Level.PTM_RATIO,
							   level.getFrustrumCulling().getBounds().y * Level.PTM_RATIO,
							   level.getFrustrumCulling().getBounds().width * Level.PTM_RATIO,
							   level.getFrustrumCulling().getBounds().height * Level.PTM_RATIO);
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
