package com.scrappile.sushiloco.object;

import com.scrappile.sushiloco.GameObject;


public interface ObjectFactory {
	public abstract GameObject build(float screenPosX, float screenPosY);
}