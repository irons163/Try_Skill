package com.example.try_skill.try_my_game.model;

import android.content.Context;
import android.graphics.RectF;
import android.util.Log;

import com.example.try_skill.effect.EffectSprite;
import com.example.try_skill.effect.HealEffect;
import com.example.try_skill.effect.IEffect;
import com.example.try_skill.effect.NormalEffect;
//import com.example.try_skill.try_game.try_effect.HealEffect;
//import com.example.try_skill.try_game.try_effect.NormalEffect;
import com.example.try_skill.try_my_game.model.Defener.AttackType;



public class Summerize {

	public static EffectSprite summerize(Context context, float x, float y, int type){
		
		final Warrior defener = new Warrior(x, y, false, AttackType.Melee);
		
		WeapenSprite meleeWeapen = new WeapenSprite(x, y, false);
//		meleeWeapen.setBattleRange(WeapenSprite.NO_ATK_RANGE);
		meleeWeapen.setBattleRange(defener.getEffectRangeDetectAreaHandler());
		meleeWeapen.addEffect(new NormalEffect(5));
		meleeWeapen.setInterval(0.5f);
		defener.setWeapen(meleeWeapen);
		return defener;

	}
	
	public static EffectSprite summerize2(Context context, float x, float y, int type){
		
		final Musketeer defener = new Musketeer(x, y, false, AttackType.Shoot);
		
		WeapenSprite shooterWeapenWithoutBullet = new WeapenSprite(x, y, false, 1.0f);
		shooterWeapenWithoutBullet.setBattleRange(250);
		shooterWeapenWithoutBullet.addEffect(new NormalEffect(5));
		defener.setWeapen(shooterWeapenWithoutBullet);
		return defener;
	}
	
	public static EffectSprite summerize3(Context context, float x, float y, int type){
		
		final Medic defener = new Medic(x, y, false, AttackType.Shoot);
		
		final WeapenSprite shooterWeapen = new WeapenSprite(x, y, false, 2.0f);
		shooterWeapen.setBattleRange(250);
		
//		shooterWeapen.addWeapenEffect("NORNAL", new NormalEffect(5));	     
		shooterWeapen.addWeapenEffect("HEAL", new HealEffect(5));
		
		defener.setWeapen(shooterWeapen);
		return defener;

	}
}
