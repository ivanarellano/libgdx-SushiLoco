package com.tinyrender.rollemup;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Gui extends InputAdapter {
	public interface GuiController {
		public void update();
		public void render();
	}
	
    public OrthographicCamera cam;
    public GuiController control;
    public Timer timer;

	public Gui() {
		cam = new OrthographicCamera(RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
		cam.setToOrtho(false, RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
		cam.position.set(RollEmUp.SCREEN_HALF_WIDTH, RollEmUp.SCREEN_HALF_HEIGHT, 0);
		timer = new Timer();
		
		control = new GuiController() {
			@Override public void update() {}
			@Override public void render() {}
		};
	}
	
	public void render() {
		Assets.batch.setProjectionMatrix(cam.combined);
		Assets.batch.begin();
			control.render();
		Assets.batch.end();
	}
}
