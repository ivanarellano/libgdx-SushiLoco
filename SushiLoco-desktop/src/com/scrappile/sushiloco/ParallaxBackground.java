package com.scrappile.sushiloco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ParallaxBackground {
	private ParallaxLayer[] layers;
	private ParallaxLayer curLayer;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Vector2 speed = new Vector2();
	
	final static float scale = 2.5f;
	
	float zoom = 1.0f;
	
	/**
	* @param layers		Background layers 
	* @param width		Screen width 
	* @param height		Screen height
	* @param speed		Vector2 attribute for x,y speed
	*/
	public ParallaxBackground(ParallaxLayer[] layers, float width, float height, Vector2 speed) {
		this.layers = layers;
		this.speed.set(speed);
		camera = new OrthographicCamera(width * scale, height * scale);
		batch = new SpriteBatch();
	}

	public void render(float delta) {
		this.camera.position.add(speed.x*delta, speed.y*delta, 0);
		
		camera.update(false);
		
		batch.setProjectionMatrix(camera.projection);
		batch.begin();
		
		for (int i = 0; i < layers.length; i++) {
			curLayer = layers[i];
			
			float currentX = -camera.position.x*curLayer.parallaxRatio.x 
					% (curLayer.region.getRegionWidth() + curLayer.padding.x);

			if (speed.x < 0.1f)
				currentX += -(curLayer.region.getRegionWidth() + curLayer.padding.x);
		
			do {
				float currentY = -camera.position.y*curLayer.parallaxRatio.y
				 	% (curLayer.region.getRegionHeight() + curLayer.padding.y);
		
				if (speed.y < 0.1f)
					currentY += -(curLayer.region.getRegionHeight() + curLayer.padding.y);
			
				do {
					batch.draw(curLayer.region,
					-this.camera.viewportWidth/2 + currentX + curLayer.startPosition.x,
					-this.camera.viewportHeight/2 + currentY + curLayer.startPosition.y);
				
					currentY += (curLayer.region.getRegionHeight() + curLayer.padding.y );
			
				} while (currentY < camera.viewportHeight);
			
				currentX += (curLayer.region.getRegionWidth() + curLayer.padding.x);
			
			} while (currentX < camera.viewportWidth);
		}
			
		batch.end();
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public void setSpeed(float x, float y) {
		this.speed = speed.set(x, y);
	}
	
	public void setZoom(float zoom) {
		this.camera.zoom += zoom;
		//this.zoom += zoom;
		//camera.viewportWidth = SushiLoco.SCREEN_WIDTH * scale * this.zoom;
		//camera.viewportHeight = SushiLoco.SCREEN_HEIGHT * scale * this.zoom;
		//Gdx.app.log("zoom", ""+camera.viewportWidth + "x" + camera.viewportHeight);
	}
}
