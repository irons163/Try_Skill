package com.example.try_skill.sprite_skill;

import com.example.try_skill.behavior_tree.Node;
import com.example.try_skill.behavior_tree.Task;
import com.example.try_skill.behavior_tree.Task.BevRunningStatus;
import com.example.try_skill.sprite_skill.BTree.Input;

public class CheckIsEnemyTask extends Task{

	public CheckIsEnemyTask(Node pNode) {
		super(pNode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OnInit(Object inputParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BevRunningStatus OnUpdate(Object inputParam, Object outputParam) {
		// TODO Auto-generated method stub
		Input input = (Input) inputParam;
		if(input.sprite.isEnemy())
			return BevRunningStatus.k_BRS_Finish;
		return BevRunningStatus.k_BRS_Failure;
	}

	@Override
	public void OnTerminate(Object inputParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
}
