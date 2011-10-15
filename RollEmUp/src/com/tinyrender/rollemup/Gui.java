package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Gui {
    public OrthographicCamera cam;
    
    public Gui() {
		cam = new OrthographicCamera(RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
		cam.setToOrtho(false, RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
		cam.position.set(RollEmUp.SCREEN_HALF_WIDTH, RollEmUp.SCREEN_HALF_HEIGHT, 0);
    }
    
	public void render(Drawable...drawables) {
		Assets.batch.setProjectionMatrix(cam.combined);
		Assets.batch.begin();
			for (Drawable obj : drawables)
				obj.draw();
		Assets.batch.end();
	}
}
