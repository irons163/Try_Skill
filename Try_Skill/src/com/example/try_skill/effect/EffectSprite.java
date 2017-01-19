package com.example.try_skill.effect;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.utils.DetectArea;
import com.example.try_gameengine.utils.DetectAreaPoint;
import com.example.try_gameengine.utils.DetectAreaRect;
import com.example.try_gameengine.utils.DetectAreaRound;
import com.example.try_gameengine.utils.SpriteDetectAreaHandler;

public class EffectSprite extends Sprite implements IEffectable{
	public static final int UNLIMIT_ATTACK_TARGET_COUNT = Integer.MAX_VALUE;
	private AttributeInfo attributeInfo;
	protected boolean isNeedRemove = false;
	protected boolean isBattleable = true;
	protected float battleRange = 0;
	protected int attackTargeCounttMaxLimit = 1;
//	protected boolean isSpreadEffect;
	protected float effectSpreadRange;
	
	protected List<IEffect> effects = new ArrayList<IEffect>(); // main effects.
	protected List<IEffect> spreadEffects = new ArrayList<IEffect>(); // spread effects, if size=0, use main effects for spread.
	
	protected DetectArea battleRangeDetectArea;
	
	protected SpriteDetectAreaHandler effectRangeDetectAreaHandler;
	
	protected BeAttackedRangeType beAttackedRangeType;
	
	protected DetectArea beAttackedRangeDetectArea;
	protected float beAttackedRange;
	
	public EffectSprite(Bitmap bitmap, int w, int h, boolean autoAdd) {
		super(bitmap, w, h, autoAdd);
		// TODO Auto-generated constructor stub
		initDefaultAttributeInfo();
		initDefaultBattleRange();
		initDefaultBeAttackedRange();
	}

	public EffectSprite(float x, float y, boolean autoAdd) {
		// TODO Auto-generated constructor stub
		super(x, y, autoAdd);
		initDefaultAttributeInfo();
		initDefaultBattleRange();
		initDefaultBeAttackedRange();
	}
	
	protected void initDefaultAttributeInfo(){
		attributeInfo = new AttributeInfo();
		attributeInfo.setAtk(10);
		attributeInfo.setDef(5);
		attributeInfo.setHp(20);	
	}
	
	protected void initDefaultBattleRange(){
		setBattleRange(battleRange);
	}
	
	protected void initDefaultBeAttackedRange(){
		setBeAttackedRangeType(BeAttackedRangeType.RangeBySelfCenterPoint);
	}
	
	public enum BeAttackedRangeType{
		RangeBySelfCenterPoint, RangeBySelfCollisiontRectF, RangeBySelfFrame, RangeBySelfBeAttackedRange,
		RangeByParentCenterPoint, RangeByParentCollisiontRectF, RangeByParentFrame, RangeByParentBeAttackedRange
	}
	
	public void setBeAttackedRangeType(BeAttackedRangeType beAttackedRangeType){
		this.beAttackedRangeType = beAttackedRangeType;
		switch (beAttackedRangeType) {
		case RangeBySelfCenterPoint:
			beAttackedRangeDetectArea = new DetectAreaPoint(new PointF(getCenterX(), getCenterY()));
			break;
		case RangeBySelfCollisiontRectF:
			beAttackedRangeDetectArea = new DetectAreaRect(getCollisionRectF());
			break;
		case RangeBySelfFrame:
			beAttackedRangeDetectArea = new DetectAreaRect(getFrame());
			break;
		case RangeBySelfBeAttackedRange:
			if(beAttackedRange>0)
				beAttackedRangeDetectArea = new DetectAreaRound(new PointF(getCenterX(), getCenterY()), beAttackedRange);
			else
				beAttackedRangeDetectArea = new DetectAreaPoint(new PointF(getCenterX(), getCenterY()));
			break;
		case RangeByParentCenterPoint:
			
			break;
		case RangeByParentCollisiontRectF:
//			beAttackedRangeDetectArea = new DetectAreaRect(getCollisionRectF());
			break;
		case RangeByParentFrame:
			
			break;
		case RangeByParentBeAttackedRange:
			
			break;
		}
		
	}
	
	public void setBeAttackedRangeWithDetectArea(DetectArea detectArea){
		beAttackedRange = 0;
		beAttackedRangeDetectArea = detectArea;
		this.beAttackedRangeType = BeAttackedRangeType.RangeBySelfBeAttackedRange;
	}
	
	public void setBeAttackedRange(float beAttackedRange){
		if(this.beAttackedRange==beAttackedRange)
			return;
		this.beAttackedRange = beAttackedRange;
		setBeAttackedRangeType(BeAttackedRangeType.RangeBySelfBeAttackedRange);
	}
	
	public DetectArea getBeAttackedRangeWithDetectArea(){
		return beAttackedRangeDetectArea;
	}
	
	public float getBeAttackedRange(){
		return beAttackedRange;
	}
	
