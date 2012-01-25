package com.tinyrender.rollemup;

import java.util.Iterator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.tinyrender.rollemup.box2d.PhysicsObject;
import com.tinyrender.rollemup.box2d.PhysicsWorld;
import com.tinyrender.rollemup.gui.LevelGui;
import com.tinyrender.rollemup.object.Player;
import com.tinyrender.rollemup.screen.PlayScreen;

public abstract class Level extends PhysicsWorld implements LevelState {
	public int time = 5;
	public LevelGui gui;
	public Player player;
	
	GameObject objectCulling = new GameObject(b2world);
	
	public float camZoom = 1.0f;
	OrthographicCamera levelCam = new OrthographicCamera(
			(float) RollEmUp.TARGET_WIDTH,
			(float) RollEmUp.TARGET_HEIGHT);
	OrthographicCamera box2dCam = new OrthographicCamera(
			(float) RollEmUp.TARGET_WIDTH/Level.PTM_RATIO,
			(float) RollEmUp.TARGET_HEIGHT/Level.PTM_RATIO);
	
	public Iterator<Body> bodiesList;
	public Body nextWorldBody;
	public GameObject nextWorldGameObj;
	public PhysicsObject nextWorldPhysicsObj;

	public Level() {
		gui = new LevelGui(this);
		
		levelCam.setToOrtho(false, (float) RollEmUp.TARGET_WIDTH, (float) RollEmUp.TARGET_HEIGHT);		
		box2dCam.setToOrtho(false, (float) RollEmUp.TARGET_WIDTH/Level.PTM_RATIO, (float) RollEmUp.TARGET_HEIGHT/Level.PTM_RATIO);
		/*
		objectCulling.body = BodyFactory.createBox(0, 0, 
				RollEmUp.TARGET_WIDTH / Level.PTM_RATIO, 
				RollEmUp.TARGET_HEIGHT / Level.PTM_RATIO,
				0, true, BodyType.KinematicBody, b2world);
		objectCulling.body.setUserData(objectCulling);
		*/
	}
	
	public void update(int state, float deltaTime) {
		switch (state) {
			case PlayScreen.GAME_READY:
				ready(deltaTime);
			break;
			case PlayScreen.GAME_RUNNING:
				running(deltaTime);
			break;
			case PlayScreen.GAME_PAUSED:
				paused(deltaTime);
			break;
			case PlayScreen.GAME_LEVEL_END:
				levelEnd(deltaTime);
			break;
			case PlayScreen.GAME_OVER:
				gameOver(deltaTime);
			break;
		}
	}
	
	protected void updateBodies() {
		bodiesList = getWorldBodies();
		
		while (bodiesList.hasNext()) {
			nextWorldBody = bodiesList.next();
			
			if (null != nextWorldBody) {
				nextWorldPhysicsObj = (PhysicsObject) nextWorldBody.getUserData();
				
				if (nextWorldPhysicsObj.doUpdate)
					nextWorldPhysicsObj.update();
			}
		}
	}
	
	public OrthographicCamera getLevelCamera() {
		return levelCam;
	}
	
	public OrthographicCamera getBox2dCamera() {
		return box2dCam;
	}
	
	public abstract void createWorld();
	
	public abstract void touchDown();
	public abstract void touchUp();
	public abstract boolean keyDown(int keyCode);
	public abstract boolean keyUp(int keyCode);
}
