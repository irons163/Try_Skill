package com.example.try_skill.try_my_game.model;

import com.example.try_skill.effect.EffectSprite;

public class MoveableEffectSprite extends EffectSprite{
	protected float moveAngle;
	
	public MoveableEffectSprite(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
	}
	
	protected void setMoveAngle(float moveAngle){
		this.moveAngle = moveAngle;
	}
	
	public float getMoveAngle(){
		return moveAngle;
	}
}
