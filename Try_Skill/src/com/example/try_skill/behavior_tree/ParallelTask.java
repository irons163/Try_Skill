package com.example.try_skill.behavior_tree;

import java.util.ArrayList;
import java.util.List;

public class ParallelTask extends Task{
	private List<Behavior> m_Behaviors;
	
	public ParallelTask(Node pNode) {
		super(pNode);
		// TODO Auto-generated constructor stub
		m_Behaviors = new ArrayList<Behavior>();
		
		CompositeNode comNode = GetCompositeNode();
		for(int i = 0; i < comNode.GetChildCount(); ++i)
		{
			m_Behaviors.add(new Behavior());
		}
	}

	@Override
	public void OnInit(Object inputParam) {
		// TODO Auto-generated method stub
		CompositeNode comNode = GetCompositeNode();
//		D_CHECK(comNode.GetChildCount() == (int)m_Behaviors.size());
		for(int i = 0; i < (int)m_Behaviors.size(); ++i)
		{
			m_Behaviors.get(i).Install(comNode.GetChild(i));
		}
	}

	@Override
	public BevRunningStatus OnUpdate(Object inputParam,
			Object outputParam) {
		// TODO Auto-generated method stub
		for(int i = 0; i < (int)m_Behaviors.size(); ++i)
		{
			BevRunningStatus ret = m_Behaviors.get(i).Update(inputParam, outputParam);
			if(ret != BevRunningStatus.k_BRS_Executing)
			{
				return ret;
			}
		}
		return BevRunningStatus.k_BRS_Executing;
	}

	@Override
	public void OnTerminate(Object inputParam) {
		// TODO Auto-generated method stub
		
	}
	
	public CompositeNode GetCompositeNode(){
		return (CompositeNode) m_pNode;
	}
	
	public void stop(){
		for(int i = 0; i < m_Behaviors.size(); ++i)
		{
			m_Behaviors.get(i).Uninstall();
		}
	}
}

//class ParallelTask : public Task
//{
//public:
//	ParallelTask(Node* pNode)
//		: Task(pNode)
//	{
//		CompositeNode& comNode = GetCompositeNode();
//		for(int i = 0; i < comNode.GetChildCount(); ++i)
//		{
//			m_Behaviors.push_back(new Behavior());
//		}
//	}
//	~ParallelTask()
//	{
//		for(int i = 0; i < m_Behaviors.size(); ++i)
//		{
//			D_SafeDelete(m_Behaviors[i]);
//		}
//	}
//	CompositeNode& GetCompositeNode(){
//		return *dynamic_cast<CompositeNode*>(m_pNode);
//	}
//	virtual void OnInit(const BevNodeInputParam& inputParam)
//	{
//		CompositeNode& comNode = GetCompositeNode();
//		D_CHECK(comNode.GetChildCount() == (int)m_Behaviors.size());
//		for(int i = 0; i < (int)m_Behaviors.size(); ++i)
//		{
//			m_Behaviors[i]->Install(*(comNode.GetChild(i)));
//		}
//	}
//	virtual BevRunningStatus OnUpdate(const BevNodeInputParam& inputParam, BevNodeOutputParam& outputParam)
//	{
//		//todo, or
//		for(int i = 0; i < (int)m_Behaviors.size(); ++i)
//		{
//			BevRunningStatus ret = m_Behaviors[i]->Update(inputParam, outputParam);
//			if(ret != k_BRS_Executing)
//			{
//				return ret;
//			}
//		}
//		return k_BRS_Executing;
//	}
//	virtual void OnTerminate(const BevNodeInputParam& inputParam)
//	{
//	}
//private:
//	std::vector<Behavior*> m_Behaviors;
//};