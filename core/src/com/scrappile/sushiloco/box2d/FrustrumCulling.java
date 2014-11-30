package com.scrappile.sushiloco.box2d;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.scrappile.sushiloco.SushiLoco;

public class FrustrumCulling {
	float zoom = 1.0f;
	private final static float BOUNDS_SCALE = 2.15f;
	
	Vector2 pointPosition = new Vector2();
	
	Rectangle bounds = new Rectangle(0.0f, 0.0f, SushiLoco.SCREEN_WIDTH_BOX2D * BOUNDS_SCALE,
			SushiLoco.SCREEN_HEIGHT_BOX2D * BOUNDS_SCALE);
	
	float halfWidth = bounds.width / 2;
	float halfHeight = bounds.height / 2;
	
	public FrustrumCulling() {
		
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void scale(float scale) {
		zoom += scale;
		
		bounds.width = SushiLoco.SCREEN_WIDTH_BOX2D * BOUNDS_SCALE * zoom;
		bounds.height = SushiLoco.SCREEN_HEIGHT_BOX2D * BOUNDS_SCALE * zoom;
		
		halfWidth = bounds.width / 2;
		halfHeight = bounds.height / 2;		
	}
	
	public void setPosition(float x, float y) {
		bounds.setX(x - halfWidth);
		bounds.setY(y - halfHeight);
	}
	
	protected boolean contains(Rectangle rectangle) {
		return bounds.contains(rectangle);
	}
	
	protected boolean contains(float x, float y) {
		return bounds.contains(x, y);
	}

    protected boolean contains(Vector2 position) {
        return contains(position.x, position.y);
    }
}