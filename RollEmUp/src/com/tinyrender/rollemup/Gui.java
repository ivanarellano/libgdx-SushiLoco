package com.tinyrender.rollemup;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Gui extends InputAdapter {
    public final OrthographicCamera camera;
    //final Vector3 curr = new Vector3();
    //final Vector3 last = new Vector3(-1, -1, -1);
    //final Vector3 delta = new Vector3();

	public Gui() {
		camera = new OrthographicCamera(RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
		camera.setToOrtho(false, RollEmUp.TARGET_WIDTH, RollEmUp.TARGET_HEIGHT);
		camera.position.set(RollEmUp.SCREEN_HALF_WIDTH, RollEmUp.SCREEN_HALF_HEIGHT, 0);
	}
	
	/*
    @Override
    public boolean touchDragged (int x, int y, int pointer) {
            camera.unproject(curr.set(x, y, 0));
            if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
                    camera.unproject(delta.set(last.x, last.y, 0));
                    delta.sub(curr);
                    camera.position.add(delta.x, delta.y, 0);
            }
            last.set(x, y, 0);
            return false;
    }

    @Override
    public boolean touchUp (int x, int y, int pointer, int button) {
            last.set(-1, -1, -1);
            return false;
    }
    */
}
