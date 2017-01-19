package com.example.try_skill.try_my_game.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.try_gameengine.framework.IActionListener;
import com.example.try_skill.effect.EffectSprite;
import com.example.try_skill.effect.IEffect;
import com.example.try_skill.util.Attribute;
import com.example.try_skill.util.AttributeHelper;
import com.example.try_skill.util.BattleUtil;
import com.example.try_skill.util.BitmapUtil;


public class Defener extends EffectSprite{
	Attribute attribute;
	public AttributeHelper attributeHelper;
	long lastShootTime;
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
	
	protected EffectSprite weapenSprite;
	private AttackType attackType;
	
	protected List<IEffect> effectsForAllies = new ArrayList<IEffect>(); // for allies effects.
	protected List<IEffect> effectsForEnemy = new ArrayList<IEffect>(); // for enemy effects.
	
	public enum AttackType {

		Shoot(
		"Shoot",
		new int[]{0,10,0,1}),		
		Melee(
		"Melee",
		new int[]{0,10,11,1}),	
		;

		String name;
		int[] sequence;

		private AttackType(String name, int[] sequenceForAnimation) {
			this.name = name;
			this.sequence = sequenceForAnimation;
		}

		public String getName() {
			return name;
		}

		public int[] getSequence() {
			return sequence;
		}
	}
	
	public Defener(float x, float y, boolean autoAdd, AttackType attackType) {
		super(x, y, autoAdd);
		this.attackType = attackType;
		
		initAttribute();
		initDefaultBitmap();
		initDefaultAction();
		initCollisiontRectF();
	}
	
	protected void initDefaultBitmap(){
		setBitmapAndFrameWH(BitmapUtil.hamster, BitmapUtil.hamster.getWidth()/7, BitmapUtil.hamster.getHeight()/2);
	}
	
	protected void initDefaultAction(){	
		addActionFPSFrame(attackType.getName(), attackType.getSequence(), BattleUtil.changeToNew(new int[]{0,5,5,5}, getAttributeInfo().getBattleInviable()), true, new IActionListener() {
			
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
		
		setAction(attackType.getName());
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
	
	protected void initAttribute(){
		attribute = new Attribute();
		float interval = new BigDecimal(1.0f/1.0f)
        .setScale(2, BigDecimal.ROUND_HALF_UP)
        .floatValue();
		attribute.setInterval(interval);
		attributeHelper = new AttributeHelper(attribute);
	}

	private void shoot(){
		long currentTime = System.currentTimeMillis();
		if(!attributeHelper.isCanShoot(lastShootTime, currentTime)){
			return;
		}
		isShooting = true;
		isMoveing = false;
		lastShootTime = currentTime;
		
		if(shootBulletLevel==2){
			if(timeCounter<=0){
				shootBulletLevel = 0;
			}
		}
		
		if(shootBulletLevel==0 || shootBulletLevel==4){
			Bullet bullet = new Bullet(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY(), false, shootBulletLevel);
//			Bullet bullet = new Bullet(this.getX(), this.getY(), false, shootBulletLevel);
			bullet.setPosition(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY());
			bullets.add(bullet);
		}else if(shootBulletLevel==1){
			Bullet bullet = new Bullet(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY(), false, shootBulletLevel+4);
			bullet.setPosition(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY());
			bullets.add(bullet);
			bullet = new Bullet(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY(), false, shootBulletLevel+5);
			bullet.setPosition(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY());
			bullets.add(bullet);
		}else if(shootBulletLevel==2){
			Bullet bullet = new Bullet(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY(), false, shootBulletLevel);
			bullet.setPosition(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY());
			bullets.add(bullet);
			bullet = new Bullet(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY(), false, shootBulletLevel-1);
			bullet.setPosition(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY());
			bullets.add(bullet);
			bullet = new Bullet(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY(), false, shootBulletLevel-2);
			bullet.setPosition(this.getX()+w/2-BitmapUtil.bullet.getWidth()/3.0f/2, this.getY());
			bullets.add(bullet);
		}

		setAction(attackType.getName());
	}
	
	public boolean checkEnemyIfInBattleRangeThenAttack(List<EffectSprite> battleables){
		effects = effectsForEnemy;
		return checkIfInBattleRangeThenAttack(battleables);
	}
	
	public boolean checkAlliesIfInBattleRangeThenAttack(List<EffectSprite> battleables){
		effects = effectsForAllies;
		return checkIfInBattleRangeThenAttack(battleables);
	}
	
	@Override
	public boolean checkIfInBattleRangeThenAttack(List<EffectSprite> battleables){
		if(weapenSprite==null){
			return super.checkIfInBattleRangeThenAttack(battleables);
		}else{
			return weapenSprite.checkIfInBattleRangeThenAttack(battleables);
		}	
	}
	
	@Override
	public void updateSpriteDetectAreaCenter(PointF center) {
		// TODO Auto-generated method stub
		super.updateSpriteDetectAreaCenter(center);
		if(weapenSprite!=null)
			weapenSprite.updateSpriteDetectAreaCenter(center);
	}
	
	@Override
	public void frameTrig() {
		// TODO Auto-generated method stub
		super.frameTrig();
		
		if(weapenSprite!=null)
			weapenSprite.frameTrig();
		
//		if(isPrepareToShoot){
//			this.shoot();
//			isPrepareToShoot = false;
//			
//		}
//		
//		if(shootBulletLevel==2){
//			timeCounter--;
//		}
//			
//		injuring();
//		
//		if(!isInjuring()){
//			for(Bullet bullet : bullets){
//				bullet.frameTrig();
//			}	
//		}
	}
	
	@Override
	public void drawSelf(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		super.drawSelf(canvas, paint);
		
		if(weapenSprite!=null)
			weapenSprite.drawSelf(canvas, paint);
	}
	
	private void injuring(){
		if(!isInjure)
			return;
		hamsterInjureCounter++;
		if(HAMSTER_INJURE_TIME <= hamsterInjureCounter){
			isInjure = false;
			isInvincible = true;
			hamsterInjureCounter = 0;
			bitmap = BitmapUtil.hamster;
		}
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
	
//	WeapenSprite weapen;
	
	public void setWeapen(WeapenSprite weapen){
		this.weapenSprite = weapen;
	}
	
	@Override
	public void addEffect(IEffect effect) {
		// TODO Auto-generated method stub
		effects = effectsForEnemy;
		super.addEffect(effect);
	}
	
	@Override
	public void addEffects(List<IEffect> effects) {
		// TODO Auto-generated method stub
		effects = effectsForEnemy;
		super.addEffects(effects);
	}
	
	public void addEffectForAllies(IEffect effect){
		effects = effectsForAllies;
		super.addEffect(effect);
	}
	
	public void addEffectsForAllies(List<IEffect> effects) {
		// TODO Auto-generated method stub
		effects = effectsForAllies;
		super.addEffects(effects);
	}
	
//	@Override
//	public void attack(BattleableSprite battleable) {
//		// TODO Auto-generated method stub
//		shoot();
//		
//		AttributeInfo attributeInfoNew;
//		if(weapenSprite!=null){
//			weapenSprite.attack(battleable);
//			attributeInfoNew = weapenSprite.getNewAttributeInfo(attributeInfo);
//			
//		}
//		AttributeInfo attributeInfoBeAttacked = battleable.getAttributeInfo();
//		
//		
//	}
}
