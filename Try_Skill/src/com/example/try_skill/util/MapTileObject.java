package com.example.try_skill.util;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.try_gameengine.framework.Sprite;
import com.example.try_skill.effect.EffectSprite;
import com.example.try_skill.effect.IEffectable;

public class MapTileObject extends Sprite{
	private EffectSprite battleableSprite;
	
	public MapTileObject(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
	}

	public void setSprite(EffectSprite battleableSprite){
		this.battleableSprite = battleableSprite;
	}
	
	public EffectSprite getSprite(){
		return battleableSprite;
	}
	
	public boolean isTouchXY(float x, float y){
		if(getX() < x && getX() + w > x && getY() < y && getY() + h > y){
			return true;
		}
		return false;
	}
	
	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		super.drawSelf(canvas, paint);
//		if(battleableSprite!=null)
//		battleableSprite.drawSelf(canvas, paint);
	}
	
	@Override
	public void frameTrig() {
		// TODO Auto-generated method stub
		super.frameTrig();
		if(battleableSprite!=null)
		((Sprite)battleableSprite).frameTrig();
	}
}
