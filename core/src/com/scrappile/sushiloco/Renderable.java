package com.scrappile.sushiloco;

import com.badlogic.gdx.math.Vector2;

abstract public class Renderable implements Drawable {

	protected Vector2 pos = new Vector2();
	
	@Override
	public void draw() {
		// No-op
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
	}

	public void setPos(float x, float y) {
		pos.set(x, y);
	}

	public void scale(float factor) {
		pos.scl(factor);
	}
}