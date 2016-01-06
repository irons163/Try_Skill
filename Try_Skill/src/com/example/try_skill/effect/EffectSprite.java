package com.example.try_skill.effect;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.utils.DetectAreaRound;
import com.example.try_gameengine.utils.SpriteDetectAreaHandler;

public class EffectSprite extends Sprite implements IEffectable{
	private AttributeInfo attributeInfo;
	protected boolean isNeedRemove = false;
	protected boolean isBattleable = true;
	protected float battleRange = 100f;
	
	protected List<IEffect> effects = new ArrayList<IEffect>();
	
	public EffectSprite(Bitmap bitmap, int w, int h, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		// TODO Auto-generated constructor stub
		initDefaultValue();
	}

	public EffectSprite(float x, float y, boolean autoAdd) {
		// TODO Auto-generated constructor stub
		super(x, y, autoAdd);
		initDefaultValue();
	}
	
	private void initDefaultValue(){
		attributeInfo = new AttributeInfo();
		attributeInfo.setAtk(10);
		attributeInfo.setDef(5);
		attributeInfo.setHp(20);
		
		SpriteDetectAreaHandler spriteDetectAreaHandler = new SpriteDetectAreaHandler();
		spriteDetectAreaHandler.addSuccessorDetectArea(new DetectAreaRound(new PointF(getCenterX(), getCenterY()), 100));
		spriteDetectAreaHandler.apply();
		setSpriteDetectAreaHandler(spriteDetectAreaHandler);
	}
	
	public void addEffect(IEffect effect){
		effects.add(effect);
	}
	
	public void addEffects(List<IEffect> effects){
		this.effects.addAll(effects);
	}
	
	public void removeEffect(IEffect effect){
		effects.remove(effect);
	}
	
	public void removeEffects(List<IEffect> effects){
		this.effects.removeAll(effects);
	}
	
	protected void setEffectListener(IEffect effect) {
		
		effect.setEffectListener(new IEffect.EffectListener() {

			@Override
			public void didEffect(IEffect effect) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public void checkIfInBattleRangeThenAttack(List<EffectSprite> battleables){
		for(EffectSprite effectSprite : battleables){
			if(getSpriteDetectAreaHandler().detectByPoint(new PointF(effectSprite.getCenterX(), effectSprite.getCenterY()))){
				for(IEffect effect : effects){
					effectSprite.getAttributeInfo().addToEffectStatusList(effect.cloneEffect());
				}		
				break;
			}
		}
	}

	public boolean isBattleable(){
		return isBattleable;
	}
	
	public void setIsBattleable(boolean isBattleable){
		this.isBattleable = isBattleable;
	}
	
	@Override
	public AttributeInfo getAttributeInfo(){
		return attributeInfo;
	}
	
	public boolean isNeedRemove(){
		return isNeedRemove;
	}

	@Override
	public void setBattleRange(float battleRange) {
		// TODO Auto-generated method stub
		this.battleRange = battleRange;
	}

	@Override
	public AttributeInfo getNewAttributeInfo(AttributeInfo attributeInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	private void doEffectSelf(){
		List<IEffect> removeEffects = new ArrayList<IEffect>();
		for(IEffect effect : getAttributeInfo().getEffectStatusList()){
			if(effect.checkEffecting() && effect.checkEffectable()){
				effect.doEffect(this);
			}else if(!effect.checkEffecting())
				removeEffects.add(effect);
		}
		
		getAttributeInfo().removeFromEffectStatusList(this, removeEffects);
	}

	@Override
	public void doAttachedEffects() {
		// TODO Auto-generated method stub
		doEffectSelf();
		if(getAttributeInfo().isChanged())
			updateInfoFromAtrributeInfoDetail();
		getAttributeInfo().setChanged(false);
	}
	
	public void updateInfoFromAtrributeInfoDetail(){

	}
}
