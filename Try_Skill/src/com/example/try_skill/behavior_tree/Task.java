package com.example.try_skill.behavior_tree;

import java.lang.reflect.InvocationTargetException;
import java.nio.channels.GatheringByteChannel;

//enum BevRunningStatus
//{
//	k_BRS_Invalid					= -1,
//	k_BRS_Executing					= 0,
//	k_BRS_Finish					= 1,
//	k_BRS_Failure					= 2
//};

public abstract class Task {
	protected Node m_pNode;
	
	public enum BevRunningStatus
	{
		k_BRS_Invalid					,
		k_BRS_Executing					,
		k_BRS_Finish					,
		k_BRS_Failure					
	};
	
	public Task(Node pNode) {
		m_pNode = pNode;
	}
	
	public abstract void OnInit(final Object inputParam);
	public abstract BevRunningStatus OnUpdate(final Object inputParam, Object outputParam);
	public abstract void OnTerminate(final Object inputParam);
	
	public abstract void stop();
	
	public Task newInstance(){
		Class<? extends Task> taskClass = this.getClass();
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
		
		return task;
	}
}

//class Task
//{
//public:
//	Task(Node* pNode)
//		:m_pNode(pNode)
//	{}
//	virtual ~Task(){};
//	virtual void			 OnInit(const BevNodeInputParam& inputParam)									= 0;
//	virtual BevRunningStatus OnUpdate(const BevNodeInputParam& inputParam, BevNodeOutputParam& outputParam)	= 0;
//	virtual void			 OnTerminate(const BevNodeInputParam& inputParam)								= 0;
//
//protected:
//	Node* m_pNode;
//};

