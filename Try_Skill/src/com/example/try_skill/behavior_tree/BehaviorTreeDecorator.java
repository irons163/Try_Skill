package com.example.try_skill.behavior_tree;

public abstract class BehaviorTreeDecorator extends Node{
	private Node node = null;
	
	public BehaviorTreeDecorator(Node node) {
		// TODO Auto-generated constructor stub
		this.node = node;
	}
	
	
}
