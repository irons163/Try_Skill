package com.example.try_skill.util;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.try_skill.effect.EffectSprite;
import com.example.try_skill.effect.IEffectable;

public class MapTileUtil {
	private MapTileObject[][] mapTileObjects = new MapTileObject[5][5];
	
	private float offsetX = 150.0f;
	private float offsetY = 100.0f;
	
	public MapTileUtil(){
		init();
	}
	
	private void init(){
		for(int i = 0; i < mapTileObjects.length; i++){
			for(int j = 0; j < mapTileObjects[i].length; j++){
				mapTileObjects[i][j] = new MapTileObject(i*230+offsetX, j*150+offsetY, false);
				mapTileObjects[i][j].setBitmapAndAutoChangeWH(BitmapUtil.hp);
			}
		}
	}
	
	public void checkMapDefenersBattleWithMonsters(List<EffectSprite> enemys){
		for(int i = 0; i < mapTileObjects.length; i++){
			for(int j = 0; j < mapTileObjects[i].length; j++){
				MapTileObject mapTileObject = mapTileObjects[i][j];
				EffectSprite defener = mapTileObject.getSprite();

				if(defener!=null){
					defener.checkIfInBattleRangeThenAttack(enemys);
				}
			}
		}
	}
	
	public MapTileObject checkTouchXY(float x, float y){
		for(int i = 0; i < mapTileObjects.length; i++){
			for(int j = 0; j < mapTileObjects[i].length; j++){
				MapTileObject mapTileObject = mapTileObjects[i][j];
				if(mapTileObject.isTouchXY(x ,y)){
					return mapTileObject;
				}
			}
		}
		
		return null;
	}
	
	public void frameTrig(){
		for(int i = 0; i < mapTileObjects.length; i++){
			for(int j = 0; j < mapTileObjects[i].length; j++){
				mapTileObjects[i][j].frameTrig();
			}
		}
	}
	
	public void drawSelf(Canvas canvas, Paint paint){
		for(int i = 0; i < mapTileObjects.length; i++){
			for(int j = 0; j < mapTileObjects[i].length; j++){
				mapTileObjects[i][j].drawSelf(canvas, paint);
			}
		}
	}
}
