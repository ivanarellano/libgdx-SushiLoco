package com.scrappile.sushiloco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.scrappile.sushiloco.box2d.PhysicsObject;
import com.scrappile.sushiloco.object.Player;
import com.scrappile.sushiloco.object.StaticObject;
import com.scrappile.sushiloco.screen.PlayScreen;

public class LevelRenderer {

    public Level level;
    private Box2DDebugRenderer box2dDebugRenderer = new Box2DDebugRenderer(true, false, false, false, false, false);
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public LevelRenderer(PlayScreen screen) {
        level = screen.getLevel();
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
    }

    public void render(float deltaTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //level.getBackground().render(deltaTime);

        level.getLevelCamera().update(false);
        //level.getLevelCamera().apply(Gdx.gl20);

        Assets.batch.setProjectionMatrix(level.getLevelCamera().combined);
        Assets.batch.begin();

        // Render objects without physics
        for (int i = 0; i < level.unreachableObjects.size; i++) {
            level.unreachableObjects.get(i).draw();
        }

        Array<Body> bodies = level.getBodies();

        for (Body b : bodies) {
            PhysicsObject physicsObject = (PhysicsObject) b.getUserData();

            if (physicsObject.body.isActive()) {
                switch (physicsObject.type) {
                    case ROLLABLE:
                        GameObject gameObject = (GameObject) physicsObject;

                        if (gameObject.objRep.texture != null)
                            gameObject.objRep.draw();

                        for (int i = 0; i < gameObject.children.size; i++) {
                            if (gameObject.children.get(i).objRep.texture != null)
                                gameObject.children.get(i).objRep.draw();
                        }

                        break;
                    case STATIC:
                        StaticObject staticObject = (StaticObject) physicsObject;

                        if (!staticObject.hidden)
                            staticObject.objRep.draw();

                        break;
                    case PLAYER:
                        break;
                    default:
                        break;
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

        // TODO: Move to DebugRenderer
        if (Settings.debugEnabled) {
            level.getBox2dCamera().update(false);
            box2dDebugRenderer.render(level.getWorld(), level.getBox2dCamera().combined);

            shapeRenderer.setProjectionMatrix(level.getLevelCamera().combined);

            Player.GroundSensor sensor = level.player.getGroundSensor();
            // Render player grounding sensor
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.setColor(1.0f, 1.0f, 0.0f, 1.0f);
            shapeRenderer.rect(sensor.rect.x * Level.PTM_RATIO,
                    sensor.rect.y * Level.PTM_RATIO,
                    sensor.rect.width * Level.PTM_RATIO,
                    sensor.rect.height * Level.PTM_RATIO);
            shapeRenderer.end();

            // Render culling sensor
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.setColor(1.0f, 0.0f, 0.5f, 1.0f);
            shapeRenderer.rect(level.getFrustrumCulling().getBounds().x * Level.PTM_RATIO,
                    level.getFrustrumCulling().getBounds().y * Level.PTM_RATIO,
                    level.getFrustrumCulling().getBounds().width * Level.PTM_RATIO,
                    level.getFrustrumCulling().getBounds().height * Level.PTM_RATIO);
            shapeRenderer.end();
        }
    }

    public void dispose() {
        box2dDebugRenderer.dispose();
        box2dDebugRenderer = null;
    }

    public void resume() {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        if (null == box2dDebugRenderer)
            box2dDebugRenderer = new Box2DDebugRenderer();
    }
}
