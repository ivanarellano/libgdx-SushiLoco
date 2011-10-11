package com.tinyrender.rollemup;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LevelGui extends Gui {
	public class GoalMeter {
		public float scale;
		public float width;
		public float height;
		public TextureRegion texture;
		
		public void setTexture(TextureRegion texture) {
			this.texture = texture;
			width = texture.getRegionWidth();
			height = texture.getRegionHeight();
		}
		
		public void draw() {
			Assets.batch.draw(goalMeter.texture,
					(-width * goalMeter.scale) /2.0f, RollEmUp.TARGET_HEIGHT -(height * goalMeter.scale) / 2.0f,
					width * goalMeter.scale / 2.0f, height * goalMeter.scale / 2.0f,
					width * goalMeter.scale, 
					height * goalMeter.scale,
					1.0f, 1.0f,
					0.0f);
		}
	}
	
	public GoalMeter goalMeter;
    public Timer timer;

	public LevelGui() {
		goalMeter = new GoalMeter();
		timer = new Timer();
	}

	@Override
	public void ready(float deltaTime) {		
	}
	
	/*
	 *	SpriteBatch.draw()
	 *  params: textureRegion, x, y, originX, originY, width, height, scaleX, scaleY, rotation
	 */
	public void running(float deltaTime) {
		timer.update();
		
		Assets.batch.setProjectionMatrix(cam.combined);
		Assets.batch.begin();
			timer.draw();
			goalMeter.draw();
		Assets.batch.end();
	}

	@Override
	public void paused(float deltaTime) {		
	}

	@Override
	public void levelEnd(float deltaTime) {		
	}

	@Override
	public void gameOver(float deltaTime) {		
	}
}
