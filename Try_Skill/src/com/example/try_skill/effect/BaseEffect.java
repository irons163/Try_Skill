package com.example.try_skill.effect;

import com.example.try_skill.center_notification.NSANotification;

public abstract class BaseEffect implements IEffect{
	protected EffectListener effectListener;
	protected InjureType injureType;
	protected int effectTimes = 1;
	protected int currentEffectTimes = 0;
	protected int dmg = 0;
	protected IntervalTimer intervalTimer;
//	protected EffectListener effectListener;
	private int maxExistCount = 1;
	private IEffect child;
	private boolean updateable;
	
	@Override
	public boolean checkEffecting() {
		if(currentEffectTimes >= effectTimes && (intervalTimer.isLastTimeDelay()?intervalTimer.isCanShoot():true))
			return false;
		return true;
	}
	
	@Override
	public boolean checkEffectable() {
		if(intervalTimer.isCanShoot() && currentEffectTimes < effectTimes){
//			battleableSprite.injure(battleSpriteInjureType);
//			currentEffectTimes++;
			return true;
		}else if(currentEffectTimes >= effectTimes){
			return false;
		}
		return false;
	}
	
	@Override
	public void doEffect(IEffectable effectable) {
		// TODO Auto-generated method stub
		currentEffectTimes++;
		intervalTimer.executeAndGotoColdTime();
		if(effectListener!=null)
			effectListener.didEffect(this);
		if(getChild()!=null)
			effectable.getAttributeInfo().addToEffectStatusList(getChild());
		
		// TODO Auto-generated method stub
//		if(!effectable.getAttributeInfo().checkIsAlreadyHasTheSameEffect(this)){
//			addEffectAndDoEffectDetail(weapenSprite, battleableSpriteBeAttacked);
//			battleableSpriteBeAttacked.getAttributeInfo().addToEffectStatusList(this);
//		}
//		weapenSprite.beAttacked(weapenSprite);
		
//		effectListener.didEffect(battleableSpriteBeAttacked);
//		battleableSpriteBeAttacked.injure(battleSpriteInjureType);
	}
	
	@Override
	public void setEffectListener(EffectListener effectListener) {
		// TODO Auto-generated method stub
		this.effectListener = effectListener;
	}
	
	@Override
	public void checkEffect(IEffectable effectable) {
		// TODO Auto-generated method stub
		if(intervalTimer.isCanShoot() && currentEffectTimes < effectTimes){
//			battleableSprite.injure(battleSpriteInjureType);
			currentEffectTimes++;
		}else if(currentEffectTimes >= effectTimes){
//			removeEffectAndDoEffectDetail(effectable);
//			effectable.getAttributeInfo().removeFromEffectStatusList(this);
		}
	}
	
	@Override
	public InjureType getType() {
		// TODO Auto-generated method stub
		return injureType;
	}

	@Override
	public void receiveNotification(NSANotification nsaNotification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxExistCount() {
		// TODO Auto-generated method stub
		return maxExistCount;
	}

	@Override
	public void setMaxExistCount(int maxExistCount) {
		// TODO Auto-generated method stub
		this.maxExistCount = maxExistCount;
	}

	@Override
	public IEffect getChild() {
		// TODO Auto-generated method stub
		return child;
	}

	@Override
	public void setChild(IEffect child) {
		// TODO Auto-generated method stub
		this.child = child;
	}

	public boolean isUpdateable() {
		return updateable;
	}

	public void setUpdateable(boolean updateable) {
		this.updateable = updateable;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doRemoveEffect(IEffectable effectable) {
		// TODO Auto-generated method stub
		if(getChild()!=null && effectable.getAttributeInfo().containEffectInStatusList(getChild())){
			effectable.getAttributeInfo().removeFromEffectStatusList(effectable, getChild());
		}
	}
	
//	protected BattleSpriteInjureType battleSpriteInjureType = BattleSpriteInjureType.Fire;
//	protected int effectTimes = 1;
//	protected int currentEffectTimes = 0;
//	protected int dmg = 0;
//	protected IntervalTimer intervalTimer;
//	protected EffectListener effectListener;
//	
//	@Override
//	public void doEffect(WeapenSprite weapenSprite,
//			BattleableSprite battleableSpriteBeAttacked) {
//		// TODO Auto-generated method stub
//		if(!battleableSpriteBeAttacked.getAttributeInfo().checkIsAlreadyHasTheSameEffect(this)){
//			addEffectAndDoEffectDetail(weapenSprite, battleableSpriteBeAttacked);
//			battleableSpriteBeAttacked.getAttributeInfo().addToEffectStatusList(this);
//		}
////		weapenSprite.beAttacked(weapenSprite);
//		effectListener.didEffect(battleableSpriteBeAttacked);
//		battleableSpriteBeAttacked.injure(battleSpriteInjureType);
//	}
//	
//	protected abstract void addEffectAndDoEffectDetail(WeapenSprite weapenSprite,
//			BattleableSprite battleableSpriteBeAttacked);
//	
//	protected abstract void removeEffectAndDoEffectDetail(BattleableSprite battleableSprite);
//
//	@Override
//	public void checkEffect(BattleableSprite battleableSprite) {
//		// TODO Auto-generated method stub
//		if(intervalTimer.isCanShoot() && currentEffectTimes < effectTimes){
////			battleableSprite.injure(battleSpriteInjureType);
//			currentEffectTimes++;
//		}else if(currentEffectTimes >= effectTimes){
//			removeEffectAndDoEffectDetail(battleableSprite);
//			battleableSprite.getAttributeInfo().removeFromEffectStatusList(this);
//		}
//	}
//
//	@Override
//	public IEffect cloneEffect() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void setEffectListener(EffectListener effectListener) {
//		// TODO Auto-generated method stub
//		this.effectListener = effectListener;
//	}
}
