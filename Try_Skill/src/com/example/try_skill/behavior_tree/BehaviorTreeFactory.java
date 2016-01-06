package com.example.try_skill.behavior_tree;

import java.util.List;

public class BehaviorTreeFactory {
	
	public static TerminateNode createMoveToTerminateNode(){
		return new TerminateNode(MoveToTask.class);
	}
	
	public static TerminateNode createMoveToTerminateNodeForTest(){
		return new TerminateNode(MoveToTaskForTest.class);
	}
	
	public static TerminateNode createMoveToActionTerminateNode(){
		return new TerminateNode(MoveToTask.class);
	}
	
	public static TerminateNode createTerminateNode(Class<? extends Task> taskClass){
		return new TerminateNode(taskClass);
	}
	
	public static TerminateNode createTerminateNode(Class<? extends Task> taskClass, List<Class<? extends TaskDecorator>> taskDecoratorClasses){
		return new TerminateNode(taskClass, taskDecoratorClasses);
	}
}
