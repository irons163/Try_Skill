package com.example.try_skill.behavior_tree;

public abstract class Node {
	protected Node m_pParent;
	
	public abstract Task CreateTask();
	public abstract void DestroyTask(Task pTask);
	
	public void SetParent(Node node){
		if(m_pParent != null)
		{
			System.out.println("has parent already?\n");
		}
		m_pParent = node;
	}
}
//class Node
//{
//public:
//	Node()
//		:m_pParent(NULL)
//	{}
//	virtual ~Node(){};
//	virtual Task* CreateTask() = 0;
//	virtual void DestroyTask(Task* pTask) = 0;
//
//	void SetParent(Node* node)
//	{
//		if(m_pParent)
//		{
//			D_Output("has parent already?\n");
//		}
//		m_pParent = node;
//	}
//
//protected:
//	Node* m_pParent;
//};