package com.scrappile.sushiloco;

import com.badlogic.gdx.physics.box2d.World;

public interface ObjectFactory {
	public abstract GameObject build(float x, float y, World world);
}