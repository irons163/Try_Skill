package com.example.try_skill.behavior_tree;

public class CompositeNode_Sequence extends CompositeNode{
	
	@Override
	public Task CreateTask(){
		return new SequenceTask(this);
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