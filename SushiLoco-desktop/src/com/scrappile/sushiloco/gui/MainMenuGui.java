package com.scrappile.sushiloco.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.Settings;
import com.scrappile.sushiloco.SushiLoco;

public class MainMenuGui extends Gui {
	public ImageButton start;
	public ImageButton sound;
	public LabelButton debug;
	public TextureRegion titleScreen;
	public TextureRegion titleLogo;
	
	public MainMenuGui() {
		titleScreen = Assets.atlas.findRegion("bgtitlescreen");
		titleLogo = Assets.atlas.findRegion("titlelogo");
		start = new ImageButton(Assets.atlas.findRegion("start"));
		
		if (Settings.soundEnabled)
			sound = new ImageButton(Assets.atlas.findRegion("soundon"));
		else
			sound = new ImageButton(Assets.atlas.findRegion("soundoff"));
		
		if (Settings.debugEnabled)
			debug = new LabelButton(Assets.droidSansFont, "debug: on");
		else
			debug = new LabelButton(Assets.droidSansFont, "debug: off");
		
		start.setPosition(SushiLoco.SCREEN_HALF_WIDTH - start.width/2.0f, SushiLoco.SCREEN_HALF_HEIGHT - 115.0f);
		sound.setPosition(50.0f, 50.0f);
		
		debug.setPosition(SushiLoco.SCREEN_HALF_WIDTH - debug.fontCache.getBounds().width/2.0f, SushiLoco.SCREEN_HALF_HEIGHT - 190.0f);
	}

	public void render() {
		Assets.batch.begin();
			Assets.batch.draw(titleScreen, 0.0f, 0.0f);
			Assets.batch.draw(titleLogo, SushiLoco.SCREEN_HALF_WIDTH - titleLogo.getRegionWidth()/2.0f, SushiLoco.SCREEN_HALF_HEIGHT + 75.0f);
			
			start.draw();
			sound.draw();
			debug.draw();
		Assets.batch.end();
	}
}
