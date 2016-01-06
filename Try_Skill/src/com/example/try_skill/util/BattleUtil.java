package com.example.try_skill.util;

import java.util.List;

import android.util.MonthDisplayHelper;


public class BattleUtil {
	
//	public static void checkBattle(List<IEffectable> battleableSpriteDefeners, List<IEffectable> battleableSpriteMonsters){
////		if(battleableSprite.isInBattleRange(battleableSprite2)){
////			battleableSprite.attack(battleableSprite2);
////		}
////		if(battleableSprite2.isInBattleRange(battleableSprite)){
////			battleableSprite2.attack(battleableSprite);
////		}
//		for(IEffectable defener : battleableSpriteDefeners){
//			defener.checkIfInBattleRangeThenAttack(battleableSpriteMonsters);
//		}
//		for(IEffectable monster : battleableSpriteMonsters){
//			monster.checkIfInBattleRangeThenAttack(battleableSpriteDefeners);
//		}
//	}
	
	public static int[] changeToNew(int[] olds, float time){
		int[] news = new int[olds.length];
		for(int i = 0; i<olds.length; i++){
			int t = olds[i];
			news[i] = Math.round(t*time);
		}
		return news;
	}
	
	public static int changeToNew(int old, float time){
		int thenew = Math.round(old*time);
		return thenew;
	}
}
