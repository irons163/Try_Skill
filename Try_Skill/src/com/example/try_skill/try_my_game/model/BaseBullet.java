package com.example.try_skill.try_my_game.model;

import java.util.List;

import com.example.try_skill.effect.IEffect;
import com.example.try_skill.effect.IEffectable;

public class BaseBullet extends WeapenSprite{

	public BaseBullet(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
	}

	interface BulletsEventListener{
		void willAttack(IEffectable battleableSprite);
	}

	BulletsEventListener bulletsEventListener;

	public void setBulletsEventListener(BulletsEventListener bulletsEventListener){
		this.bulletsEventListener = bulletsEventListener;
	}

	@Override
	public void attack(IEffectable battleable) {
		// TODO Auto-generated method stub
		bulletsEventListener.willAttack(battleable);
		
		super.attack(battleable);
	}
	
}
