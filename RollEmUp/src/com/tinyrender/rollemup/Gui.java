package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Gui {
    public OrthographicCamera cam = new OrthographicCamera(RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
    
    public Gui() {
		cam.setToOrtho(false, RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
		cam.position.set(RollEmUp.SCREEN_HALF_WIDTH, RollEmUp.SCREEN_HALF_HEIGHT, 0);
    }
}