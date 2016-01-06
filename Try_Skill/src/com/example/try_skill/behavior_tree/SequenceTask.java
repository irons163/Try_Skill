package com.example.try_skill.behavior_tree;

public class SequenceTask extends Task{
	private int m_iCurrentChild;
	private Behavior m_CurrentBehavior;
	private boolean isRun;
	
	public SequenceTask(Node pNode) {
		super(pNode);
		// TODO Auto-generated constructor stub
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
			return BevRunningStatus.k_BRS_Failure;;

		if(!m_CurrentBehavior.HasInstalled())
		{
			m_iCurrentChild = 0;
			m_CurrentBehavior.Install(comNode.GetChild(m_iCurrentChild)); 
		}
//		while(true)
		isRun = true;
		while(isRun)
		{
			BevRunningStatus status = m_CurrentBehavior.Update(inputParam, outputParam);
			if(status == BevRunningStatus.k_BRS_Finish)
			{
				m_iCurrentChild++;
				if(m_iCurrentChild >= comNode.GetChildCount())
				{
					return status;
				}
				m_CurrentBehavior.Install(comNode.GetChild(m_iCurrentChild)); 
			}
			else
			{
				return status;
			}
		}
		return BevRunningStatus.k_BRS_Invalid;
	}

	@Override
	public void OnTerminate(Object inputParam) {
		// TODO Auto-generated method stub
		m_CurrentBehavior.Uninstall();
	}
	
	public CompositeNode GetCompositeNode(){
		return (CompositeNode) m_pNode;
	}
	
	public void stop(){
		isRun = false;
	}
}

//class SequenceTask : public Task
//{
//public:
//	SequenceTask(Node* pNode)
//		: Task(pNode)
//		, m_iCurrentChild(0)
//	{}
//	CompositeNode& GetCompositeNode(){
//		return *dynamic_cast<CompositeNode*>(m_pNode);
//	}
//	virtual void OnInit(const BevNodeInputParam& inputParam)
//	{
//
//	}
//	virtual BevRunningStatus OnUpdate(const BevNodeInputParam& inputParam, BevNodeOutputParam& outputParam)
//	{
//		CompositeNode& comNode = GetCompositeNode();
//		if(comNode.GetChildCount() == 0)
//			return k_BRS_Failure;;
//
//		if(!m_CurrentBehavior.HasInstalled())
//		{
//			m_iCurrentChild = 0;
//			m_CurrentBehavior.Install(*(comNode.GetChild(m_iCurrentChild))); 
//		}
//		while(true)
//		{
//			BevRunningStatus status = m_CurrentBehavior.Update(inputParam, outputParam);
//			if(status == k_BRS_Finish)
//			{
//				m_iCurrentChild++;
//				if(m_iCurrentChild >= comNode.GetChildCount())
//				{
//					return status;
//				}
//				m_CurrentBehavior.Install(*(comNode.GetChild(m_iCurrentChild))); 
//			}
//			else
//			{
//				return status;
//			}
//		}
//		return k_BRS_Invalid;
//	}
//	virtual void OnTerminate(const BevNodeInputParam& inputParam)
//	{
//		m_CurrentBehavior.Uninstall();
//	};
//
//private:
//	int m_iCurrentChild;
//	Behavior m_CurrentBehavior;
//};