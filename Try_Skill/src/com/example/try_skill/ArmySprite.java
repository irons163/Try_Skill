package com.example.try_skill;

import com.example.try_gameengine.framework.Sprite;

public class ArmySprite extends Sprite{
	private boolean isEnemy = false;

	public ArmySprite(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
	}

	public void setIsEnemy(boolean isEnemy){
		this.isEnemy = isEnemy;
	}
	
	public boolean isEnemy(){
		return isEnemy;
	}
}
