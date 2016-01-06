package com.example.try_skill.behavior_tree;

public class CompositeNode_Parallel extends CompositeNode{
	
	@Override
	public Task CreateTask() {
		return new ParallelTask(this);
	}
	
	@Override
	public void DestroyTask(Task pTask) {
		ParallelTask pTest = (ParallelTask) pTask;
		pTest.stop();
		pTask = null;
	}
}

//class CompositeNode_Parallel : public CompositeNode
//{
//public:
//	virtual Task* CreateTask()
//	{
//		return new ParallelTask(this);
//	}
//	virtual void DestroyTask(Task* pTask)
//	{
//		ParallelTask* pTest = dynamic_cast<ParallelTask*>(pTask);
//		D_CHECK(pTest);
//		D_SafeDelete(pTest);
//	}
//};