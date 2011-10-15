package com.tinyrender.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public static AssetManager manager;
	public static AssetErrorListener managerError;
	
	public static TextureAtlas atlas;
	public static TextureRegion levelSelectMap;
	public static TextureRegion soundOff;
	public static TextureRegion soundOn;
	public static TextureRegion splashScreen;
	public static TextureRegion titleScreen;
	public static TextureRegion start;
	public static TextureRegion titleLogo;
	public static TextureRegion player;
	public static TextureRegion circleSushi;
	public static TextureRegion boxSushi;
	public static TextureRegion soySauce;
	public static Texture scratch;
	public static BitmapFont droidsans;
	
	public static Music music;
	public static Sound hitSound;
	
	public static SpriteBatch batch;
    
    public static void create() {
    	manager = new AssetManager();
    	managerError = new AssetErrorListener() {
			@Override
			public void error(String fileName, Class type, Throwable t) {
				Gdx.app.error("AssetManagerTest", "couldn't load asset '" + fileName + "'", (Exception)t);						
			}
		};
		
    	manager.setErrorListener(managerError);
    	load();
    	Texture.setAssetManager(manager);
    	
    	batch = new SpriteBatch();
    }
    
    private static void load() {
    	manager.load("data/pack", TextureAtlas.class);
    	manager.load("data/assets1.png", Texture.class);
    	manager.load("data/assets2.png", Texture.class);
    	manager.load("data/scratch.png", Texture.class);
    	manager.load("data/droidsans.fnt", BitmapFont.class);
    	manager.load("data/music.mp3", Music.class);
    	manager.load("data/click.ogg", Sound.class);
    	
    	manager.finishLoading();
    	
    	if (manager.isLoaded("data/pack")) {
    		levelSelectMap = manager.get("data/pack", TextureAtlas.class).findRegion("levelselect");
    		soundOff = manager.get("data/pack", TextureAtlas.class).findRegion("soundoff");
    		soundOn = manager.get("data/pack", TextureAtlas.class).findRegion("soundon");
    		splashScreen = manager.get("data/pack", TextureAtlas.class).findRegion("tuna");
    		titleScreen = manager.get("data/pack", TextureAtlas.class).findRegion("bgtitlescreen");
    		start = manager.get("data/pack", TextureAtlas.class).findRegion("start");
    		titleLogo = manager.get("data/pack", TextureAtlas.class).findRegion("titlelogo");
    		player = manager.get("data/pack", TextureAtlas.class).findRegion("player");
    		circleSushi = manager.get("data/pack", TextureAtlas.class).findRegion("circlesushi");
    		boxSushi = manager.get("data/pack", TextureAtlas.class).findRegion("boxsushi");
    		soySauce = manager.get("data/pack", TextureAtlas.class).findRegion("soy");
    		
        	if (manager.isLoaded("data/droidsans.fnt")) {
        		BitmapFontData bfd = new BitmapFontData(Gdx.files.internal("data/droidsans.fnt"), false);
        		droidsans = new BitmapFont(bfd, manager.get("data/pack", TextureAtlas.class).findRegion("droidsans"), false);
        	}
    	}
    	
    	if (manager.isLoaded("data/scratch.png"))
    		scratch = manager.get("data/scratch.png", Texture.class);
    	
    	if (manager.isLoaded("data/music.mp3")) {
    		music = manager.get("data/music.mp3", Music.class);
    		music.setLooping(true);
    		music.setVolume(0.5f);
    	}
    	
    	if (manager.isLoaded("data/click.ogg"))
    		hitSound = manager.get("data/click.ogg", Sound.class);
    }
    
    public static void playSound(Sound sound) {
        if (Settings.soundEnabled)
            sound.play(1);
    }
}
