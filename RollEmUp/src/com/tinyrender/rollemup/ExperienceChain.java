package com.tinyrender.rollemup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class ExperienceChain {
	public class Level {
		public Level(int tag, int maxScore) {
			this.tag = tag;
			this.maxScore = maxScore;
		}
		
		int tag;
		int maxScore;
	}
	
	public static Level MAX_LEVEL;
	
	int totalScore;
	int currentScore;
	Level currentLevel;
	Array<Level> levelChain = new Array<Level>();
	
	/** Assumes levels are in ascending order. (1..3) */
	public void populate(Level...levels) {
		for (int i = 0; i < levels.length; i++) {
			this.levelChain.add(levels[i]);

			if (i == levels.length - 1)
				MAX_LEVEL = levels[i];
		}
		
		// initialize level chain
		currentLevel = levelChain.get(0);
	}
	
	public void levelUp() {
		int remainder = currentScore - currentLevel.maxScore;
		
		currentLevel = getNextLevel();
		currentScore = remainder;
	}
	
	public int getLevelTag() {
		return currentLevel.tag;
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	
	public Level getNextLevel() {
		if ((currentLevel.tag + 1) <= MAX_LEVEL.tag)
			return levelChain.get(currentLevel.tag++);
		else
			return currentLevel;
	}
	
	public Level getPreviousLevel() {
		if ((currentLevel.tag - 1) >= 0)
			return levelChain.get(currentLevel.tag--);
		else
			return currentLevel;
	}
	
	public void addPoints(int points) {
		currentScore += points;
		totalScore += points;
	}
	
	public void setLevel(int level) {
		
	}
	
	public boolean justLeveledUp() {
		if (currentScore >= currentLevel.maxScore) {
			if (currentLevel.tag < MAX_LEVEL.tag)
				return true;
		}
		
		return false;
	}
	
	public void printCurrentLevel() {
		Gdx.app.log("Level", "(" + currentLevel.tag + ") " + currentScore + "/" + currentLevel.maxScore);
	}
}
