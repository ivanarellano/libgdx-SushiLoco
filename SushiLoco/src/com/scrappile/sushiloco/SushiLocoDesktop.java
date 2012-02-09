package com.scrappile.sushiloco;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

public class SushiLocoDesktop {
	public static void main(String args[]) {
        Settings settings = new Settings();
        settings.padding = 4;
        settings.maxWidth = 1024;
        settings.maxHeight = 1024;
        settings.incremental = true;
        settings.pot = true;
        TexturePacker.process(settings, "assets", "data");
        
		new LwjglApplication(new SushiLoco(), "Sushi Loco", 854, 480, false);
	}
}