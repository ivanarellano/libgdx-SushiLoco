package com.tinyrender.androidgame.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Assets implements AssetErrorListener {
	public static AssetManager manager;
	
	public static TextureAtlas atlas;
	public static AtlasRegion levelSelectMap;
	public static AtlasRegion soundOff;
	public static AtlasRegion soundOn;
	public static AtlasRegion splashScreen;
	public static AtlasRegion start;
	public static AtlasRegion titleLogo;
	
	public static SpriteBatch batch;
    
    public static void create() {
    	FileHandleResolver resolver = new InternalFileHandleResolver();
    	manager = new AssetManager();
    	manager.setLoader(Texture.class, new TextureLoader(resolver));
    	batch = new SpriteBatch();
    	load();
    	Texture.setAssetManager(manager);
    }
    
    private static void load() {
    	manager.load("assets/data/pack", TextureAtlas.class);
    	
    	manager.finishLoading();
    	
    	if (Assets.manager.isLoaded("assets/data/pack")) {
    		levelSelectMap = new AtlasRegion(manager.get("assets/data/pack", TextureAtlas.class).findRegion("levelselect"));
    		soundOff = new AtlasRegion(manager.get("assets/data/pack", TextureAtlas.class).findRegion("soundoff"));
    		soundOn = new AtlasRegion(manager.get("assets/data/pack", TextureAtlas.class).findRegion("soundon"));
    		splashScreen = new AtlasRegion(manager.get("assets/data/pack", TextureAtlas.class).findRegion("splash1"));
    		start = new AtlasRegion(manager.get("assets/data/pack", TextureAtlas.class).findRegion("start"));
    		titleLogo = new AtlasRegion(manager.get("assets/data/pack", TextureAtlas.class).findRegion("title"));
    	}
    }
    
    private static void unload() {
    	manager.unload("assets/data/pack");
    }
    
    public static void playSound(Sound sound) {
        if(Settings.soundEnabled)
            sound.play(1);
    }

	@Override
	public void error(String fileName, Class type, Throwable t) {
		Gdx.app.error("AssetManagerTest", "couldn't load asset '" + fileName + "'", (Exception)t);		
	}
}
