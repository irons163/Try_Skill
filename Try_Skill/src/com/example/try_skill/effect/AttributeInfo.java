package com.example.try_skill.effect;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.try_skill.effect.IEffect.InjureType;

public class AttributeInfo {
	private float hp;
	private float atk;
	private float def;
	private float battleInviable = 1.0f;
	private boolean isChanged = false;

	private List<IEffect> effectStatusList = new CopyOnWriteArrayList<IEffect>();

	public AttributeInfo() {

	}

	public AttributeInfo(AttributeInfo attributeInfo) {
		this.hp = attributeInfo.hp;
		this.atk = attributeInfo.atk;
		this.def = attributeInfo.def;
		this.battleInviable = attributeInfo.battleInviable;
	}

	public float getHp() {
		return hp;
	}

	public void setHp(float hp) {
		this.hp = hp;
		setChanged(true);
	}

	public float getAtk() {
		return atk;
	}

	public void setAtk(float atk) {
		this.atk = atk;
		setChanged(true);
	}

	public float getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
		setChanged(true);
	}

	public float getBattleInviable() {
		return battleInviable;
	}

	public void setBattleInviable(float battleInviable) {
		this.battleInviable = battleInviable;
		setChanged(true);
	}

	public void removeAllFromEffectStatusList(IEffectable effectable) {
		for(int i = effectStatusList.size()-1; i >= 0; i-- ){
			IEffect effect = effectStatusList.get(i);
			effect.doRemoveEffect(effectable);
		}
		effectStatusList.clear();
		setChanged(true);
	}
	
	

//	public void removeTheSameEffectFromEffectStatusList(IEffect effect) {
//		for (IEffect efIEffect : effectStatusList) {
//			if (efIEffect.getType() == effect.getType()) {
//				effectStatusList.remove(effect);
//				break;
//			}
//		}
//	}



	public boolean checkIsAlreadyHasTheSameEffect(IEffect effect) {
		boolean alreadyHasTheSameEffect = false;
		for (IEffect efIEffect : effectStatusList) {
			if (efIEffect.getType() == effect.getType()) {
				alreadyHasTheSameEffect = true;
				break;
			}
		}
		return alreadyHasTheSameEffect;
	}

	public boolean checkHasEffectOrNotByEffectType(
			InjureType battleSpriteInjureType) {
		boolean hasEffect = false;
		for (IEffect efIEffect : effectStatusList) {
			if (efIEffect.getType() == battleSpriteInjureType) {
				hasEffect = true;
				break;
			}
		}
		return hasEffect;
	}

	private int checkHasHowManyTheSameEffectByEffectType(
			InjureType battleSpriteInjureType) {
		int effectCount = 0;
		for (IEffect effect : effectStatusList) {
			if (effect.getType() == battleSpriteInjureType) {
				effectCount++;
			}
		}
		return effectCount;
	}
	
	private int checkHasHowManyTheSameEffect(
			IEffect effectForCheck) {
		int effectCount = 0;
		for (IEffect effect : effectStatusList) {
			if (effect.getType() == effectForCheck.getType()) {
				effectCount++;
			}
		}
		return effectCount;
	}
	
//	public void checkAllEffect(IEffectable battleableSprite) {
//		for (IEffect efIEffect : effectStatusList) {
//			efIEffect.checkEffect(battleableSprite);
//		}
//	}

	public List<IEffect> getEffectStatusList() {
		return effectStatusList;
	}
	
	public void addToEffectStatusList(IEffect effect) {
		if(effect.getMaxExistCount()>checkHasHowManyTheSameEffect(effect)){
			effectStatusList.add(effect);
			setChanged(true);
		}else if(effect.isUpdateable()){
			IEffect oldestEffectForSpecificType = getOldestEffectByEffectType(effect.getType());
			oldestEffectForSpecificType.update();
			setChanged(true);
		}
	}
	
	private IEffect getOldestEffectByEffectType(InjureType type){
		IEffect oldestEffectForSpecificType = null;
		for (IEffect effect : effectStatusList) {
			if (effect.getType() == type) {
				oldestEffectForSpecificType = effect;
				break;
			}
		}
		return oldestEffectForSpecificType;
	}

	public void removeFromEffectStatusList(IEffectable effectable, IEffect effect) {
		setChanged(effectStatusList.remove(effect)  || isChanged());
		effect.doRemoveEffect(effectable);
	}
	
	public void removeFromEffectStatusList(IEffectable effectable, List<IEffect> removeEffects) {
		for(int i = removeEffects.size()-1; i >= 0; i-- ){
			IEffect effect = removeEffects.get(i);
			effect.doRemoveEffect(effectable);
		}
		
		setChanged(effectStatusList.removeAll(removeEffects) || isChanged());
	}
	
	public boolean containEffectInStatusList(IEffect effect){
		return effectStatusList.contains(effect);
	}
	
	public boolean isChanged() {
		return isChanged;
	}

	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
}
