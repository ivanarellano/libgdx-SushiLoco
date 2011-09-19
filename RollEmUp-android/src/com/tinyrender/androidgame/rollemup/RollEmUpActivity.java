package com.tinyrender.androidgame.rollemup;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.tinyrender.rollemup.RollEmUp;

public class RollEmUpActivity extends AndroidApplication {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new RollEmUp(), false);
    }
}