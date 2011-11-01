package com.tinyrender.rollemup.object;

import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.rollemup.GameObject;

public interface ObjectFactory {
	public abstract GameObject build(float x, float y, World world);
}