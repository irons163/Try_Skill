package com.example.try_skill.effect;

import com.example.try_skill.center_notification.NSANotifiable;

public interface IEffect extends NSANotifiable{
	
	public enum InjureType{
		None, Normal, Frozen, Fire, Heal
	}
	
	interface EffectListener{
		void didEffect(IEffect effect);
	}
	
	public boolean checkEffecting();
	
	public boolean checkEffectable();
	
	public void checkEffect(IEffectable effectable);
	
	public void doEffect(IEffectable effectable);
	
	public void doRemoveEffect(IEffectable effectable);
	
	public IEffect cloneEffect();
	
	public void setEffectListener(EffectListener effectListener);
	
	public InjureType getType();
	
	public int getMaxExistCount();
	
	public void setMaxExistCount(int maxExistCount);
	
	public IEffect getChild();
	public void setChild(IEffect child);
	
	public boolean isUpdateable();
	
	public void setUpdateable(boolean updateable);
	
	public void update();
	
//	interface EffectListener{
//		void didEffect(BattleableSprite battleableSpriteByEffecten);
//	}
//	
//	public void doEffect(WeapenSprite weapenSprite, BattleableSprite battleableSpriteBeAttacked);
//	public void checkEffect(BattleableSprite battleableSprite);
//	
//	public IEffect cloneEffect();
//	
//	public void setEffectListener(EffectListener effectListener);
}
