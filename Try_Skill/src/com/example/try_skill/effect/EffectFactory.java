package com.example.try_skill.effect;

import java.util.ArrayList;
import java.util.List;

public class EffectFactory {

	public static List<IEffect> createFrozenDmgEffect(){
		IEffect effect = new FrozenEffect();
//		effect.setChild(new NormalEffect(10));
		IEffect effectDmg = new NormalEffect(5);
		List<IEffect> effects = new ArrayList<IEffect>();
		effects.add(effect);
		effects.add(effectDmg);
		return effects;
	}
	
}
