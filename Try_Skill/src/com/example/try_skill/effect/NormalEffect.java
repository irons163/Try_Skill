package com.example.try_skill.effect;

import java.math.BigDecimal;

import com.example.try_skill.center_notification.NSANotification;

public class NormalEffect extends BaseEffect{
	private float dmg;
	
	public NormalEffect(float dmg) {
		// TODO Auto-generated constructor stub
		injureType = InjureType.Normal;
		this.dmg = dmg;
		init();
	}
	
	private void init(){
//		battleSpriteInjureType = BattleSpriteInjureType.Frozen;
		float interval = new BigDecimal(1.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		intervalTimer = new IntervalTimer(interval);
		effectTimes = 1;
		currentEffectTimes = 0;
	}
	
	@Override
	public void doEffect(IEffectable effectable) {
		// TODO Auto-generated method stub
		super.doEffect(effectable);
		effectable.getAttributeInfo().setHp((effectable.getAttributeInfo().getHp()-dmg));
	}
	
	@Override
	public IEffect cloneEffect() {
		// TODO Auto-generated method stub
		IEffect effect = new NormalEffect(dmg);
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
