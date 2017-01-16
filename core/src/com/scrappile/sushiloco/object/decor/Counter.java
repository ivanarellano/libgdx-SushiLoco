package com.scrappile.sushiloco.object.decor;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.scrappile.sushiloco.Assets;
import com.scrappile.sushiloco.SingleTexture;
import com.scrappile.sushiloco.object.StaticBoxObject;

public class Counter {

	public Array<TextureRegion> textures = new Array<TextureRegion>(3);
	public TextureRegion counterPanel = Assets.atlas.findRegion("counterpanel");

	private int[] offsets = new int[3];

	public Counter() {
		textures.add(Assets.atlas.findRegion("counter1"));
		textures.add(Assets.atlas.findRegion("counter2"));
		textures.add(Assets.atlas.findRegion("counter3"));
		
		//magic
		offsets[0] = 0;
		offsets[1] = 1024;
		offsets[2] = 1792; // w+hw
	}

	public StaticBoxObject buildSingle(float screenPosX, float screenPosY, TextureRegion tex) {
		StaticBoxObject singleCounter = new StaticBoxObject();

		singleCounter.objRep = new SingleTexture();
		singleCounter.objRep.setTexture(tex);

		singleCounter.build(screenPosX, screenPosY, tex.getRegionWidth(), 68.0f);
		singleCounter.setTexturePos(0.0f, -215.0f);

		return singleCounter;
	}

	public List<StaticBoxObject> build(float screenPosX, float screenPosY) {
		StaticBoxObject newBox = new StaticBoxObject();
		List<StaticBoxObject> counterBoxes = new ArrayList<StaticBoxObject>();
		
		for (int i = 0; i < textures.size; i++) {			
			newBox = this.buildSingle(screenPosX + offsets[i], screenPosY, textures.get(i));
			counterBoxes.add(newBox);
		}

		return counterBoxes;
	}

	public SingleTexture buildPanel(float screenPosX, float screenPosY) {
		SingleTexture panelTex = new SingleTexture();
		panelTex.setTexture(counterPanel);
		panelTex.setPos(screenPosX, screenPosY);
		return panelTex;
	}
}
