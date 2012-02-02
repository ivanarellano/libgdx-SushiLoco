package com.tinyrender.rollemup;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class RollEmUpActivity extends AndroidApplication {
	private PowerManager.WakeLock wakeLock;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new RollEmUp(), false);
        
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, getClass().getName());
    }
    
    @Override
    protected void onPause() {
    	wakeLock.release();
    	super.onPause();
    }
    
    @Override
    protected void onResume() {
    	wakeLock.acquire();
    	super.onResume();
    }
}