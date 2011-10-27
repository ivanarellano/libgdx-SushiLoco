package com.tinyrender.rollemup;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

public class RollEmUpDesktop {
	public static void main(String args[]) {
        Settings settings = new Settings();
        settings.padding = 4;
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;
        settings.incremental = true;
        settings.pot = true;
        TexturePacker.process(settings, "assets", "data");
        
		new LwjglApplication(new RollEmUp(), "Roll 'Em Up", 854, 480, false);
	}
}