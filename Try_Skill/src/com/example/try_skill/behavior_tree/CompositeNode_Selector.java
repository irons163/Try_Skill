package com.example.try_skill.behavior_tree;

public class CompositeNode_Selector extends CompositeNode{
	
	@Override
	public Task CreateTask() {
		// TODO Auto-generated method stub
		return new SelectorTask(this);
	}
	
	@Override
	public void DestroyTask(Task pTask) {
		// TODO Auto-generated method stub
		SelectorTask pTestp = (SelectorTask) pTask;
		pTestp.stop();
		pTestp = null;
	}
}

//class CompositeNode_Selector : public CompositeNode
//{
//public:
//	virtual Task* CreateTask()
//	{
//		return new SelectorTask(this);
//	}
//	virtual void DestroyTask(Task* pTask)
//	{
//		SelectorTask* pTest = dynamic_cast<SelectorTask*>(pTask);
//		D_CHECK(pTest);
//		D_SafeDelete(pTest);
//	}
//};