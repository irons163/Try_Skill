package com.example.try_skill.try_my_game.model;

import android.content.Context;
import android.graphics.RectF;
import android.util.Log;

import com.example.try_skill.effect.IEffect;
//import com.example.try_skill.try_game.try_effect.HealEffect;
//import com.example.try_skill.try_game.try_effect.NormalEffect;



public class Summerize {

//	public static BattleableSprite summerize(Context context, float x, float y, int type){
//		
//		final Warrior defener = new Warrior(x, y, false);
//		
//		MeleeWeapon shooterWeapen = new MeleeWeapon(context, x, y, false);
//		shooterWeapen.setBattleRange(WeapenSprite.NO_ATK_RANGE);
//		shooterWeapen.setWeapenEffect(new NormalEffect());
//		shooterWeapen.setNoATKRangeListener(new MeleeWeapon.NoATKRangeListener() {
//
//			@Override
//			public boolean isInNoATKBattleRange(
//					BattleableSprite battleableSprite) {
//				// TODO Auto-generated method stub
////				boolean isCollision = defener.getCollisionRectF().intersect(battleableSprite.getCollisionRectF());
//				
//				boolean isCollision = RectF.intersects(defener.getCollisionRectF(), battleableSprite.getCollisionRectF());
//				
////				if(isCollision)
//					Log.e("isCollision", "isCollision:"+isCollision);
//				return isCollision;
//			}
//
//			@Override
//			public void attack(BattleableSprite battleableSprite) {
//				// TODO Auto-generated method stub
////				shooterWeapen.attack(battleableSprite);
//				defener.cancelMovementAction();
//				defener.setAtkAction();
//			}
//
//			@Override
//			public void willDoEffect(IEffect effect,
//					BattleableSprite battleableSprite) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		defener.setWeapen(shooterWeapen);
//		return defener;
//
//	}
//	
//	public static BattleableSprite summerize2(Context context, float x, float y, int type){
//		
//		final Musketeer defener = new Musketeer(x, y, false);
//		
//		MeleeWeapon shooterWeapen = new MeleeWeapon(context, x, y, false, 1.5f);
//		shooterWeapen.setBattleRange(250);
//		shooterWeapen.setWeapenEffect(new NormalEffect());
//		shooterWeapen.setNoATKRangeListener(new MeleeWeapon.NoATKRangeListener() {
//
//			@Override
//			public boolean isInNoATKBattleRange(
//					BattleableSprite battleableSprite) {
//				// TODO Auto-generated method stub
////				boolean isCollision = defener.getCollisionRectF().intersect(battleableSprite.getCollisionRectF());
//				
//				boolean isCollision = RectF.intersects(defener.getCollisionRectF(), battleableSprite.getCollisionRectF());
//				
////				if(isCollision)
//					Log.e("isCollision", "isCollision:"+isCollision);
//				return isCollision;
//			}
//
//			@Override
//			public void attack(BattleableSprite battleableSprite) {
//				// TODO Auto-generated method stub
////				shooterWeapen.attack(battleableSprite);
//				defener.cancelMovementAction();
//				defener.setAtkAction();
//			}
//
//			@Override
//			public void willDoEffect(IEffect effect,
//					BattleableSprite battleableSprite) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		defener.setWeapen(shooterWeapen);
//		return defener;
//
//	}
//	
//	public static BattleableSprite summerize3(Context context, float x, float y, int type){
//		
//		final Medic defener = new Medic(x, y, false);
//		
//		final MeleeWeapon shooterWeapen = new MeleeWeapon(context, x, y, false, 2.0f);
//		shooterWeapen.setBattleRange(250);
//		
//		shooterWeapen.addWeapenEffect("NORNAL", new NormalEffect());
//		
//		shooterWeapen.addWeapenEffect("HEAL", new HealEffect());
//		
//		shooterWeapen.setNoATKRangeListener(new MeleeWeapon.NoATKRangeListener() {
//
//			@Override
//			public boolean isInNoATKBattleRange(
//					BattleableSprite battleableSprite) {
//				// TODO Auto-generated method stub
////				boolean isCollision = defener.getCollisionRectF().intersect(battleableSprite.getCollisionRectF());
//				
//				boolean isCollision = RectF.intersects(defener.getCollisionRectF(), battleableSprite.getCollisionRectF());
//				
////				if(isCollision)
//					Log.e("isCollision", "isCollision:"+isCollision);
//				return isCollision;
//			}
//
//			@Override
//			public void attack(BattleableSprite battleableSprite) {
//				// TODO Auto-generated method stub
////				shooterWeapen.attack(battleableSprite);
//				defener.cancelMovementAction();
//				defener.setAtkAction();
//			}
//
//			@Override
//			public void willDoEffect(IEffect effect,
//					BattleableSprite battleableSprite) {
//				// TODO Auto-generated method stub
//				if(battleableSprite.getBattleableSpriteType()==BattleableSpriteType.Enemy){
//					shooterWeapen.setWeapenEffectByKey("NORNAL");
//				}else if(battleableSprite.getBattleableSpriteType()==BattleableSpriteType.Self){
//					shooterWeapen.setWeapenEffectByKey("HEAL");
//				}
//			}
//		});
//		defener.setWeapen(shooterWeapen);
//		return defener;
//
//	}
}
