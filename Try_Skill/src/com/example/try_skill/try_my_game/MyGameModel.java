package com.example.try_skill.try_my_game;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;

import com.example.try_gameengine.Camera.Camera;
import com.example.try_gameengine.framework.Data;
import com.example.try_gameengine.framework.GameModel;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_skill.effect.EffectSprite;
import com.example.try_skill.effect.IEffectable;
import com.example.try_skill.try_my_game.model.Defener;
import com.example.try_skill.try_my_game.model.DefenerBuilder;
import com.example.try_skill.try_my_game.model.Summerize;
import com.example.try_skill.try_my_game.model.Zombe;
import com.example.try_skill.try_my_game.model.ZombeBuilder;
import com.example.try_skill.try_my_game.model.Defener.AttackType;
import com.example.try_skill.util.CommonUtil;
import com.example.try_skill.util.MapTileObject;
import com.example.try_skill.util.MapTileUtil;

public class MyGameModel extends GameModel{
	public Defener player;
//	public Cat cat;
	int ballLevel = 0;
	public int hitBrickLevelDownCount;
	public static boolean waitGameSuccessProcessing = false;
	static boolean waitHamsterInjureProcessing = false;
	public static boolean GAME_PAUSE_FLAG = false;
	
	public static boolean isAllScreenQuake = false;
	public int allScrennQuakeTriggerCount;
	public final int ALL_SCREEN_QUAKE_COUNT = 40; 
	
	public static final int INIT_MAX_CAT_HP = 15;
	
	public static boolean gameFlag = true;
	
	public static final Object LOCK = new Object();
	
	static final int GAME_MAX_HP = 3;
	int gameHp = GAME_MAX_HP;
	
	int stickMovePointIndex = 0;
	
	private boolean bStickTouched = false;
	
	private MapTileUtil mapTileUtil;
	
	private Zombe zombe;
	
	private List<EffectSprite> zombes = new CopyOnWriteArrayList<EffectSprite>();
	private List<EffectSprite> fighters = new CopyOnWriteArrayList<EffectSprite>();
	
	private ZombeBuilder zombeBuilder;
	
	EffectSprite warrior;
	EffectSprite musketeer;
	EffectSprite medic;
	
	public MyGameModel(Context context, Data data) {
		super(context, data);
		// TODO Auto-generated constructor stub
		
		Camera camera = null;
		if(camera==null){
			camera = new Camera(0, 0, 1760, 980);
			setCamera(camera);
			getCamera().enableClearViewNextTime();
		}
//		getCamera().translate(1500, 1500);
//		getCamera().setCameraTranslateBeforeApply(0, 0);
//		getCamera().zoom(-1.0f);
//		getCamera().rotation(-35); //viewport, touchrange, spinner,edittext,save    
		getCamera().setViewPort(0, 0, CommonUtil.screenWidth, CommonUtil.screenHeight);
//		getCamera().getViewPort().setRotation(45);
		getCamera().getViewPortRectF();
		
		getCamera().applyCameraSpaceToViewPort();
	
		player = new Defener(100, 100, false, AttackType.Shoot);
		player.setPosition(500, 1000);
		
//		bullets = new Bullets(context, 100, 100, false, 0);
//		bullets.setMoveRage(0, 0, CommonUtil.screenHeight,
//				CommonUtil.screenWidth);
//		bullets.setType(0);
		
		mapTileUtil = new MapTileUtil();
		
		zombe = new Zombe(800, 100, false);
		zombe.setPosition(1000, 500);
		zombes.add(zombe);
		
		zombeBuilder = new ZombeBuilder();
		
//		warrior = (EffectSprite) Summerize.summerize(context, 500, 500, 0);
		musketeer = (EffectSprite) Summerize.summerize2(context, 400, 500, 0);
		
		
		medic = (EffectSprite) Summerize.summerize3(context, 200, 500, 0);
//		
//		fighters.add(warrior);
//		fighters.add(medic);
	}
	
	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX();
		float y = event.getY();
		
