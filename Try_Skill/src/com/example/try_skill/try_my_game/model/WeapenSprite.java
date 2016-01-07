package com.example.try_skill.try_my_game.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import android.graphics.PointF;
import android.util.Log;

import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.utils.DetectAreaRound;
import com.example.try_skill.effect.AttributeInfo;
import com.example.try_skill.effect.EffectSprite;
import com.example.try_skill.effect.IEffect;
import com.example.try_skill.effect.IEffectable;
import com.example.try_skill.util.Attribute;
import com.example.try_skill.util.AttributeHelper;

public class WeapenSprite extends EffectSprite{
	protected AttributeInfo attributeInfo;
	public static final float NO_ATK_RANGE = 0;
	
	protected Attribute attribute;
	protected AttributeHelper attributeHelper;
	protected long lastShootTime;
	protected float interval = -1;
	
	public WeapenSprite(float x, float y, boolean autoAdd) {
//		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
		this(x, y, autoAdd, -1);
	}
	
	public WeapenSprite(float x, float y, boolean autoAdd, float interval) {
		super(x, y, autoAdd);
		
		this.interval = interval;
		initAttribute();
	}
	
	protected void initAttribute() {
		attribute = new Attribute();
		
		if(interval<=-1){
			interval = new BigDecimal(1.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
		attribute.setInterval(interval);
		attributeHelper = new AttributeHelper(attribute);
	}

//	@Override
//	public AttributeInfo getNewAttributeInfo(AttributeInfo attributeInfo) {
//		// TODO Auto-generated method stub
//		AttributeInfo attributeInfoNew = new AttributeInfo(attributeInfo);
//		attributeInfoNew.setAtk(attributeInfoNew.getAtk()+5);
//		return attributeInfoNew;
//	}	
	
	public void addWeapenEffect(String key, IEffect effect){
		effects.add(effect);
//		effects.put(key, effect);
	}
	
	public void addWeapenEffect(IEffect effect){
		effects.add(effect);
//		effects.put(key, effect);
	}

	@Override
	public AttributeInfo getAttributeInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttributeInfo getNewAttributeInfo(AttributeInfo attributeInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doAttachedEffects() {
		// TODO Auto-generated method stub
		
	}

}
