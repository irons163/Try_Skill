package com.example.try_skill.skill_tree_view;

import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;

import com.example.try_gameengine.framework.ButtonLayer;
import com.example.try_gameengine.framework.ILayer;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_skill.ASkill;
import com.example.try_skill.SkillManager;

public class SkillView extends ButtonLayer{
	private ASkill skill;
	private Point postionInBoard;
	private LabelLayer numLabelLayer;
	
	private OnSkillViewClickListener onSkillViewClickListener;
	
	public interface OnSkillViewClickListener{
		public void onClick(SkillView skillView);
	}
	
	public SkillView(int w, int h, boolean autoAdd, ASkill skill) {
		super(w, h, autoAdd);
		// TODO Auto-generated constructor stub
		this.skill = skill;
		numLabelLayer = new LabelLayer(skill.getCurrentSkillLevel()+"", 0f, 0f, false);
		numLabelLayer.setTextColor(Color.WHITE);
		addChild(numLabelLayer);
	}
	
	public void setPositionInBoard(Point postionInBoard){
		this.postionInBoard = postionInBoard;
	}
	
	public Point getPositnioInBoard(){
		return postionInBoard;
	}
	
	public ASkill getSkill(){
		return skill;
	}
	
//	public TouchMode getTouchMode() {
//		return touchMode;
//	}
//
//	public void setTouchMode(TouchMode touchMode) {
//		this.touchMode = touchMode;
//	}
	
	public OnSkillViewClickListener getOnSkillViewClickListener() {
		return onSkillViewClickListener;
	}

	public void setOnSkillViewClickListener(
			OnSkillViewClickListener onSkillViewClickListener) {
		this.onSkillViewClickListener = onSkillViewClickListener;
	}
	
	public LabelLayer getNumLabelLayer() {
		return numLabelLayer;
	}

	public void setNumLabelLayer(LabelLayer numLabelLayer) {
		this.numLabelLayer = numLabelLayer;
	}

	@Override
	public void setPosition(float x, float y) {
		// TODO Auto-generated method stub
		super.setPosition(x, y);
		for(ILayer child : getLayers()){
			child.setLocationInScene(locationInSceneByCompositeLocation(child.getX(), child.getY()));
		}
	}
	
	@Override
	public boolean onTouch(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouch(event);
	}
	
	@Override
	protected void onTouched(MotionEvent event) {
		// TODO Auto-generated method stub
		super.onTouched(event);
		if(event.getAction()==MotionEvent.ACTION_UP && isPressed()){
			if(onSkillViewClickListener!=null)
				onSkillViewClickListener.onClick(this);
//			switch (touchMode) {
//			case INCREASE:
//				if(skill.isSkillLearnAble()){
//					skill.increaseSkillLevel();
//					numLabelLayer.setText(skill.getCurrentSkillLevel()+"");
//				}
//				break;
//
//			case DESCREASE:
//				if(skill.isSkillLearnAble()){
//					skill.increaseSkillLevel();
//					numLabelLayer.setText(skill.getCurrentSkillLevel()+"");
//				}
//				SkillManager.decreaseSkillLevelToTargetSkillByCheckWholeSkillTreeValid(skill, SkillTree);
//				break;
//			}
			
		}
	}
}
