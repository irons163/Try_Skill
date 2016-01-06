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

public class WeapenSprite extends EffectSprite implements IWeapen{
//	protected IEffect effect;
//	protected float atkR = 50;
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
		attributeInfo = new AttributeInfo();
		attributeInfo.setAtk(10);
		attributeInfo.setDef(5);
		attributeInfo.setHp(20);
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

	@Override
	public void setWeapenEffect(IEffect effect) {
		// TODO Auto-generated method stub
//		this.effect = effect;
//		setEffectListener(this.effect);
		
		addWeapenEffect(effect);
		
	}
	
	protected void setEffectListener(IEffect effect) {
		
		effect.setEffectListener(new IEffect.EffectListener() {
			
//			@Override
//			public void didEffect(BattleableSprite battleableSpriteByEffecten) {
//				// TODO Auto-generated method stub
//				int dmg = attributeInfo.getAtk() - battleableSpriteByEffecten.getAttributeInfo().getDef();
//				if(dmg > 0){
//					battleableSpriteByEffecten.getAttributeInfo().setHp(battleableSpriteByEffecten.getAttributeInfo().getHp() - dmg);
//				}
//			}

			@Override
			public void didEffect(IEffect effect) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	@Override
	public IEffect getWeapenEffect() {
		// TODO Auto-generated method stub
//		return effect;
		return null;
	}
	
	public List<IEffect> getWeapenEffects() {
		// TODO Auto-generated method stub
		return effects;
	}

	protected boolean isInBattleRange(EffectSprite effectSprite) {
		return getSpriteDetectAreaHandler().detectByPoint(new PointF(effectSprite.getCenterX(), effectSprite.getCenterY()));
	}

	protected void attack(IEffectable effectSprite) {
		// TODO Auto-generated method stub
		for(IEffect effect : effects){
			effectSprite.getAttributeInfo().addToEffectStatusList(effect.cloneEffect());
		}
	}
	
	@Override
	public void checkIfInBattleRangeThenAttack(
			List<EffectSprite> battleables) {
		// TODO Auto-generated method stub
		
		boolean isAtLeastOneTargetInBattleRange = false;
		for(EffectSprite battleableSprite : battleables){
			boolean isInBattleRange = isInBattleRange(battleableSprite);
			if(isInBattleRange){ 
				attack(battleableSprite);
				isAtLeastOneTargetInBattleRange = true;
			}
		}
//		return isAtLeastOneTargetInBattleRange;
	}

	@Override
	public void setBattleRange(float battleRange) {
		// TODO Auto-generated method stub
		this.battleRange = battleRange;
		getSpriteDetectAreaHandler().reset();
		getSpriteDetectAreaHandler().addSuccessorDetectArea(new DetectAreaRound(new PointF(getCenterX(), getCenterY()), battleRange));
		getSpriteDetectAreaHandler().apply();
	}

//	@Override
//	public AttributeInfo getNewAttributeInfo(AttributeInfo attributeInfo) {
//		// TODO Auto-generated method stub
//		AttributeInfo attributeInfoNew = new AttributeInfo(attributeInfo);
//		attributeInfoNew.setAtk(attributeInfoNew.getAtk()+5);
//		return attributeInfoNew;
//	}	

//	public boolean isHasATKRange(){
//		if(atkR==NO_ATK_RANGE)
//			return false;
//		return true;
//	}
	
	public interface NoATKRangeListener{
		boolean isInNoATKBattleRange(IEffectable battleableSprite);
		void attack(IEffectable battleableSprite);
		void willDoEffect(IEffect effect, IEffectable battleableSprite);
//		void attackFinish(BattleableSprite battleableSprite);
	}
	
	NoATKRangeListener noATKRangeListener = new NoATKRangeListener() {
		
		@Override
		public boolean isInNoATKBattleRange(IEffectable battleableSprite) {
			// TODO Auto-generated method stub
			new RuntimeException();
			return false;
		}

		@Override
		public void attack(IEffectable battleableSprite) {
			// TODO Auto-generated method stub
			new RuntimeException();
		}
		
		@Override
		public void willDoEffect(IEffect effect, IEffectable battleableSprite) {
			// TODO Auto-generated method stub
			new RuntimeException();
		}
		
//		@Override
//		public void attackFinish(BattleableSprite battleableSprite) {
//			// TODO Auto-generated method stub
//			new RuntimeException();
//		}
	};
	
	public void setNoATKRangeListener(NoATKRangeListener noATKRangeListener){
		this.noATKRangeListener = noATKRangeListener;
	}
	
//	HashMap<String, IEffect> effects = new HashMap<String, IEffect>();
	
	public void addWeapenEffect(String key, IEffect effect){
		effects.add(effect);
//		effects.put(key, effect);
	}
	
	public void addWeapenEffect(IEffect effect){
		effects.add(effect);
//		effects.put(key, effect);
	}
	
//	public void setWeapenEffectByKey(String key){
//		setWeapenEffect(effects.get(key));
//	}

	@Override
	public void beAttacked(Sprite weapenSprite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beAttacked(IEffect effect) {
		// TODO Auto-generated method stub
		
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
