package com.example.try_skill;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.try_gameengine.action.MAction;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.framework.BitmapUtil;
import com.example.try_gameengine.framework.ButtonLayer;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_skill.skill_tree_view.SkillTreeView;
import com.example.try_skill.skill_tree_view.SkillView;

public class MyScence extends EasyScene{
	private GameView gameView;
	SkillTreeView skillTreeView;
	ButtonLayer showSkillTreeViewButton;
	
	public MyScence(Context context, String id) {
		super(context, id);
		// TODO Auto-generated constructor stub
		
		Sprite sprite = new Sprite(10, 10, true);
		sprite.setBitmapAndAutoChangeWH(BitmapUtil.redPoint);
		
		Sprite sprite2 = new Sprite(20, 20, true);
		sprite2.setBitmapAndAutoChangeWH(BitmapUtil.bluePoint);
		
//		MovementAction move = MAction.moveTo(30, 30, 20, 2000);
//		MovementAction move2 = MAction.moveTo(40, 40, 20, 2000);
		
		MovementAction move = MAction.moveTo(30, 30, 2000);
		MovementAction move2 = MAction.moveTo(40, 40, 2000);
		
		sprite.runMovementAction(move);
		sprite2.runMovementAction(move2);
		
		List<ASkill> skillTree = new ArrayList<ASkill>();
		ASkill skillA = new SkillA(10);
		ASkill skillB = new SkillB(10);
		ASkill skillC = new SkillC(10);
		skillTree.add(skillA);
		skillTree.add(skillB);
		skillTree.add(skillC);
		
		
		int skillALevelConditionToLearnSkillB = 3;
		skillB.addNextSkillWithConditionSkillLevel(skillA, skillALevelConditionToLearnSkillB);
		int skillALevelConditionToLearnSkillC = 5;
		skillC.addNextSkillWithConditionSkillLevel(skillA, skillALevelConditionToLearnSkillC);
		int skillBLevelConditionToLearnSkillC = 7;
		skillC.addNextSkillWithConditionSkillLevel(skillB, skillBLevelConditionToLearnSkillC);
		
		if(!SkillManager.checkSkillTreeValid(skillTree)){
			finish();
		}
//		SkillManager.meetConditionForLearn(skillC);
		skillC.isSkillLearnAble();
		
		SkillView skillViewA = new SkillView(30, 30, false, skillA);
		skillViewA.setPositionInBoard(new Point(0, 0));
		skillViewA.setBitmapAndAutoChangeWH(BitmapUtil.redPoint);
		skillViewA.setButtonBitmap(BitmapUtil.redPoint, BitmapUtil.greenPoint, BitmapUtil.redPoint);
		SkillView skillViewB = new SkillView(30, 30, false, skillB);
		skillViewB.setBitmapAndAutoChangeWH(BitmapUtil.redPoint);
		skillViewB.setButtonBitmap(BitmapUtil.redPoint, BitmapUtil.greenPoint, BitmapUtil.redPoint);
		skillViewB.setPositionInBoard(new Point(1, 1));
		SkillView skillViewC = new SkillView(30, 30, false, skillC);
		skillViewC.setPositionInBoard(new Point(0, 2));
		skillViewC.setBitmapAndAutoChangeWH(BitmapUtil.redPoint);
		skillViewC.setButtonBitmap(BitmapUtil.redPoint, BitmapUtil.greenPoint, BitmapUtil.redPoint);
		
		skillTreeView = new SkillTreeView(BitmapUtil.redPoint, 300.0f, 300.0f, true, skillTree);
		skillTreeView.setInitWidth(300);
		skillTreeView.setInitHeight(300);
		skillTreeView.addSkillView(skillViewA);
		skillTreeView.addSkillView(skillViewB);
		skillTreeView.addSkillView(skillViewC);
		
		skillTreeView.addAutoLine();
		
//		skillTreeView.setHidden(true);
		
		showSkillTreeViewButton = new ButtonLayer("Show", 100, 100, false);
//		showSkillTreeViewButton.setPosition(300, 800);
		showSkillTreeViewButton.setX(300);
		showSkillTreeViewButton.setY(600);
		showSkillTreeViewButton.setTextColor(Color.WHITE);
		showSkillTreeViewButton.setOnClickListener(new ButtonLayer.OnClickListener() {
			
			@Override
			public void onClick(ButtonLayer buttonLayer) {
				// TODO Auto-generated method stub
				addAutoDraw(skillTreeView);
//				skillTreeView.setHidden(false);
			}
		});
		
		addAutoDraw(showSkillTreeViewButton);
	}

	@Override
	public void initGameView(Activity activity, IGameController gameController,
			IGameModel gameModel) {
		// TODO Auto-generated method stub
		gameView = new GameView(activity, gameController, gameModel);
		
	}

	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		LayerManager.getInstance().drawLayers(canvas, null);
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		// TODO Auto-generated method stub
////		if(!showSkillTreeViewButton.onTouchEvent(event))
////		skillTreeView.onTouchEvent(event);
//		
//		LayerManager.onTouchLayers(event);
//		
//		return true;
//	}
	
	@Override
	public void beforeGameStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void arrangeView(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setActivityContentView(Activity activity) {
		// TODO Auto-generated method stub
		activity.setContentView(gameView);
	}

	@Override
	public void afterGameStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

}
