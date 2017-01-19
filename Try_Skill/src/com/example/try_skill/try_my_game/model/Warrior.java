package com.example.try_skill.try_my_game.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Bitmap.Config;
import android.util.Log;

import com.example.try_gameengine.action.MathUtil;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionInfo;
import com.example.try_gameengine.action.MovementActionItemBaseReugularFPS;
import com.example.try_gameengine.action.MovementActionSetWithThreadPool;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.framework.IActionListener;
import com.example.try_gameengine.utils.DetectArea;
import com.example.try_gameengine.utils.DetectAreaRect;
import com.example.try_gameengine.utils.DetectAreaRound;
import com.example.try_gameengine.utils.DetectAreaType;
import com.example.try_gameengine.utils.IDetectAreaRequest;
import com.example.try_gameengine.utils.ISpriteDetectAreaListener;
import com.example.try_gameengine.utils.SpriteDetectAreaHandler;
import com.example.try_skill.effect.EffectSprite;
import com.example.try_skill.effect.IEffectable;
import com.example.try_skill.try_my_game.model.Zombe.SheepMove;
import com.example.try_skill.util.Attribute;
import com.example.try_skill.util.AttributeHelper;
import com.example.try_skill.util.BattleUtil;
import com.example.try_skill.util.BitmapUtil;

public class Warrior extends Defener{
//	Attribute attribute;
//	public AttributeHelper attributeHelper;
//	long lastShootTime;
	boolean isPrepareToShoot = false;
	boolean isShooting = false;
	boolean isMoveing = false;
	int timeCounter;
	int shootBulletLevel = 0;
	List<Bullet> bullets = new ArrayList<Bullet>();
	boolean isInjure = false;
	int HAMSTER_INJURE_TIME = 40;
	int hamsterInjureCounter;
	boolean isInvincible = false;
	protected DetectArea watchRangeDetectArea;
	protected float watchRange = 300;
	protected boolean isAllowToMove;
	protected boolean isInWatchRangeChecking;
	protected float speedX = 4.0f;
	protected float speedY;
	
	enum SheepMove {

		Shoot(
				"Shoot",
				new Bitmap[] {
						
						BitmapUtil.hamster,
						BitmapUtil.hamsterShoot,
						BitmapUtil.hamsterShoot2
				}),
				
		Move(
		"RABIT_MOVE",
		new Bitmap[] {
				
				BitmapUtil.hamster,
				BitmapUtil.hamsterShoot,
				BitmapUtil.hamsterShoot2
		}),
		Injure(
				"Injure",
				new Bitmap[] {
						
						BitmapUtil.hamster_injure
				}),		
		;

		String name;
		Bitmap[] bitmaps;

		private SheepMove(String name, Bitmap[] bitmaps) {
			this.name = name;
			this.bitmaps = bitmaps;
		}

		public String getName() {
			return name;
		}

		public Bitmap[] getBitmaps() {
			return bitmaps;
		}
	}
	
	public Warrior(float x, float y, boolean autoAdd, AttackType attackType) {
		super(x, y, autoAdd, attackType);
		// TODO Auto-generated constructor stub
		setWatchRange(watchRange);
		initCollisiontRectF();
	}
	
	@Override
	protected void initAttribute() {
		// TODO Auto-generated method stub
		super.initAttribute();
	}
	
