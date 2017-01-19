package com.example.try_skill.try_my_game.model;

import android.util.Log;

import com.example.try_gameengine.action.MathUtil;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionInfo;
import com.example.try_gameengine.action.MovementActionItemBaseReugularFPS;
import com.example.try_gameengine.action.MovementActionSetWithThreadPool;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.utils.DetectArea;
import com.example.try_gameengine.utils.DetectAreaRect;
import com.example.try_gameengine.utils.DetectAreaRound;
import com.example.try_gameengine.utils.DetectAreaType;
import com.example.try_skill.effect.EffectSprite;
import com.example.try_skill.util.BattleUtil;

public class Medic extends Warrior{

	public Medic(float x, float y, boolean autoAdd, AttackType attackType) {
		super(x, y, autoAdd, attackType);
		// TODO Auto-generated constructor stub
	}

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
}
