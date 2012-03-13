package com.scrappile.sushiloco;

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

public class Assets {
	public static AssetManager manager;
	public static AssetErrorListener managerError;
	public static TextureAtlas atlas;
	public static SpriteBatch batch;
	
	public static BitmapFont droidSansFont;
    
    public static void create() {
    	manager = new AssetManager();
    	managerError = new AssetErrorListener() {
			@Override
			public void error(String fileName, Class type, Throwable t) {
				Gdx.app.error("AssetManagerTest", "couldn't load asset '" + fileName + "'", (Exception)t);						
			}
		};
		
    	manager.setErrorListener(managerError);
    	Texture.setAssetManager(manager);
    	init();
    	
    	batch = new SpriteBatch();
    }
    
    private static void init() {
    	manager.load("data/pack", TextureAtlas.class);
    	manager.finishLoading();
    	atlas = manager.get("data/pack", TextureAtlas.class);
    }
    
    public static void loadTexture(String...src) {
        for (String file : src)
           manager.load(file, Texture.class);
    }
    
    public static void loadBitmapFont(String...src) {
        for (String file : src)
           manager.load(file, BitmapFont.class);
    }
    
    public static void loadMusic(String...src) {
        for (String file : src)
           manager.load(file, Music.class);
    }
    
    public static void loadSound(String...src) {
        for (String file : src)
           manager.load(file, Sound.class);
    }
    
    public static Texture getTexture(String src) {
        return manager.get(src, Texture.class);  
    }
    
    public static BitmapFont getBitmapFont(String src, String fontRegion) {
		BitmapFontData bfd = new BitmapFontData(Gdx.files.internal(src), false);
		
        return new BitmapFont(bfd, atlas.findRegion(fontRegion), false);  
    }
    
    public static Music getMusic(String src) {
    	return manager.get(src, Music.class);
    }
    
    public static Sound getSound(String src) {
    	return manager.get(src, Sound.class);
    }
    
    public static void unload(String...toUnload) {
    	for (String file : toUnload)
    		manager.unload(file);
    }
    
    public static void playSound(Sound sound) {
        if (Settings.soundEnabled)
            sound.play(1);
    }
}
