package com.scrappile.sushiloco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {
    private static final String LOG = Assets.class.getSimpleName();
	public static AssetManager manager;
	public static TextureAtlas atlas;
	public static SpriteBatch batch;
	
	public static BitmapFont droidSansFont;
    
    public Assets() {
        manager = new AssetManager();
        manager.setErrorListener(this);
        Texture.setAssetManager(manager);

        manager.load("data/pack", TextureAtlas.class);
        manager.finishLoading();

        atlas = manager.get("data/pack", TextureAtlas.class);

        batch = new SpriteBatch();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(LOG, "couldn't load asset '" + asset + "'", throwable);
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

    /**
     * Releases all resources of this object.
     */
    @Override
    public void dispose() {
        manager.dispose();
        batch.dispose();
    }
}
