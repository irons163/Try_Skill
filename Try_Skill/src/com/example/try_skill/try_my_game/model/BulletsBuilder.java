package com.example.try_skill.try_my_game.model;

import android.content.Context;

import com.example.try_skill.effect.EffectFactory;
import com.example.try_skill.effect.FrozenEffect;
import com.example.try_skill.effect.NormalEffect;
import com.example.try_skill.effect.StunEffect;

public class BulletsBuilder {
	
	public enum BulletEnum{
		FrozenBullets, NormalBullets, StunBullets, RangeNormalBullets
	}
	
	public static BaseBullet createBulletByBulletEnum(BulletEnum bulletEnum, Context context, float x, float y){
		BaseBullet baseBullet = null;
		
		switch (bulletEnum) {
		case FrozenBullets:
			baseBullet = createFrozenBullets(context, x, y);
			break;

		case NormalBullets:
			baseBullet = createNormalBullets(context, x, y);
			break;
			
		case StunBullets:
			baseBullet = createStunBullets(context, x, y);
			break;
			
		case RangeNormalBullets:
			baseBullet = createRangeNormalBullets(context, x, y);
			break;
			
		default:
			break;
		}
		
		return baseBullet;
	}
	
	public static Bullets createFrozenBullets(Context context, float x, float y){
		Bullets bullet = new Bullets(context, x, y, false, 0);
//		bullet.setPosition(bullet.getX(), bullet.getY());
		bullet.setType(0);
		bullet.addEffect(new FrozenEffect());
		bullet.setBattleRange(100);
		return bullet;
	}
	
	public static Bullet createNormalBullets(Context context, float x, float y){
		Bullet bullet = new Bullet(x, y, false, 4);
//		bullet.setPosition(bullet.getX(), bullet.getY());
		bullet.addEffects(EffectFactory.createFrozenDmgEffect());
		bullet.setBattleRange(100);
		return bullet;
	}
	
	public static Bullet createStunBullets(Context context, float x, float y){
		Bullet bullet = new Bullet(x, y, false, 0);
//		bullet.setPosition(bullet.getX(), bullet.getY());
		bullet.addEffect(new StunEffect());
		bullet.setBattleRange(100);
		return bullet;
	}
	
	public static RangeBullets createRangeNormalBullets(Context context, float x, float y){
		RangeBullets bullet = new RangeBullets(context, x, y, false, 0);
//		bullet.setPosition(bullet.getX(), bullet.getY());
		bullet.setType(0);
		bullet.addEffect(new NormalEffect(5));
		bullet.setBattleRange(100);
//		bullet.setAttackTargeCounttMaxLimit(Bullet.UNLIMIT_ATTACK_TARGET_COUNT);
		bullet.setEffectSpreadRange(200);
		return bullet;
	}
}
