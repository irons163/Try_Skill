package com.example.try_skill.behavior_tree;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CompositeNodeWithDecoraor_Sequence extends CompositeNode{

	private List<Class<? extends TaskDecorator>> taskDecoratorClasses;
	
	public CompositeNodeWithDecoraor_Sequence(List<Class<? extends TaskDecorator>> taskDecoratorClasses) {
		// TODO Auto-generated constructor stub
		this.taskDecoratorClasses = taskDecoratorClasses;
	}
	
	@Override
	public Task CreateTask() {
		// TODO Auto-generated method stub
		Task task = new SequenceTask(this);
		
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
	public void DestroyTask(Task pTask){
		SequenceTask pTestp = (SequenceTask) pTask;
		pTestp.stop();
		pTestp = null;
	}
}

//class CompositeNode_Sequence : public CompositeNode
//{
//public:
//	virtual Task* CreateTask()
//	{
//		return new SequenceTask(this);
//	}
//	virtual void DestroyTask(Task* pTask)
//	{
//		SequenceTask* pTest = dynamic_cast<SequenceTask*>(pTask);
//		D_CHECK(pTest);
//		D_SafeDelete(pTest);
//	}
//};