		if (gameFlag) {
			int action = event.getAction();
			int pointerCount = event.getPointerCount();
			int pointerId = 0;
			if (pointerCount > 1) {
				pointerId = (action & MotionEvent.ACTION_POINTER_ID_MASK) >>> MotionEvent.ACTION_POINTER_ID_SHIFT;
			}

			if ((event.getAction() & MotionEvent.ACTION_MASK) % 5 == MotionEvent.ACTION_DOWN) {
				x = event.getX(pointerId);
				y = event.getY(pointerId);
				
				float a[] = new float[]{x, y};
				Matrix matrix = new Matrix(getCamera().getMatrix());
				matrix.invert(matrix);
				matrix.mapPoints(a);
				
				x = a[0];
				y = a[1];
				
					stickMovePointIndex = pointerId;
					bStickTouched = true;
					
					MapTileObject mapTileObject = mapTileUtil.checkTouchXY(x, y);
					if(mapTileObject != null){
//						mapTileObject.setSprite(new Defener(mapTileObject.getX(), mapTileObject.getY(), false));
//						mapTileObject.setSprite(DefenerBuilder.createHamster1(context, mapTileObject.getX(), mapTileObject.getY()));
						EffectSprite defener = DefenerBuilder.createBySelect(context, mapTileObject.getX(), mapTileObject.getY());
						if(defener!=null)
								mapTileObject.setSprite(defener);
					}
					
			} else if ((event.getAction() & MotionEvent.ACTION_MASK) % 5 == MotionEvent.ACTION_MOVE) {
				System.out.println("bStickTouched= " + bStickTouched);
				System.out.println("pointerId= " + pointerId);
				boolean flag = false;
						
				for (int i = 0; i < pointerCount; ++i) {
					pointerId = event.getPointerId(i);
					Log.d("pointer id - move", Integer.toString(pointerId));

					if (bStickTouched && stickMovePointIndex == pointerId ) {
						Log.d("pointer id ", Integer.toString(pointerId)+"stickMovePointIndex"+stickMovePointIndex);
						
						if(pointerId>=pointerCount){
							x = event.getX(pointerId - ((pointerId+1)-pointerCount));
							y = event.getY(pointerId - ((pointerId+1)-pointerCount));
//							stickMovePointIndex = pointerCount;
						}else{
							x = event.getX(pointerId);
							y = event.getY(pointerId);
						}
						
						
					}
				}
			} else if ((event.getAction() & MotionEvent.ACTION_MASK) % 5 == MotionEvent.ACTION_UP) {
				if (bStickTouched && stickMovePointIndex == pointerId) {
					bStickTouched = false;
				}

			}
		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
			bStickTouched = false;
		}
		

		
		super.onTouchEvent(event);
	}
	
	private void checkMapDefenerInBattle(){
		mapTileUtil.checkMapDefenersBattleWithMonsters(zombes);
	}

	@Override
	protected void process() {
		// TODO Auto-generated method stub
		super.process();
		
//		bullets.frameTrig();
		
		mapTileUtil.frameTrig();
		
		Zombe zombeNew = zombeBuilder.createZombe();
		
		if(zombeNew!=null){
			zombes.add(zombeNew);
		}
		
//		zombe.frameTrig();
		
		for(IEffectable zombe : zombes){
			((Sprite)zombe).frameTrig();
		}
		
//		warrior.frameTrig();
		musketeer.frameTrig();
		medic.frameTrig();
//		
//		mapTileUtil.frameTrig();
//		
//		warrior.checkIfInBattleRangeThenAttack(zombes);
		musketeer.checkIfInBattleRangeThenAttack(zombes);
		medic.checkIfInBattleRangeThenAttack(zombes);
		
		checkMapDefenerInBattle();
		
		for(IEffectable zombe : zombes){
			zombe.doAttachedEffects();
		}
		
		for(EffectSprite zombe : zombes){
			if(((EffectSprite)zombe).getX() < 0 || ((EffectSprite)zombe).isNeedRemove()){
				zombe.getMovementAction().controller.cancelAllMove();
				zombes.remove(zombe);
				
				((EffectSprite)zombe).setIsBattleable(false);
			}
		}
	}
	
	@Override
	protected void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.doDraw(canvas);
		
		player.drawSelf(canvas, null);
		
//		bullets.drawSelf(canvas, null);
		
//		zombe.drawSelf(canvas, null);		
		
		mapTileUtil.drawSelf(canvas, null);
		
		LayerManager.getInstance().drawLayers(canvas, null);
		
		for(IEffectable zombe : zombes){
			((Sprite)zombe).drawSelf(canvas, null);
		}
		
//		warrior.drawSelf(canvas, null);
		musketeer.drawSelf(canvas, null);
		medic.drawSelf(canvas, null);
	}
	
	public int getBallLevel() {
		return ballLevel;
	}
	
	public void setBallLevel(int ballLevel) {
		this.ballLevel = ballLevel;
		// ball.setBallLevel(ballLevel);
	}
	
	public void setWeapenChange(){
		player.setBulletLevel();
	}
	
	public void setWeapenChange2(){
		player.setBulletLevel2();
	}
	
	public void setWeapenChange3(){
		player.setBulletLevel3();
	}
	
	public void setWeapenChange5(){
		player.setBulletLevel5();
	}
	
	public boolean isGameRun() {
		return isGameRun;
	}
	
	public boolean isBallRun() {
		return !isGameStop;
	}
	
	public void addHp(){
		if(gameHp < GAME_MAX_HP){
		gameHp++;
		}
	}
}