package com.scrappile.sushiloco;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.scrappile.sushiloco.box2d.PhysicsWorld;
import com.scrappile.sushiloco.gui.LevelGui;
import com.scrappile.sushiloco.object.Player;
import com.scrappile.sushiloco.object.StaticObject;
import com.scrappile.sushiloco.screen.PlayScreen;

public abstract class Level extends PhysicsWorld implements LevelState {
	public int time = 5;
	public LevelGui gui;
	public Player player;
	
    ParallaxBackground background = new ParallaxBackground(new ParallaxLayer[]{
            new ParallaxLayer(Assets.atlas.findRegion("counterpanel"), new Vector2(100.0f, 100.0f), new Vector2(0.0f, -200.0f), new Vector2(0,2200.0f)),
            new ParallaxLayer(Assets.atlas.findRegion("counter1"), new Vector2(100.0f, 100.0f), new Vector2(0.0f,0.0f), new Vector2(0,2200.0f)),
      }, SushiLoco.SCREEN_WIDTH, SushiLoco.SCREEN_HEIGHT, new Vector2());
    
	
	OrthographicCamera levelCam = new OrthographicCamera(SushiLoco.SCREEN_WIDTH, SushiLoco.SCREEN_HEIGHT);
	OrthographicCamera box2dCam = new OrthographicCamera(SushiLoco.SCREEN_WIDTH/Level.PTM_RATIO, SushiLoco.SCREEN_HEIGHT/Level.PTM_RATIO);
	
	public GameObject nextWorldGameObj;
	public StaticObject nextStaticObj;
	
	public Level() {
		gui = new LevelGui(this);
		
		levelCam.setToOrtho(false, SushiLoco.SCREEN_WIDTH, SushiLoco.SCREEN_HEIGHT);		
		box2dCam.setToOrtho(false, SushiLoco.SCREEN_WIDTH/Level.PTM_RATIO, SushiLoco.SCREEN_HEIGHT/Level.PTM_RATIO);		
	}
	
	public void update(int state, float deltaTime) {
		switch (state) {
			case PlayScreen.GAME_READY:
				ready(deltaTime);
			break;
			case PlayScreen.GAME_RUNNING:
				running(deltaTime);
				//background.setSpeed(player.vel.x, player.vel.y);
			break;
			case PlayScreen.GAME_PAUSED:
				paused(deltaTime);
				//background.setSpeed(0.0f, 0.0f);
			break;
			case PlayScreen.GAME_LEVEL_END:
				levelEnd(deltaTime);
			break;
			case PlayScreen.GAME_OVER:
				gameOver(deltaTime);
			break;
		}
	}
	
	public void setCameraPosition(float x, float y) {
		levelCam.position.set(x * Level.PTM_RATIO, y * Level.PTM_RATIO, 0.0f);
		frustrumCulling.setPosition(x, y);
		
		if (Settings.debugEnabled)
			box2dCam.position.set(x, y, 0.0f);
	}
	
	public void zoomCamera(float zoom) {
		levelCam.zoom += zoom;
		box2dCam.zoom += zoom;
		background.setZoom(zoom);
		frustrumCulling.scale(zoom);
	}
	
	public OrthographicCamera getBox2dCamera() {
		return box2dCam;
	}
	
	public OrthographicCamera getLevelCamera() {
		return levelCam;
	}
	
	public ParallaxBackground getBackground() {
		return background;
	}
	
	public abstract void createWorld();
	
	public abstract void touchDown();
	public abstract void touchUp();
	public abstract boolean keyDown(int keyCode);
	public abstract boolean keyUp(int keyCode);
}
