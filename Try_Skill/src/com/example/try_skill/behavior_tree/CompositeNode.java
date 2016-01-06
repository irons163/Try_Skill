package com.example.try_skill.behavior_tree;

import java.util.ArrayList;
import java.util.List;

public class CompositeNode extends Node{
	protected List<Node> m_Children = new ArrayList<Node>();
	
	@Override
	public Task CreateTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void DestroyTask(Task pTask) {
		// TODO Auto-generated method stub
		
	}

	public Node GetChild(int idx){
		if(idx < 0 || idx >= (int)m_Children.size())
			return null;
		return m_Children.get(idx);
	}
	
	public void AddChild(Node node)
	{
		node.SetParent(this);
		m_Children.add(node);
	}
	
	public int GetChildCount(){
		return m_Children.size();
	}
}

//typedef std::vector<Node*> Nodes;
//
//class CompositeNode : public Node
//{
//public:
//	virtual ~CompositeNode()
//	{
//		for(int i = 0; i < (int)m_Children.size(); ++i)
//		{
//			D_SafeDelete(m_Children[i]);
//		}
//	}
//	Node* GetChild(int idx){
//		if(idx < 0 || idx >= (int)m_Children.size())
//			return NULL;
//		return m_Children[idx];
//	}
//	void AddChild(Node* node)
//	{
//		node->SetParent(this);
//		m_Children.push_back(node);
//	}
//	int GetChildCount() const{
//		return m_Children.size();
//	}
//protected:
//	Nodes m_Children;
//};
