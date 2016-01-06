package com.example.try_skill.effect;

import java.math.BigDecimal;

import com.example.try_skill.center_notification.NSANotification;

public class FrozenEffect extends BaseEffect{
	
	public FrozenEffect() {
		// TODO Auto-generated constructor stub	
		init();
	}
	
	private void init(){
//		battleSpriteInjureType = BattleSpriteInjureType.Frozen;
		injureType = InjureType.Frozen;
		float interval = new BigDecimal(2.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		intervalTimer = new IntervalTimer(interval);
		intervalTimer.setFirstTimeDelay(false);
		effectTimes = 1;
		currentEffectTimes = 0;
	}
	
	@Override
	public void doEffect(IEffectable effectable) {
		// TODO Auto-generated method stub
		super.doEffect(effectable);
		effectable.getAttributeInfo().setBattleInviable((effectable.getAttributeInfo().getBattleInviable()*1.5f));
	}
	
	@Override
	public void doRemoveEffect(IEffectable effectable) {
		// TODO Auto-generated method stub
		super.doRemoveEffect(effectable);
		effectable.getAttributeInfo().setBattleInviable(effectable.getAttributeInfo().getBattleInviable()/1.5f);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		init();
	}
	
	@Override
	public IEffect cloneEffect() {
		// TODO Auto-generated method stub
		IEffect effect = new FrozenEffect();
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
