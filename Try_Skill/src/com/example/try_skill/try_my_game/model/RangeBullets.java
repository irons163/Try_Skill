package com.example.try_skill.try_my_game.model;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionInfo;
import com.example.try_gameengine.action.MovementActionItemBaseReugularFPS;
import com.example.try_gameengine.action.MovementActionSetWithThreadPool;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.script.ScriptPaser;
import com.example.try_skill.effect.IEffectable;
import com.example.try_skill.util.BitmapUtil;
import com.example.try_skill.util.CommonUtil;

public class RangeBullets extends Bullets{
	private float AOERange = 200; 
	public RangeBullets(Context context, float x, float y, boolean autoAdd,
			int type_direction) {
		super(context, x, y, autoAdd, type_direction);
		// TODO Auto-generated constructor stub
		setBitmapAndFrameWH(BitmapUtil.hand, BitmapUtil.hand.getWidth()/7, BitmapUtil.hand.getHeight()/2);
		addActionFPSFrame(HandAnimaton.Move.getName(), new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13}, HandAnimaton.Move.getFramesTime());
		
//		setX(x - w/2);
		this.type_direction = type_direction;
		
//		if(type_direction == TYPE_TWO_HAND_LEFT_UP || type_direction == TYPE_TWO_HAND_RIGHT_UP){
//			reflectCounter++;
//		}
		if(type_direction == TYPE_TWO_HAND_LEFT_UP || type_direction == TYPE_TWO_HAND_RIGHT_UP){
			if(type==Top){
				type = Down;
			}else{
				type = Top;
			}
			limit = CommonUtil.cat_bg_height;
		}
		this.context = context;
		initCollisiontRectF();
//		initMove();
	}
	
	private void initCollisiontRectF(){
		setCollisionRectFEnable(true);
		float collisionWidth = w/3.0f;
		float collisionHitght = h/3.0f;
		float collisionOffsetX = w/2-collisionWidth/2;
		float collisionOffsetY = h/2-collisionHitght/2;
		setCollisionOffsetXY(collisionOffsetX, collisionOffsetY);
		setCollisionRectFWH(collisionWidth, collisionHitght);
		setCollisionRectF(getX()+collisionOffsetX, getY()+collisionOffsetY, getX()+collisionOffsetX+collisionWidth, getY()+collisionOffsetY+collisionHitght);
	}

}
