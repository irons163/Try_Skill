package com.example.try_skill.behavior_tree;

public class TaskResultInverseDecorator extends TaskDecorator{

	public TaskResultInverseDecorator(Task task, Node pNode) {
		super(task, pNode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BevRunningStatus OnUpdate(Object inputParam, Object outputParam) {
		// TODO Auto-generated method stub
		BevRunningStatus bevRunningStatus = super.OnUpdate(inputParam, outputParam);
		if(bevRunningStatus==BevRunningStatus.k_BRS_Finish)
			bevRunningStatus = BevRunningStatus.k_BRS_Failure;
		else if(bevRunningStatus==BevRunningStatus.k_BRS_Failure)
			bevRunningStatus = BevRunningStatus.k_BRS_Finish;
		
		return bevRunningStatus;
	}
}
