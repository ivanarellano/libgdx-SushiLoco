package com.tinyrender.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
	public static AssetManager manager;
	public static AssetErrorListener managerError;
	
	public static TextureAtlas atlas;
	public static Sprite levelSelectMap;
	public static Sprite soundOff;
	public static Sprite soundOn;
	public static Sprite splashScreen;
	public static Sprite titleScreen;
	public static Sprite start;
	public static Sprite titleLogo;
	
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
    	manager.load("data/music.mp3", Music.class);
    	manager.load("data/click.ogg", Sound.class);
    	
    	manager.finishLoading();
    	
    	if (manager.isLoaded("data/pack")) {
    		levelSelectMap = manager.get("data/pack", TextureAtlas.class).createSprite("levelselect");
    		soundOff = manager.get("data/pack", TextureAtlas.class).createSprite("soundoff");
    		soundOn = manager.get("data/pack", TextureAtlas.class).createSprite("soundon");
    		splashScreen = manager.get("data/pack", TextureAtlas.class).createSprite("tuna");
    		titleScreen = manager.get("data/pack", TextureAtlas.class).createSprite("bgtitlescreen");
    		start = manager.get("data/pack", TextureAtlas.class).createSprite("start");
    		titleLogo = manager.get("data/pack", TextureAtlas.class).createSprite("titlelogo");
    	}
    	
    	if (manager.isLoaded("data/music.mp3")) {
    		music = manager.get("data/music.mp3", Music.class);
    		music.setLooping(true);
    		music.setVolume(0.5f);
    	}
    	
    	if (manager.isLoaded("data/click.ogg"))
    		hitSound = manager.get("data/click.ogg", Sound.class);
    }
    
    public static void playSound(Sound sound) {
        if(Settings.soundEnabled)
            sound.play(1);
    }
}
