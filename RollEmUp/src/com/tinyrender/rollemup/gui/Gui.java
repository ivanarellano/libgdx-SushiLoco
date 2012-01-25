package com.tinyrender.rollemup.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tinyrender.rollemup.RollEmUp;

public class Gui {
    public OrthographicCamera cam = new OrthographicCamera(RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
    
    public Gui() {
		cam.setToOrtho(false, RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
		cam.position.set(RollEmUp.TARGET_HALF_WIDTH, RollEmUp.TARGET_HALF_HEIGHT, 0);
    }
}