	@Override
	public void updateSpriteDetectAreaCenter(PointF center) {
		// TODO Auto-generated method stub
		if(beAttackedRangeDetectArea!=null){
			if(beAttackedRangeType==BeAttackedRangeType.RangeBySelfCenterPoint){
				beAttackedRangeDetectArea.setCenter(center);
			}else if(beAttackedRangeType==BeAttackedRangeType.RangeBySelfBeAttackedRange){
				beAttackedRangeDetectArea.setCenter(center);
			}else if(beAttackedRangeType==BeAttackedRangeType.RangeBySelfBeAttackedRange){
				beAttackedRangeDetectArea.setCenter(center);
			}
		}
			
		
		if(effectRangeDetectAreaHandler!=null)
			effectRangeDetectAreaHandler.updateSpriteDetectAreaCenter(center);
		
		super.updateSpriteDetectAreaCenter(center);
	}
	
	public SpriteDetectAreaHandler getEffectRangeDetectAreaHandler() {
		// TODO Auto-generated method stub
		return effectRangeDetectAreaHandler;
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
	
	protected void attack(IEffectable effectSprite) {
		// TODO Auto-generated method stub
		for(IEffect effect : effects){
			effectSprite.getAttributeInfo().addToEffectStatusList(effect.cloneEffect());
		}
	}
	
	public boolean checkIfInBattleRangeThenAttack(List<EffectSprite> battleables){
		List<EffectSprite> effectSpritesByAttecked = null;
		boolean isAtLeastOneTargetInBattleRange = false;
		int attackCount = 0;
		for(EffectSprite effectSprite : battleables){
			if(isInBattleRange(effectSprite)){
				attack(effectSprite);	
				attackCount++;
				isAtLeastOneTargetInBattleRange = true;
				if(effectSpreadRange>0){
					if(effectSpritesByAttecked==null)
						effectSpritesByAttecked = new ArrayList<EffectSprite>();
					effectSpritesByAttecked.add(effectSprite);
				}
				if(attackTargeCounttMaxLimit!=UNLIMIT_ATTACK_TARGET_COUNT && attackTargeCounttMaxLimit >= attackCount)
					break;
			}
		}
		
		checkIfInEffectSpreadRangeThenSpread(battleables, effectSpritesByAttecked);
		
		return isAtLeastOneTargetInBattleRange;
	}
	
	protected void checkIfInEffectSpreadRangeThenSpread(List<EffectSprite> battleables, List<EffectSprite> effectSpritesByAttecked) {
		if(effectSpreadRange>0 && effectSpritesByAttecked!=null){
			for(EffectSprite effectSpriteByAttecked : effectSpritesByAttecked){
				SpriteDetectAreaHandler spriteDetectAreaHandler = new SpriteDetectAreaHandler();
				spriteDetectAreaHandler.addSuccessorDetectArea(new DetectAreaRound(new PointF(effectSpriteByAttecked.getCenterX(), effectSpriteByAttecked.getCenterY()), effectSpreadRange));
				spriteDetectAreaHandler.apply();
				for(EffectSprite effectSprite : battleables){
					if(effectSprite!=effectSpriteByAttecked && spriteDetectAreaHandler.detectByPoint(new PointF(effectSprite.getCenterX(), effectSprite.getCenterY()))){
						for(IEffect effect : effects){
							effectSprite.getAttributeInfo().addToEffectStatusList(effect.cloneEffect());
						}
					}
				}
			}
		}
	}
	
	protected boolean isInBattleRange(EffectSprite effectSprite) {
//		return effectRangeDetectAreaHandler.detectByPoint(new PointF(effectSprite.getCenterX(), effectSprite.getCenterY()));
		return effectRangeDetectAreaHandler.detectByDetectArea(effectSprite.getBeAttackedRangeWithDetectArea());
	}

	public int getAttackTargeCounttMaxLimit() {
		return attackTargeCounttMaxLimit;
	}

	public void setAttackTargeCounttMaxLimit(int attackTargeCounttMaxLimit) {
		this.attackTargeCounttMaxLimit = attackTargeCounttMaxLimit;
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
		DetectArea detectArea;
		if(battleRange>0){
			detectArea = new DetectAreaRound(new PointF(getCenterX(), getCenterY()), battleRange);
		}else if(isCollisionRectFEnable() && getCollisionRectF()!=null){
			detectArea = new DetectAreaRect(getCollisionRectF());
		}else{
			detectArea = new DetectAreaRect(getFrame());
		}
		
		if(effectRangeDetectAreaHandler==null){
			effectRangeDetectAreaHandler = new SpriteDetectAreaHandler();
			effectRangeDetectAreaHandler.addSuccessorDetectArea(detectArea);
//			setSpriteDetectAreaHandler(effectRangeDetectAreaHandler);
		}else{
			effectRangeDetectAreaHandler.replaceDetectArea(battleRangeDetectArea, detectArea);
		}
		effectRangeDetectAreaHandler.apply();
		battleRangeDetectArea = detectArea;
	}
	
	public float getEffectSpreadRange() {
		return effectSpreadRange;
	}

	public void setEffectSpreadRange(float effectSpreadRange) {
		this.effectSpreadRange = effectSpreadRange;
	}

	public void setBattleRange(DetectArea detectArea){
		
	}
	
	public void setBattleRange(SpriteDetectAreaHandler spriteDetectAreaHandler){
//		setSpriteDetectAreaHandler(spriteDetectAreaHandler);
		effectRangeDetectAreaHandler = spriteDetectAreaHandler;
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
