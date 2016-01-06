package com.example.try_skill.effect;

import android.util.Log;

public class IntervalTimer {
	private float interval;
	private long lastShootTime;
	private boolean isFirstTimeDelay = false;
	private boolean isLastTimeDelay = true;
	
	public IntervalTimer(float interval){
		this.interval = interval;
	}
	
	public void setFirstTimeDelay(boolean isFirstTimeDelay){
		this.isFirstTimeDelay = isFirstTimeDelay;
	}
	
	public void setLastTimeDelay(boolean isLastTimeDelay){
		this.isLastTimeDelay = isLastTimeDelay;
	}
	
	public boolean isFirstTimeDelay(){
		return isFirstTimeDelay;
	}
	
	public boolean isLastTimeDelay(){
		return isLastTimeDelay;
	}
	
	public boolean isCanShoot(){
		long currentTime = System.currentTimeMillis();
		boolean isCanShoot = false;
		long nextShootTime;
		
		if(isFirstTimeDelay){
			nextShootTime = currentTime + (long) (interval*1000);
			lastShootTime = currentTime;
			isFirstTimeDelay = false;
		}else
			nextShootTime = lastShootTime + (long) (interval*1000);
		
		Log.e("interval", interval+"x");
		if(nextShootTime < currentTime ){
			isCanShoot = true;
		}
		
		return isCanShoot;
	}
	
	public void executeAndGotoColdTime(){
		long currentTime = System.currentTimeMillis();
		lastShootTime = currentTime;
	}
	
	public void pause(){
		
	}
	
	public void resume(){
		
	}
}
