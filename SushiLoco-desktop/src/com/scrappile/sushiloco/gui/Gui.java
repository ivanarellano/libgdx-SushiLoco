package com.scrappile.sushiloco.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.scrappile.sushiloco.SushiLoco;

public class Gui {
    public OrthographicCamera cam = new OrthographicCamera(SushiLoco.SCREEN_WIDTH, SushiLoco.SCREEN_HEIGHT);
    
    public Gui() {
		cam.setToOrtho(false, SushiLoco.SCREEN_WIDTH, SushiLoco.SCREEN_HEIGHT);
		cam.position.set(SushiLoco.SCREEN_HALF_WIDTH, SushiLoco.SCREEN_HALF_HEIGHT, 0);
    }
}