	@Override
	protected void initDefaultAction() {
		// TODO Auto-generated method stub
//		super.initDefaultAction();
		addActionFPSFrame(SheepMove.Move.getName(), new int[]{11,12,13,11}, new int[]{4,4,4,4}, true, new IActionListener() {
			@Override
			public void beforeChangeFrame(int nextFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterChangeFrame(int periousFrameId) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void actionFinish() {
				// TODO Auto-generated method stub
//				isShooting = false;
			}
		});
		
		float interval = attribute.getInterval();
		int num = (int) (interval*com.example.try_gameengine.framework.Config.fps);
		addActionFPSFrame(SheepMove.Shoot.getName(), new int[]{0,10,11,1}, new int[]{0,num/3,num/3,num/3}, false, new IActionListener() {
			@Override
			public void beforeChangeFrame(int nextFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterChangeFrame(int periousFrameId) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void actionFinish() {
				// TODO Auto-generated method stub
				isShooting = false;
			}
		});
	}
	
	private void initCollisiontRectF(){
		setCollisionRectFEnable(true);
		float collisionWidth = w;
		float collisionHitght = h;
		float collisionOffsetX = w/2-collisionWidth/2;
		float collisionOffsetY = h/2-collisionHitght/2;
		setCollisionOffsetXY(collisionOffsetX, collisionOffsetY);
		setCollisionRectFWH(collisionWidth, collisionHitght);
		setCollisionRectF(getX()+collisionOffsetX, getY()+collisionOffsetY, getX()+collisionOffsetX+collisionWidth, getY()+collisionOffsetY+collisionHitght);
	}
	
	MovementAction movementActionShoot;
	
	public void setMovementAction(final EffectSprite targetBattleableSprite){
		
		if(movementActionShoot!=null)
			return;
		
		if(isShooting)
			return;
			
		movementActionShoot = new MovementActionSetWithThreadPool();
		movementActionShoot.setMovementActionController(new MovementAtionController());
		MovementActionInfo info = new MovementActionInfo(3000, BattleUtil.changeToNew(1, getAttributeInfo().getBattleInviable()), speedX, speedY, "", null, false);
		MovementActionItemBaseReugularFPS reFlectaction = new MovementActionItemBaseReugularFPS(info);
		movementActionShoot.addMovementAction(reFlectaction);
		
		movementActionShoot.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
			
			@Override
			public void onTick(float dx, float dy) {
				// TODO Auto-generated method stub
				
				if(targetBattleableSprite == null || !targetBattleableSprite.isBattleable()){
					Log.e("Warrior", "isBattleable:" + targetBattleableSprite.isBattleable());
					movementActionShoot.controller.cancelAllMove();
					movementActionShoot = null;
					return;
				}
				
				if(!isAllowToMove){
					return;
				}
				isAllowToMove = false;
				
				float targetX = targetBattleableSprite.getCenterX(); 
				float targetY = targetBattleableSprite.getCenterY(); 
				if(targetBattleableSprite instanceof MoveableEffectSprite){
					MoveableEffectSprite moveableEffectSprite = (MoveableEffectSprite)targetBattleableSprite;
					DetectArea detectArea = moveableEffectSprite.getBeAttackedRangeWithDetectArea();
					float angle = moveableEffectSprite.getMoveAngle();
					if(detectArea.getDetectAreaType()==DetectAreaType.POINT){
//						targetX = targetBattleableSprite.getCenterX(); 
//						targetY = targetBattleableSprite.getCenterY(); 
						float minLength = targetBattleableSprite.getFrame().width()/2 < targetBattleableSprite.getFrame().height()/2 ? targetBattleableSprite.getFrame().width()/2:targetBattleableSprite.getFrame().height()/2;
						targetX = (float) Math.cos(Math.toRadians(angle)) * minLength + targetBattleableSprite.getCenterX();
						targetY = (float) Math.sin(Math.toRadians(angle)) * minLength * (-1) + targetBattleableSprite.getCenterY();
					}else if(detectArea.getDetectAreaType()==DetectAreaType.RECT){
						DetectAreaRect detectAreaRect = ((DetectAreaRect)detectArea);
						float minLength = detectAreaRect.getRectF().width()/2 < detectAreaRect.getRectF().height()/2 ? detectAreaRect.getRectF().width()/2:detectAreaRect.getRectF().height()/2;
						targetX = (float) Math.cos(Math.toRadians(angle)) * minLength + targetBattleableSprite.getCenterX();
						targetY = (float) Math.sin(Math.toRadians(angle)) * minLength * (-1) + targetBattleableSprite.getCenterY();
					}else if(detectArea.getDetectAreaType()==DetectAreaType.ROUND){
						DetectAreaRound detectAreaRound = ((DetectAreaRound)detectArea);
						float radius = detectAreaRound.getRadius();
						targetX = (float) Math.cos(Math.toRadians(angle)) * radius + targetBattleableSprite.getCenterX();
						targetY = (float) Math.sin(Math.toRadians(angle)) * radius * (-1) + targetBattleableSprite.getCenterY();
					}
				}
				
				
				final MathUtil mathUtil = new MathUtil();
				final float angle = mathUtil.getNewAngleTowardsPointF(targetX, targetY, getCenterX(), getCenterY());
					
				mathUtil.setXY(dx, dy);
				
				mathUtil.setINITSPEEDX(mathUtil.genTotalSpeed());
				
				dx = mathUtil.getSpeedX(angle);
				
				dy = mathUtil.getSpeedY(angle);
				
				move(dx, dy);
				
				Log.e("Warrior", "move:dx:" + dx + " dy:" + dy);
				
//				scriptPaser.nextScriptLine();

				
//				scriptPaser.triggerAndDoCommandInSprite();
				
			}
		});
		
		movementActionShoot.initMovementAction();
		
		movementActionShoot.start();
		
		setMovementAction(movementActionShoot);
	}
	
