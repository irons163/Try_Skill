package com.example.try_skill.behavior_tree;

public class SelectorTask extends Task{
	private int m_LastBehavior;
	private Behavior m_CurrentBehavior;
//	private boolean isRun;
	
	public SelectorTask(Node pNode) {
		super(pNode);
		// TODO Auto-generated constructor stub
		m_LastBehavior = -1;
		m_CurrentBehavior = new Behavior();
	}

	@Override
	public void OnInit(Object inputParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BevRunningStatus OnUpdate(Object inputParam,
			Object outputParam) {
		// TODO Auto-generated method stub
		CompositeNode comNode = GetCompositeNode();
		if(comNode.GetChildCount() == 0)
			return BevRunningStatus.k_BRS_Failure;

		if(!m_CurrentBehavior.HasInstalled())
		{
			m_LastBehavior = 0;
			m_CurrentBehavior.Install(comNode.GetChild(m_LastBehavior)); 
		}
		BevRunningStatus status = m_CurrentBehavior.Update(inputParam, outputParam);
		if(status != BevRunningStatus.k_BRS_Failure)
		{
			return status;
		}
		for(int i = 0; i < comNode.GetChildCount(); ++i)
		{
			if(m_LastBehavior == i)
				continue;

			m_CurrentBehavior.Install(comNode.GetChild(i));
			BevRunningStatus _status = m_CurrentBehavior.Update(inputParam, outputParam);
			if(_status != BevRunningStatus.k_BRS_Failure)
			{
				m_LastBehavior = i;
				return _status;
			}
		}
		return BevRunningStatus.k_BRS_Failure;
	}

	@Override
	public void OnTerminate(Object inputParam) {
		// TODO Auto-generated method stub
		m_LastBehavior = -1;
		m_CurrentBehavior.Uninstall();
	}

	public CompositeNode GetCompositeNode(){
		return (CompositeNode) m_pNode;
	}
	
	public void stop(){
//		isRun = false;
	}
}

//class SelectorTask : public Task
//{
//public:
//	SelectorTask(Node* pNode)
//		: Task(pNode)
//		, m_LastBehavior(-1)
//	{}
//	CompositeNode& GetCompositeNode(){
//		return *dynamic_cast<CompositeNode*>(m_pNode);
//	}
//	virtual void OnInit(const BevNodeInputParam& inputParam)
//	{}
//	virtual BevRunningStatus OnUpdate(const BevNodeInputParam& inputParam, BevNodeOutputParam& outputParam)
//	{
//		CompositeNode& comNode = GetCompositeNode();
//		if(comNode.GetChildCount() == 0)
//			return k_BRS_Failure;
//
//		if(!m_CurrentBehavior.HasInstalled())
//		{
//			m_LastBehavior = 0;
//			m_CurrentBehavior.Install(*(comNode.GetChild(m_LastBehavior))); 
//		}
//		BevRunningStatus status = m_CurrentBehavior.Update(inputParam, outputParam);
//		if(status != k_BRS_Failure)
//		{
//			return status;
//		}
//		for(int i = 0; i < comNode.GetChildCount(); ++i)
//		{
//			if(m_LastBehavior == i)
//				continue;
//
//			m_CurrentBehavior.Install(*(comNode.GetChild(i)));
//			BevRunningStatus status = m_CurrentBehavior.Update(inputParam, outputParam);
//			if(status != k_BRS_Failure)
//			{
//				m_LastBehavior = i;
//				return status;
//			}
//		}
//		return k_BRS_Failure;;
//	}
//	virtual void OnTerminate(const BevNodeInputParam& inputParam)
//	{
//		m_LastBehavior = -1;
//		m_CurrentBehavior.Uninstall();
//	};
//
//private:
//	int			 m_LastBehavior;
//	Behavior m_CurrentBehavior;
//};