package com.example.try_skill.effect;

import java.util.List;

import com.example.try_gameengine.framework.Sprite;

public interface IEffectable {
	
//	public boolean checkIfInBattleRangeThenAttack(List<IEffectable> battleables);
	
	public void beAttacked(Sprite weapenSprite);
	
	public void beAttacked(IEffect effect);
	
	public void doAttachedEffects();
	
	public void setBattleRange(float atkR);
	
	public AttributeInfo getAttributeInfo();
	
	public AttributeInfo getNewAttributeInfo(AttributeInfo attributeInfo);
}
