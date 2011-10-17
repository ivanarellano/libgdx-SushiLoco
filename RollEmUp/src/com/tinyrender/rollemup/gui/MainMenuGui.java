package com.tinyrender.rollemup.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tinyrender.rollemup.Assets;
import com.tinyrender.rollemup.Gui;
import com.tinyrender.rollemup.RollEmUp;
import com.tinyrender.rollemup.Settings;

public class MainMenuGui extends Gui {
	public BitmapFont droidSans;
	public ImageButton start;
	public ImageButton sound;
	public LabelButton debug;
	public TextureRegion titleScreen;
	public TextureRegion titleLogo;
	
	public MainMenuGui() {
		droidSans = Assets.getBitmapFont("data/droidsans.fnt", "droidsans");
		titleScreen = Assets.atlas.findRegion("bgtitlescreen");
		titleLogo = Assets.atlas.findRegion("titlelogo");
		start = new ImageButton(Assets.atlas.findRegion("start"));
		
		if (Settings.soundEnabled)
			sound = new ImageButton(Assets.atlas.findRegion("soundon"));
		else
			sound = new ImageButton(Assets.atlas.findRegion("soundoff"));
		
		if (Settings.debugEnabled)
			debug = new LabelButton(droidSans, "debug: on");
		else
			debug = new LabelButton(droidSans, "debug: off");
		
		start.setPosition(RollEmUp.SCREEN_HALF_WIDTH - start.width/2.0f, RollEmUp.SCREEN_HALF_HEIGHT - 115.0f);
		sound.setPosition(50.0f, 50.0f);
		debug.setPosition(RollEmUp.SCREEN_HALF_WIDTH - debug.fontCache.getBounds().width/2.0f, RollEmUp.SCREEN_HALF_HEIGHT - 190.0f);
	}

	public void render() {
		Assets.batch.begin();
			Assets.batch.draw(titleScreen, 0.0f, 0.0f);
			Assets.batch.draw(titleLogo, RollEmUp.SCREEN_HALF_WIDTH - titleLogo.getRegionWidth()/2.0f, RollEmUp.SCREEN_HALF_HEIGHT + 75.0f);
			
			start.draw();
			sound.draw();
			debug.draw();
		Assets.batch.end();
	}
}
