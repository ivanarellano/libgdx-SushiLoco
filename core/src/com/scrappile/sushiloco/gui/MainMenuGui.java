package com.scrappile.sushiloco.gui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.Settings;
import com.scrappile.sushiloco.SushiLoco;

public class MainMenuGui extends Gui {

	private ImageButton start;
	private ImageButton sound;
	private LabelButton debug;
	private TextureRegion titleScreen;
	private TextureRegion titleLogo;

	public MainMenuGui() {
		titleScreen = Assets.atlas.findRegion("bgtitlescreen");
		titleLogo = Assets.atlas.findRegion("titlelogo");

		String debugLabel = Settings.debugEnabled ? "debug: on" : "debug: off";
		String soundRegion = Settings.soundEnabled ? "soundon" : "soundoff";

		start = new ImageButton(Assets.atlas.findRegion("start"));
		sound = new ImageButton(Assets.atlas.findRegion(soundRegion));
		debug = new LabelButton(Assets.droidSansFont, debugLabel);
		
		start.setPosition(SushiLoco.SCREEN_HALF_WIDTH - start.getWidth() / 2.0f, SushiLoco.SCREEN_HALF_HEIGHT - 115.0f);
		sound.setPosition(50.0f, 50.0f);
		debug.setPosition(SushiLoco.SCREEN_HALF_WIDTH - debug.getWidth() / 2.0f, SushiLoco.SCREEN_HALF_HEIGHT - 190.0f);
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

	public ImageButton getStart() {
		return start;
	}

	public ImageButton getSound() {
		return sound;
	}

	public LabelButton getDebug() {
		return debug;
	}

	public TextureRegion getTitleScreen() {
		return titleScreen;
	}

	public TextureRegion getTitleLogo() {
		return titleLogo;
	}
}
