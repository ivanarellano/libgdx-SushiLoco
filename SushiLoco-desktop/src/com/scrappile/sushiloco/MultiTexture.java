package com.scrappile.sushiloco;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class MultiTexture extends ObjectRepresentation {
	protected Array<TextureRegion> textureList = new Array<TextureRegion>();
	private Array<Float> offsetX = new Array<Float>();
	private Array<Float> offsetY = new Array<Float>();
	
	@Override
	public void setTexture(boolean isHorizontal, TextureRegion... texture) {		
		textureList.addAll(texture);
		width = textureList.get(0).getRegionWidth();
		height = textureList.get(0).getRegionHeight();
		
		offsetX.add(0.0f);
		offsetY.add(0.0f);
		for (int i = 1; i < textureList.size; i++) {
			if (isHorizontal) {
				offsetX.add(Float.valueOf(width));
				width += textureList.get(i).getRegionWidth();
			} else {
				offsetY.add(Float.valueOf(height));
				height += textureList.get(i).getRegionHeight();
			}
		}
				
		halfWidth = width / 2.0f;
		halfHeight = height / 2.0f;
	}
	
	@Override
	public void draw() { 
		for (int i = 0; i < textureList.size; i++) {
			Assets.batch.draw(
					textureList.get(i),
					pos.x, pos.y,
					offsetX.get(i) + textureList.get(i).getRegionWidth(),
					offsetY.get(i) + textureList.get(i).getRegionHeight());			
		}
	}
}

/*
Assets.batch.draw(texture,
		(gameObj.pos.x - (halfWidth / Level.PTM_RATIO)) * Level.PTM_RATIO,
		(gameObj.pos.y - (halfHeight / Level.PTM_RATIO)) * Level.PTM_RATIO,
		halfWidth, halfHeight,
		width, height,
		1.0f, 1.0f,
		gameObj.rot);
*/