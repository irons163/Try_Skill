package com.example.try_skill.behavior_tree;

import com.example.try_skill.behavior_tree.Task.BevRunningStatus;

public class Behavior {
	private Node m_pNode;
	private Task m_pTask;
	private BevRunningStatus m_eStatus;
	
	public Behavior() {
		m_eStatus = BevRunningStatus.k_BRS_Invalid;
	}
	
	public Behavior(Node node) {
		m_eStatus = BevRunningStatus.k_BRS_Invalid;
		Install(node);
	}
	
	public void Install(Node node) {
		Uninstall();
	
		m_pNode = node;
		m_pTask = m_pNode.CreateTask();
	}
	
	public void Uninstall() {
		if(m_pTask==null) return;
		if(m_pNode==null) return;
		m_pNode.DestroyTask(m_pTask);
		m_pTask = null;
		m_eStatus = BevRunningStatus.k_BRS_Invalid;
	}
	
	public boolean HasInstalled(){
		return m_pTask != null;
	}
	
	public BevRunningStatus Update(final Object inputParam, Object outputParam){
		if(m_eStatus == BevRunningStatus.k_BRS_Invalid)
			m_pTask.OnInit(inputParam);
		BevRunningStatus ret = m_pTask.OnUpdate(inputParam, outputParam);
		if(ret != BevRunningStatus.k_BRS_Executing)
		{
			m_pTask.OnTerminate(inputParam);
			m_eStatus = BevRunningStatus.k_BRS_Invalid;
		}
		else
		{
			m_eStatus = ret;
		}
		return ret;
	}
}

//class Behavior
//{
//public:
//	Behavior()
//		:m_pTask(NULL)
//		,m_pNode(NULL)
//		,m_eStatus(k_BRS_Invalid)
//	{
//	}
//	Behavior(Node& node)
//		:m_pTask(NULL)
//		,m_eStatus(k_BRS_Invalid)
//	{
//		Install(node);
//	}
//	virtual ~Behavior()
//	{
//		Uninstall();
//	}
//
//	bool HasInstalled() const
//	{
//		return m_pTask != NULL;
//	}
//
//	void Install(Node& node)
//	{
//		Uninstall();
//
//		m_pNode = &node;
//		m_pTask = m_pNode->CreateTask();
//	}
//	void Uninstall()
//	{
//		if(!m_pTask) return;
//		if(!m_pNode) return;
//		m_pNode->DestroyTask(m_pTask);
//		m_pTask = NULL;
//		m_eStatus = k_BRS_Invalid;
//	}
//	BevRunningStatus Update(const BevNodeInputParam& inputParam,BevNodeOutputParam& outputParam)
//	{
//		if(m_eStatus == k_BRS_Invalid)
//			m_pTask->OnInit(inputParam);
//		BevRunningStatus ret = m_pTask->OnUpdate(inputParam, outputParam);
//		if(ret != k_BRS_Executing)
//		{
//			m_pTask->OnTerminate(inputParam);
//			m_eStatus = k_BRS_Invalid;
//		}
//		else
//		{
//			m_eStatus = ret;
//		}
//		return ret;
//	}
//
//private:
//	Node* m_pNode;
//	Task* m_pTask;
//	BevRunningStatus m_eStatus;
//};