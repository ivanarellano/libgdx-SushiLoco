package com.tinyrender.rollemup;

import java.util.LinkedList;
import java.util.Queue;

public class PlayerXP {
	public class Level {
		public Level(int tag, int score) {
			this.tag = tag;
			this.score = score;
		}
		
		public int tag;
		public int score;
	}
	
	public static int MAX_SCORE;
	public Level currentLevel;
	public Level nextLevel;
	
	public Queue<Level> levels = new LinkedList<Level>();
	
	public void populate(Level...newLevels) {
		for (int i = 0; i < newLevels.length; i++) {
			levels.add(newLevels[i]);

			if (i == newLevels.length - 1)
				MAX_SCORE = newLevels[i].score;
		}
	}
	
	public void levelUp() {
		currentLevel = levels.poll();
		
		if (levels.iterator().hasNext())
			nextLevel = levels.iterator().next();
	}
}
