package com.tinyrender.rollemup;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class RollEmUpDesktop {
	public static void main(String args[]) {
		new JoglApplication(new RollEmUp(), "Roll 'Em Up", 480, 320, false);
	}
}
