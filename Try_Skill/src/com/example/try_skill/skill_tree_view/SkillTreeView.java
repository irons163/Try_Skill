package com.example.try_skill.skill_tree_view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.try_gameengine.extension_module.skill.ASkill;
import com.example.try_gameengine.extension_module.skill.SkillManager;
import com.example.try_gameengine.framework.ButtonLayer;
import com.example.try_gameengine.framework.Layer;

public class SkillTreeView extends Layer{
	//like backpackage
	private List<ASkill> skills;
	private List<SkillView> skillViews;
	private List<Float> linesPts;
//	private Paint paint;
	private ButtonLayer closeButton;
	private ButtonLayer switchModeButton;
	
	private TouchMode touchMode = TouchMode.INCREASE;
	
	enum TouchMode{
		INCREASE, DESCREASE
	}
	
	public SkillTreeView(Bitmap bitmap, float x, float y, boolean autoAdd, List<ASkill> skills) {
		super(bitmap, x, y, autoAdd);
		// TODO Auto-generated constructor stub
		this.skills = skills;
		this.skillViews = new ArrayList<SkillView>();
//		initSkillTreeItem();
		initButton();
	}
	
	public SkillTreeView(Bitmap bitmap, int w, int h, boolean autoAdd, List<ASkill> skills){
		super(bitmap, w, h, autoAdd);
		this.skills = skills;
		this.skillViews = new ArrayList<SkillView>();
//		initSkillTreeItem();
		initButton();
	}
	
	private void initButton(){
		closeButton = new ButtonLayer("Close", 100, 100, false);
		closeButton.setTextColor(Color.WHITE);
//		closeButton.setPosition(this.w/2, this.h);
		LayerParam layerParam = new LayerParam();
		layerParam.setEnabledPercentagePositionX(true);
		layerParam.setEnabledPercentagePositionY(true);
		layerParam.setPercentageX(0.5f);
		layerParam.setPercentageY(1.0f);
		closeButton.setLayerParam(layerParam);
		addChild(closeButton);
		closeButton.setOnClickListener(new ButtonLayer.OnClickListener() {
			
			@Override
			public void onClick(ButtonLayer buttonLayer) {
				// TODO Auto-generated method stub
				SkillTreeView.this.removeFromParent();
//				SkillTreeView.this.setHidden(true);
			}
		});
		
		switchModeButton = new ButtonLayer("Decrease Mode", 200, 100, false);
		switchModeButton.setTextColor(Color.WHITE);
//		closeButton.setPosition(this.w/2, this.h);
		layerParam = new LayerParam();
		layerParam.setEnabledPercentagePositionX(true);
		layerParam.setEnabledPercentagePositionY(true);
		layerParam.setPercentageX(1.0f);
		layerParam.setPercentageY(1.0f);
		switchModeButton.setLayerParam(layerParam);
		addChild(switchModeButton);
		switchModeButton.setOnClickListener(new ButtonLayer.OnClickListener() {
			
			@Override
			public void onClick(ButtonLayer buttonLayer) {
				// TODO Auto-generated method stub
//				SkillTreeView.this.removeFromParent();
//				SkillTreeView.this.setHidden(true);
				if(touchMode==TouchMode.INCREASE){
					touchMode = TouchMode.DESCREASE;
					switchModeButton.setText("Increase Mode");
				}else{
					touchMode = TouchMode.INCREASE;
					switchModeButton.setText("Decrease Mode");
				}
			}
		});
	}
	
	@Override
	public void setInitHeight(int h) {
		// TODO Auto-generated method stub
		super.setInitHeight(h);
//		if(closeButton!=null)
//		closeButton.setPosition(this.w/2, this.h);
	}
	
	@Override
	public void setInitWidth(int w) {
		// TODO Auto-generated method stub
		super.setInitWidth(w);
//		if(closeButton!=null)
//		closeButton.setPosition(this.w/2, this.h);
	}
	
//	private Point[][] boardPositionOfSkills;
	private Point[][] boardPositionOfSkills;
	
	public void initSkillTreeItemWithBoardRowColNumAndBoardPosition(int rowNum, int ColNum,Point[][] boardPositionOfSkills){
		
	}
	
	public void addSkillView(SkillView skillView){
		this.skillViews.add(skillView);
		skillView.setOnSkillViewClickListener(new SkillView.OnSkillViewClickListener() {
			
			@Override
			public void onClick(SkillView skillView) {
				// TODO Auto-generated method stub
				ASkill skill = skillView.getSkill();
				switch (touchMode) {
				case INCREASE:
					if(skill.isSkillLearnAble()){
						skill.increaseSkillLevel();
						skillView.getNumLabelLayer().setText(skill.getCurrentSkillLevel()+"");
					}
					break;

				case DESCREASE:
//					if(skill.isSkillLearnAble()){
//						skill.increaseSkillLevel();
//						skillView.getNumLabelLayer().setText(skill.getCurrentSkillLevel()+"");
//					}
					SkillManager.decreaseSkillLevelToTargetSkillByCheckWholeSkillTreeValid(skill, skills);
					skillView.getNumLabelLayer().setText(skill.getCurrentSkillLevel()+"");
					break;
				}
			}
		});
		addChild(skillView);
		resetPostionXY(skillView);
	}
	
	private void resetPostionXY(SkillView skillView){
		Point positionInBoard = skillView.getPositnioInBoard();
		if(positionInBoard.x < 5 && positionInBoard.y < 5){
			skillView.setPosition(positionInBoard.x * 100, positionInBoard.y * 100);
		}
	}
	
	public void addAutoLine(){
		if(linesPts==null)
			linesPts = new ArrayList<Float>();
		else
			linesPts.clear();
		
		Paint paint = new Paint();
		paint.setColor(Color.YELLOW);
		setPaint(paint);
		
		for(SkillView skillView : skillViews){
			for(Entry<ASkill, Integer> conditionSkills : skillView.getSkill().getConditionSkills().entrySet()){
				SkillView conditionSkillView = getSkillViewBySkill(conditionSkills.getKey());
//				linesPts.add(conditionSkillView.locationInSceneByCompositeLocation(conditionSkillView.getCenterX(), conditionSkillView.getCenterY()).x);
				linesPts.add(conditionSkillView.getLocationInScene().x + conditionSkillView.w/2);
				linesPts.add(conditionSkillView.getLocationInScene().y + conditionSkillView.h/2);
				linesPts.add(skillView.getLocationInScene().x + skillView.w/2);
				linesPts.add(skillView.getLocationInScene().y + skillView.h/2);
			}
		}
	}
	
	private SkillView getSkillViewBySkill(ASkill skill){
		for(SkillView skillView : skillViews){
			if(skillView.getSkill()==skill){
				return skillView;
			}
		}
		return null;
	}
	
	
	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		super.drawSelf(canvas, paint);
		
		
		if(linesPts!=null)
			canvas.drawLines(toIntArray(linesPts), getPaint());
	}
	
	private float[] toIntArray(List<Float> floatList) {
		float[] floatArray = new float[floatList.size()];
	    for (int i = 0; i < floatList.size(); i++) {
	    	floatArray[i] = floatList.get(i);
	    }
	    return floatArray;
	}
}
