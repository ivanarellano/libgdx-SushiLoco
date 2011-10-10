package com.tinyrender.rollemup;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Gui extends InputAdapter {
    public OrthographicCamera cam;
    public Timer timer;

	public Gui() {
		cam = new OrthographicCamera(RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
		cam.setToOrtho(false, RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
		cam.position.set(RollEmUp.SCREEN_HALF_WIDTH, RollEmUp.SCREEN_HALF_HEIGHT, 0);
		timer = new Timer();
	}
	
	public void render() {
		Assets.batch.setProjectionMatrix(cam.combined);
		Assets.batch.begin();
			if (timer.enabled) {
				timer.update();
				Assets.droidsans.draw(Assets.batch,
						timer.timeString, 
						RollEmUp.TARGET_WIDTH - Assets.droidsans.getBounds(timer.timeString).width - 20.0f,
						RollEmUp.TARGET_HEIGHT - 20.0f);
			}
		Assets.batch.end();
	}
}
