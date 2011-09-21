package com.tinyrender.rollemup;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class Gui extends InputAdapter {
    public final OrthographicCamera camera;
    //final Vector3 curr = new Vector3();
    //final Vector3 last = new Vector3(-1, -1, -1);
    //final Vector3 delta = new Vector3();
    public static final int TARGET_WIDTH = 854;
    public static final int TARGET_HEIGHT = 480;

	public Gui() {
		camera = new OrthographicCamera(TARGET_WIDTH, TARGET_HEIGHT);
		camera.setToOrtho(false, TARGET_WIDTH, TARGET_HEIGHT);
		camera.position.set(TARGET_WIDTH / 2, TARGET_HEIGHT / 2, 0);
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