	@Override
	public void setWeapen(WeapenSprite weapen) {
		// TODO Auto-generated method stub
		super.setWeapen(weapen);
		
		if(weapen==null)
			return;
		float interval = weapen.attribute.getInterval();
		int num = (int) (interval*com.example.try_gameengine.framework.Config.fps);
		addActionFPSFrame(SheepMove.Shoot.getName(), new int[]{0,10,11,1}, new int[]{1,num/3,num/3,num/3}, false, new IActionListener() {
			@Override
			public void beforeChangeFrame(int nextFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterChangeFrame(int periousFrameId) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void actionFinish() {
				// TODO Auto-generated method stub
				isShooting = false;
			}
		});
	}
	
//	@Override
//	protected void attack(IEffectable effectSprite) {
//		// TODO Auto-generated method stub
//		super.attack(effectSprite);	
//	}
	
	@Override
	public boolean checkIfInBattleRangeThenAttack(
			List<EffectSprite> battleables) {
		// TODO Auto-generated method stub
		boolean isAttack = false;
		if(!isShooting)
			isAttack = super.checkIfInBattleRangeThenAttack(battleables);
		
		if(!isAttack)
			isAllowToMove = checkIfInWatchRangeThenMove(battleables);
		else{
			cancelMovementAction();
			setAtkAction();
		}
			
		return isAttack;
	}
	
	protected boolean checkIfInWatchRangeThenMove(List<EffectSprite> battleables){
		isInWatchRangeChecking = true;
		boolean isInWatchRange = false;
		for(EffectSprite effectSprite : battleables){
			isInWatchRange = isInWatchRange(effectSprite);
			if(isInWatchRange){ 
				setMovementAction(effectSprite);
				break;
			}
		}
		isInWatchRangeChecking = false;
		return isInWatchRange;
	}
	
	protected boolean isInWatchRange(EffectSprite effectSprite){
		return effectRangeDetectAreaHandler.detectByPoint(new PointF(effectSprite.getCenterX(), effectSprite.getCenterY()));
	}
	
	@Override
	public void setBattleRange(float battleRange) {
		super.setBattleRange(battleRange);

		setBattleRangeListener();
	};
	
	@Override
	protected void initDefaultBattleRange() {
		// TODO Auto-generated method stub
		super.initDefaultBattleRange();
		
		setBattleRangeListener();
	}
	
	protected void setBattleRangeListener(){
		battleRangeDetectArea.setSpriteDetectAreaListener(new ISpriteDetectAreaListener() {
			
			@Override
			public boolean stopDoSuccessorDetected(DetectArea handlerDetectArea,
					IDetectAreaRequest requestDetectArea, boolean isDetected) {
				// TODO Auto-generated method stub
				return !isInWatchRangeChecking;
			}
			
			@Override
			public void didDetected(DetectArea handlerDetectArea,
					IDetectAreaRequest requestDetectArea) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void setWatchRange(float watchRange){
		this.watchRange = watchRange;
		DetectArea newDetectArea = new DetectAreaRound(new PointF(getCenterX(), getCenterY()), watchRange);
		if(watchRangeDetectArea==null)
			effectRangeDetectAreaHandler.addSuccessorDetectArea(newDetectArea);
		else
			effectRangeDetectAreaHandler.replaceDetectArea(watchRangeDetectArea, newDetectArea);
		effectRangeDetectAreaHandler.apply();
		watchRangeDetectArea = newDetectArea;
	}
	
//	private void checkIfInWatchRangeThenMove(List<BattleableSprite> battleables){
//		for(BattleableSprite battleableSprite : battleables){
//			boolean isInWatchRange = isInWatchRange(battleableSprite);
//			if(isInWatchRange){ 
//				setMovementAction(battleableSprite);
//			}
//		}
//	}
//	
//	private boolean isInWatchRange(BattleableSprite battleable) {
//		// TODO Auto-generated method stub
//		if(!battleable.isBattleable()){
//			return false;
//		}
//		
//		double distance = Math.pow(getCenterX() - battleable.getCenterX(), 2) + Math.pow(getCenterY() - battleable.getCenterY(), 2);
//		distance = Math.sqrt(distance);
//		Log.e("distance", distance+"");
//		float watchR = 500;
//		if(distance <= watchR){
//			return true;
//		}
//		return false;
//	}
	
	@Override
	public void frameTrig() {
		// TODO Auto-generated method stub
		super.frameTrig();
		
		if(weapenSprite!=null)
			weapenSprite.frameTrig();
		
	}
	

	
	public boolean isInjuring(){
		return isInjure;
	}
	
	public void setBulletLevel(){
		shootBulletLevel = 0;
	}
	
	public void setBulletLevel2(){
		shootBulletLevel = 1;
	}
	
	public void setBulletLevel3(){
		shootBulletLevel = 2;
		timeCounter = 1000;
	}
	
	public void setBulletLevel5(){
		shootBulletLevel = 4;
	}
	
	public void setAtkAction(){
		isShooting = true;
		setAction(SheepMove.Shoot.getName());
	}
	
	public void setStopAtkAction(){
		setAction(null);
	}
	
	public void cancelMovementAction(){
		if(movementActionShoot!=null){
			movementActionShoot.controller.cancelAllMove();
			movementActionShoot = null;
		}
	}
	
}
