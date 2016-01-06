package com.example.try_skill.behavior_tree;

public abstract class TaskDecorator extends Task{
	private Task task = null;
	
	public TaskDecorator(Task task, Node pNode) {
		super(pNode);
		this.task = task;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OnInit(Object inputParam) {
		// TODO Auto-generated method stub
		this.task.OnInit(inputParam);
	}
	
	@Override
	public BevRunningStatus OnUpdate(Object inputParam, Object outputParam) {
		// TODO Auto-generated method stub		
		return this.task.OnUpdate(inputParam, outputParam);
	}
	
	@Override
	public void OnTerminate(Object inputParam) {
		// TODO Auto-generated method stub
		this.task.OnTerminate(inputParam);
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		this.task.stop();
	}
}
