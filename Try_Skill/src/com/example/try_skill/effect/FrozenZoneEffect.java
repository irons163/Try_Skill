package com.example.try_skill.effect;

import com.example.try_skill.center_notification.NSANotification;

public class FrozenZoneEffect extends FrozenEffect{
	
	@Override
	public void receiveNotification(NSANotification nsaNotification) {
		// TODO Auto-generated method stub
//		super.receiveNotification(nsaNotification);
		if(nsaNotification.getName().equals("effect")){
			this.effectListener.didEffect(this);
		}
	}
}
