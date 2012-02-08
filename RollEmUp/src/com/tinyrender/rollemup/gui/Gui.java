package com.tinyrender.rollemup.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tinyrender.rollemup.RollEmUp;

public class Gui {
    public OrthographicCamera cam = new OrthographicCamera(RollEmUp.SCREEN_WIDTH, RollEmUp.SCREEN_HEIGHT);
    
    public Gui() {
		cam.setToOrtho(false, RollEmUp.SCREEN_WIDTH, RollEmUp.SCREEN_HEIGHT);
		cam.position.set(RollEmUp.SCREEN_HALF_WIDTH, RollEmUp.SCREEN_HALF_HEIGHT, 0);
    }
}