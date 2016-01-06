package com.example.try_skill.effect;

import java.math.BigDecimal;

import com.example.try_skill.center_notification.NSANotification;

public class FireEffect extends BaseEffect  {
	
//	@Override
//	public void doEffect(BattleableSprite battleableSpriteAttacker,
//			BattleableSprite battleableSpriteBeAttacked) {
//		// TODO Auto-generated method stub
//		
//	}

	public FireEffect(){
		float interval = new BigDecimal(1.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		intervalTimer = new IntervalTimer(interval);
		init();
	}
	
	private void init(){
		injureType = InjureType.Fire;
		effectTimes = 3;
		currentEffectTimes = 0;
		dmg = 5;
	}
	
	@Override
	public void doEffect(IEffectable effectable) {
		// TODO Auto-generated method stub
		super.doEffect(effectable);
//		effectable.getAttributeInfo().setHp((effectable.getAttributeInfo().getHp()-dmg));
		effectable.getAttributeInfo().addToEffectStatusList(new NormalEffect(dmg));
	}
	
	@Override
	public void doRemoveEffect(IEffectable effectable) {
		// TODO Auto-generated method stub
		super.doRemoveEffect(effectable);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		init();
	}
	
	@Override
	public IEffect cloneEffect() {
		// TODO Auto-generated method stub
		IEffect effect = new FireEffect();
		effect.setEffectListener(effectListener);
		
		if(getChild()!=null)
			effect.setChild(getChild().cloneEffect());
		return effect;
	}

	@Override
	public void receiveNotification(NSANotification nsaNotification) {
		// TODO Auto-generated method stub
		if(nsaNotification.getName().equals("effect")){
			this.effectListener.didEffect(this);
		}
	}

}
