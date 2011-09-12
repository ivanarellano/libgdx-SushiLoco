package com.tinyrender.androidgame.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Assets {
	public static AssetManager manager;
	public static AssetErrorListener managerError;
	
	public static TextureAtlas atlas;
	public static AtlasRegion levelSelectMap;
	public static AtlasRegion soundOff;
	public static AtlasRegion soundOn;
	public static AtlasRegion splashScreen;
	public static AtlasRegion start;
	public static AtlasRegion titleLogo;
	
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
    	batch = new SpriteBatch();
    	load();
    	Texture.setAssetManager(manager);    	
    }
    
    private static void load() {
    	manager.load("assets/data/pack", TextureAtlas.class);
    	manager.load("assets/data/music.mp3", Music.class);
    	manager.load("assets/data/click.ogg", Sound.class);
    	
    	manager.finishLoading();
    	
    	if (Assets.manager.isLoaded("assets/data/pack")) {
    		levelSelectMap = manager.get("assets/data/pack", TextureAtlas.class).findRegion("levelselect");
    		soundOff = manager.get("assets/data/pack", TextureAtlas.class).findRegion("soundoff");
    		soundOn = manager.get("assets/data/pack", TextureAtlas.class).findRegion("soundon");
    		splashScreen = manager.get("assets/data/pack", TextureAtlas.class).findRegion("splash1");
    		start = manager.get("assets/data/pack", TextureAtlas.class).findRegion("start");
    		titleLogo = manager.get("assets/data/pack", TextureAtlas.class).findRegion("title");
    	}
    	
    	if (Assets.manager.isLoaded("assets/data/music.mp3")) {
    		music = manager.get("assets/data/music.mp3", Music.class);
    		music.setLooping(true);
    		music.setVolume(0.5f);
    	}
    	
    	if (Assets.manager.isLoaded("assets/data/click.ogg"))
    		hitSound = manager.get("assets/data/click.ogg", Sound.class);
    }
    
    private static void unload() {
    	manager.unload("assets/data/pack");
    	manager.unload("assets/data/music.mp3");
    	manager.unload("assets/data/click.ogg");
    }
    
    public static void playSound(Sound sound) {
        if(Settings.soundEnabled)
            sound.play(1);
    }
}
