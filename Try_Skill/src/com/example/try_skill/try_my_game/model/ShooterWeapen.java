package com.example.try_skill.try_my_game.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.try_skill.effect.EffectSprite;
import com.example.try_skill.effect.IEffect;
import com.example.try_skill.effect.IEffectable;
import com.example.try_skill.try_my_game.model.BulletsBuilder.BulletEnum;
import com.example.try_skill.util.Attribute;
import com.example.try_skill.util.AttributeHelper;
import com.example.try_skill.util.CommonUtil;

public class ShooterWeapen extends WeapenSprite{
	protected List<BaseBullet> bulletsList = new CopyOnWriteArrayList<BaseBullet>();
	private Context context;
	private BulletEnum bulletEnum;
	
	public ShooterWeapen(Context context, float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public ShooterWeapen(Context context, float x, float y, boolean autoAdd, float interval) {
		super(x, y, autoAdd, interval);
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	@Override
	protected void initAttribute() {
		attribute = new Attribute();
		
		if(interval<=-1){
			interval = new BigDecimal(3.0f / 1.0f).setScale(1,
				BigDecimal.ROUND_HALF_UP).floatValue();
		}
		
		attribute.setInterval(interval);
		attributeHelper = new AttributeHelper(attribute);
	}
	
	public void setBulletEnum(BulletEnum bulletEnum){
		this.bulletEnum = bulletEnum;
	}
	
	interface ShooterEventListener{
		void willAttack(IEffectable battleableSprite);
	}

	ShooterEventListener shooterEventListener;

	public void setShooterEventListener(ShooterEventListener shooterEventListener){
		this.shooterEventListener = shooterEventListener;
	}

	@Override
	public void attack(IEffectable battleable) {
		// TODO Auto-generated method stub
		long currentTime = System.currentTimeMillis();
		if (!attributeHelper.isCanShoot(lastShootTime, currentTime)) {
			return;
		}
		
		lastShootTime = currentTime;
//		final BaseBullet bullets = BulletsBuilder.createFrozenBullets(context, getX(), getY());
		final BaseBullet bullets = BulletsBuilder.createBulletByBulletEnum(bulletEnum, context, getX(), getY());
		bullets.setBulletsEventListener(new BaseBullet.BulletsEventListener() {
			
			@Override
			public void willAttack(IEffectable battleableSprite) {
				// TODO Auto-generated method stub
				shooterEventListener.willAttack(battleableSprite);
				bullets.getMovementAction().controller.cancelAllMove();
				bulletsList.remove(bullets);
			}
		});
		bulletsList.add(bullets);
	}
	
	@Override
	public void checkIfInBattleRangeThenAttack(
			List<EffectSprite> battleables) {
		// TODO Auto-generated method stub
		super.checkIfInBattleRangeThenAttack(battleables);
		
		for(BaseBullet bullets : bulletsList){
			bullets.checkIfInBattleRangeThenAttack(battleables);
		}
	}
	
	@Override
	public void frameTrig() {
		// TODO Auto-generated method stub
		super.frameTrig();
		
		for(BaseBullet bullets : bulletsList){
			if(bullets.getX() > CommonUtil.screenWidth){
				bullets.getMovementAction().controller.cancelAllMove();
				bulletsList.remove(bullets);
			}
		}
		
		for(BaseBullet bullets : bulletsList){
			bullets.frameTrig();
		}
	}
	
	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
//		super.drawSelf(canvas, paint);
		
		for(BaseBullet bullets : bulletsList){
			bullets.drawSelf(canvas, paint);
		}
	}
}
