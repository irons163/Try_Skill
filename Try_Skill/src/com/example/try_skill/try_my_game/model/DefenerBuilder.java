package com.example.try_skill.try_my_game.model;

import android.content.Context;
import android.graphics.RectF;
import android.util.Log;

import com.example.try_skill.effect.EffectSprite;
import com.example.try_skill.effect.FireEffect;
import com.example.try_skill.effect.IEffect;
import com.example.try_skill.effect.IEffectable;
import com.example.try_skill.effect.NormalEffect;
import com.example.try_skill.try_my_game.model.BulletsBuilder.BulletEnum;
import com.example.try_skill.try_my_game.model.Defener.AttackType;
import com.example.try_skill.util.CommonUtil;

public class DefenerBuilder {
	public static int select = -1;
	
	public static EffectSprite createBySelect(Context context, float x, float y){
		EffectSprite defener = null;
		switch (select) {
		case 0:
			defener = createHamster1(context, x, y);
			break;
		case 1:
			defener = createHamster2(context, x, y);
			break;
		case 2:
			defener = createHamster3(context, x, y);
			break;
		case 3:
			defener = createHamster4(context, x, y);
			break;
		case 4:
			defener = createHamster5(context, x, y);
			break;
		case 5:
			defener = createHamster6(context, x, y);
			break;
		}
		
		return defener;
	}
	
	public static Defener createHamster1(Context context, float x, float y){
		final Defener defener = new Defener(x, y, true, AttackType.Shoot);
		
		ShooterWeapen shooterWeapen = new ShooterWeapen(context, x, y, false);
		shooterWeapen.setBulletEnum(BulletEnum.FrozenBullets);
		shooterWeapen.setBattleRange(500);
		shooterWeapen.setShooterEventListener(new ShooterWeapen.ShooterEventListener() {
			
			@Override
			public void willAttack(IEffectable battleableSprite) {
				// TODO Auto-generated method stub
//				defener.attack(battleableSprite);
			}
		});
		defener.setWeapen(shooterWeapen);
		return defener;
	}
	
	public static Defener createHamster2(Context context, float x, float y){
		final Defener defener = new Defener(x, y, true, AttackType.Melee);
		
		final WeapenSprite meleeWeapen = new WeapenSprite(x, y, false, 0);
		meleeWeapen.setBattleRange(defener.getEffectRangeDetectAreaHandler());
		meleeWeapen.setAttackTargeCounttMaxLimit(WeapenSprite.UNLIMIT_ATTACK_TARGET_COUNT);
		meleeWeapen.addEffect(new FireEffect());

		defener.setWeapen(meleeWeapen);
		defener.setBattleRange(300);
		return defener;
	}
	
	public static Defener createHamster3(Context context, float x, float y){
		final Defener defener = new Defener(x, y, true, AttackType.Shoot);
		
		ShooterWeapen shooterWeapen = new ShooterWeapen(context, x, y, false);
		shooterWeapen.setBulletEnum(BulletEnum.NormalBullets);
		shooterWeapen.setBattleRange(CommonUtil.screenWidth);
		shooterWeapen.setShooterEventListener(new ShooterWeapen.ShooterEventListener() {
			
			@Override
			public void willAttack(IEffectable battleableSprite) {
				// TODO Auto-generated method stub
//				defener.attack(battleableSprite);
			}
		});
		defener.setWeapen(shooterWeapen);
		return defener;
	}
	
	public static Defener createHamster4(Context context, float x, float y){
		final Defener defener = new Defener(x, y, true, AttackType.Shoot);
		
		ShooterWeapen shooterWeapen = new ShooterWeapen(context, x, y, false);
		shooterWeapen.setBulletEnum(BulletEnum.StunBullets);
		shooterWeapen.setBattleRange(CommonUtil.screenWidth);
		shooterWeapen.setShooterEventListener(new ShooterWeapen.ShooterEventListener() {
			
			@Override
			public void willAttack(IEffectable battleableSprite) {
				// TODO Auto-generated method stub
//				defener.attack(battleableSprite);
			}
		});
		defener.setWeapen(shooterWeapen);
		return defener;
	}
	
	public static Defener createHamster5(Context context, float x, float y){
		final Defener defener = new Defener(x, y, true, AttackType.Shoot);
		
		ShooterWeapen shooterWeapen = new ShooterWeapen(context, x, y, false);
		shooterWeapen.setBulletEnum(BulletEnum.RangeNormalBullets);
		shooterWeapen.setBattleRange(CommonUtil.screenWidth);
		shooterWeapen.setShooterEventListener(new ShooterWeapen.ShooterEventListener() {
			
			@Override
			public void willAttack(IEffectable battleableSprite) {
				// TODO Auto-generated method stub
//				defener.attack(battleableSprite);
			}
		});
		defener.setWeapen(shooterWeapen);
		return defener;
	}
	
	public static Defener createHamster6(Context context, float x, float y){
		final Defener defener = new Defener(x, y, true, AttackType.Melee);
		
		final WeapenSprite meleeWeapen = new WeapenSprite(x, y, false);
//		shooterWeapen.setBulletEnum(BulletEnum.RangeNormalBullets);
//		meleeWeapen.setBattleRange(WeapenSprite.NO_ATK_RANGE);
		meleeWeapen.addEffect(new NormalEffect(20));
		meleeWeapen.setBattleRange(defener.getEffectRangeDetectAreaHandler());
		
		defener.setWeapen(meleeWeapen);
		return defener;
	}
}
