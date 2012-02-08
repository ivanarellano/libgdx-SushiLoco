package com.tinyrender.rollemup.box2d;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.tinyrender.rollemup.RollEmUp;

public class FrustrumCulling {
	float zoom = 1.0f;
	private float boundsScale = 2.15f;
	
	Vector2 bodyPosition = new Vector2();
	
	Rectangle bounds = new Rectangle(0.0f, 0.0f, RollEmUp.SCREEN_WIDTH_BOX2D * boundsScale,
			RollEmUp.SCREEN_HEIGHT_BOX2D * boundsScale);
	float halfWidth = bounds.width / 2;
	float halfHeight = bounds.height / 2;
	
	public FrustrumCulling() {
		
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void scale(float scale) {
		zoom += scale;
		
		bounds.width = RollEmUp.SCREEN_WIDTH_BOX2D * boundsScale * zoom;
		bounds.height = RollEmUp.SCREEN_HEIGHT_BOX2D * boundsScale * zoom;
		
		halfWidth = bounds.width / 2;
		halfHeight = bounds.height / 2;		
	}
	
	public void setPosition(float x, float y) {
		bounds.setX(x - halfWidth);
		bounds.setY(y - halfHeight);
	}
	
	protected boolean isInFrustrum(Body body) {
		bodyPosition.set(body.getPosition().x, body.getPosition().y);
		
		if (bounds.contains(bodyPosition.x, bodyPosition.y))
			return true;
		
		return false;
	}
	
}