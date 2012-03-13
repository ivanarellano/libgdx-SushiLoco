package com.scrappile.sushiloco;

import com.badlogic.gdx.backends.lwjgl.LwjglApplet;

public class SushiLocoApplet extends LwjglApplet
{
    private static final long serialVersionUID = 1L;
    public SushiLocoApplet()
    {
        super(new SushiLoco(), false);
    }
}