package com.tinyrender.rollemup.gui;

import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.Gui;
import com.tinyrender.rollemup.RollEmUp;
import com.tinyrender.rollemup.Settings;

public class MainMenuGui extends Gui {
	public ImageButton start;
	public ImageButton sound;
	public LabelButton debug;
	
	public MainMenuGui() {
		start = new ImageButton(Assets.start);
		
		if (Settings.soundEnabled)
			sound = new ImageButton(Assets.soundOn);
		else
			sound = new ImageButton(Assets.soundOff);
		
		if (Settings.debugEnabled)
			debug = new LabelButton(Assets.droidsans, "debug: on");
		else
			debug = new LabelButton(Assets.droidsans, "debug: off");
		
		start.setPosition(RollEmUp.SCREEN_HALF_WIDTH - start.width/2.0f, RollEmUp.SCREEN_HALF_HEIGHT - 115.0f);
		sound.setPosition(50.0f, 50.0f);
		debug.setPosition(RollEmUp.SCREEN_HALF_WIDTH - debug.fontCache.getBounds().width/2.0f, RollEmUp.SCREEN_HALF_HEIGHT - 190.0f);
	}

	public void render() {
		Assets.batch.begin();
			Assets.batch.draw(Assets.titleScreen, 0.0f, 0.0f);
			Assets.batch.draw(Assets.titleLogo, RollEmUp.SCREEN_HALF_WIDTH - Assets.titleLogo.getRegionWidth()/2.0f, RollEmUp.SCREEN_HALF_HEIGHT + 75.0f);
			
			start.draw();
			sound.draw();
			debug.draw();
		Assets.batch.end();
	}
}
