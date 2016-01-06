package com.example.try_skill.sprite_skill;

import android.graphics.PointF;

import com.example.try_gameengine.utils.DetectArea;
import com.example.try_skill.ASkill;
import com.example.try_skill.center_notification.NSANotificationCenter;
import com.example.try_skill.effect.FrozenEffect;
import com.example.try_skill.effect.IEffect;

public class SkillWithArea {
	private ASkill skill;
	private DetectArea detectArea;
	private float x, y;
	private boolean isTargetToEnemy = true;
	private IEffect effect;
	
	public SkillWithArea(ASkill skill, DetectArea detectArea){
		this.skill = skill;
		this.detectArea = detectArea;
		this.effect = new FrozenEffect();
		NSANotificationCenter.defaultCenter().addObserver(effect, "effect", null);
	}
	
	public void setPosition(float x, float y){
		detectArea.setCenter(new PointF(x, y));
	}
	
	public boolean checkDetectArea(DetectArea detectArea){
		return detectArea.detectConditionWithTwoArea(this.detectArea, detectArea);
	}
	
	public ASkill getSkill(){
		return skill;
	}

	public boolean isTargetToEnemy() {
		return isTargetToEnemy;
	}

	public void setTargetToEnemy(boolean isTargetToEnemy) {
		this.isTargetToEnemy = isTargetToEnemy;
	}
	
}
