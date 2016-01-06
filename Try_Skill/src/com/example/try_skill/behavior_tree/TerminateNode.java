package com.example.try_skill.behavior_tree;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class TerminateNode extends Node{
	private Class<? extends Task> taskClass;
	private List<Class<? extends TaskDecorator>> taskDecoratorClasses;
	
	public TerminateNode(Class<? extends Task> taskClass) {
		// TODO Auto-generated constructor stub
		this.taskClass = taskClass;
	}
	
	public TerminateNode(Class<? extends Task> taskClass, List<Class<? extends TaskDecorator>> taskDecoratorClasses) {
		// TODO Auto-generated constructor stub
		this.taskClass = taskClass;
		this.taskDecoratorClasses = taskDecoratorClasses;
	}

	@Override
	public Task CreateTask() {
		// TODO Auto-generated method stub
		Task task = null;
		try {
			task = taskClass.getConstructor(Node.class).newInstance(this);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(taskDecoratorClasses!=null){
			for(Class<? extends TaskDecorator> taskDecoratorClass : taskDecoratorClasses){
				try {
					task = taskDecoratorClass.getConstructor(Task.class, Node.class).newInstance(task, this);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return task;
	}

	@Override
	public void DestroyTask(Task pTask) {
		// TODO Auto-generated method stub
		pTask.stop();
		pTask = null;
	}
}
