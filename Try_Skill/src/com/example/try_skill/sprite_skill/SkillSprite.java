package com.example.try_skill.sprite_skill;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;

import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.utils.DetectAreaPoint;
import com.example.try_gameengine.utils.SpriteDetectAreaHandler;
import com.example.try_skill.ASkill;
import com.example.try_skill.ArmySprite;
import com.example.try_skill.ISkill;
import com.example.try_skill.behavior_tree.Behavior;
import com.example.try_skill.behavior_tree.BehaviorTreeFactory;
//import com.example.try_skill.behavior_tree.BevRunningStatus;
import com.example.try_skill.behavior_tree.CompositeNode;
import com.example.try_skill.behavior_tree.CompositeNode_Parallel;
import com.example.try_skill.behavior_tree.CompositeNode_Selector;
import com.example.try_skill.behavior_tree.CompositeNode_Sequence;
import com.example.try_skill.behavior_tree.Node;
import com.example.try_skill.behavior_tree.ReturnTureTaskForTest;
import com.example.try_skill.behavior_tree.SelectorTask;
import com.example.try_skill.behavior_tree.TaskDecorator;
import com.example.try_skill.behavior_tree.TaskResultInverseDecorator;
//import com.example.try_skill.behavior_tree.PointInputForTest;
//import com.example.try_skill.behavior_tree.PointOutputForTest;
import com.example.try_skill.behavior_tree.Task;
import com.example.try_skill.behavior_tree.TerminateNode;
import com.example.try_skill.behavior_tree.Task.BevRunningStatus;
import com.example.try_skill.effect.IEffect;
import com.example.try_skill.sprite_skill.BTree.Input;

public class SkillSprite extends Sprite{
	private List<SkillWithArea> skillWithAreas = new ArrayList<SkillWithArea>();
	private IEffect effect;

	public SkillSprite(Bitmap bitmap, float x, float y, int scale,
			boolean autoAdd) {
		super(bitmap, x, y, scale, autoAdd);
		// TODO Auto-generated constructor stub
	}

	public void addSkill(SkillWithArea skillWithArea){
		if(skillWithArea!=null)
			skillWithAreas.add(skillWithArea); 
	}
	
	public void initSkillBehaviorTree(){
//		Behavior bevTreeRoot = null;
//		Behavior bevTreeRoot2 = null;
//		Sprite input1 = null;
//		PointOutputForTest output1 = null;
//		PointInputForTest input2 = null;
//		PointOutputForTest output2 = null;
//		
//		sBevTree = null;
//		
//		for(int i = 0; i < 10; i++){
//			
//			if(sBevTree==null)
//			{
//				input1 = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
//				output1 = new PointOutputForTest(new Point());
//				
//				CompositeNode pSe = new CompositeNode_Sequence();
//					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
//					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
//				CompositeNode pPa = new CompositeNode_Parallel();
//					pPa.AddChild(pSe);
//					pPa.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
//				CompositeNode pRo = new CompositeNode_Selector();
//					pRo.AddChild(pPa);
//					pRo.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
//						
//				sBevTree = pRo;
//			}
//			
//			if(bevTreeRoot==null){
//				bevTreeRoot = new Behavior();
//				bevTreeRoot.Install(sBevTree);
//			}
//			
//			isFinish = bevTreeRoot.Update(input1, output1);
//			
//			switch (i) {
//			case 0:
//				assertEquals(true, output1.nextPosition.x == 12 && output1.nextPosition.y == 12);
//				break;
//			case 1:
//				assertEquals(true, output1.nextPosition.x == 14 && output1.nextPosition.y == 14);
//				break;
//			case 2:
//				assertEquals(true, output1.nextPosition.x == 16 && output1.nextPosition.y == 16);
//				break;
//			case 3:
//				assertEquals(true, output1.nextPosition.x == 18 && output1.nextPosition.y == 18);
//				break;
//			case 4:
//				assertEquals(true, output1.nextPosition.x == 20 && output1.nextPosition.y == 20);
//				break;
//			default:
//				assertEquals(true, false);
//				break;
//			}
//			
//			
//			if(bevTreeRoot2==null){
//				input2 = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
//				output2 = new PointOutputForTest(new Point());
//				
//				bevTreeRoot2 = new Behavior();
//				bevTreeRoot2.Install(sBevTree);
//			}
//			
//			isFinish = bevTreeRoot2.Update(input2, output2);
	}
		List<Sprite> detectedSpritesInSkillArea;
	public void detectSpritesInSkillArea(List<Sprite> sprites){
		SpriteDetectAreaHandler spriteDetectAreaHandler = getSpriteDetectAreaHandler();
		detectedSpritesInSkillArea =null;
		for(Sprite sprite : sprites){	
			if(spriteDetectAreaHandler.detectByPoint(new PointF(sprite.getCenterX(), sprite.getCenterY()))){
				if(detectedSpritesInSkillArea==null)
					detectedSpritesInSkillArea = new ArrayList<Sprite>();
				detectedSpritesInSkillArea.add(sprite);
			}
		}
		
//		if(detectedSpritesInSkillArea!=null){
//			skillWithArea.checkDetectArea(detectedSpritesInSkillArea);
//		}
	}

	public List<SkillWithArea> getSkillWithAreas() {
		return skillWithAreas;
	}

	public IEffect getEffect() {
		return effect;
	}

	public void setSkillWithAreas(List<SkillWithArea> skillWithAreas) {
		this.skillWithAreas = skillWithAreas;
	}

	public void setEffect(IEffect effect) {
		this.effect = effect;
	}

	public List<Sprite> getDetectedSpritesInSkillArea() {
		return detectedSpritesInSkillArea;
	}

	public void setDetectedSpritesInSkillArea(
			List<Sprite> detectedSpritesInSkillArea) {
		this.detectedSpritesInSkillArea = detectedSpritesInSkillArea;
	}
	
	
}

class BTree {
	
	public class Input{
		public SkillSprite skillSprite;
		public ArmySprite sprite;
		public int skillWithAreaIndex;
	}
	
	public BTree(Object inputParam){
//		List<Sprite> spriteSortByDistance = new ArrayList<Sprite>();
//		List<Double> distanceSortBySprite = new ArrayList<Double>();
		Map<Double, Sprite> spriteSortByDistance = new TreeMap<Double, Sprite>();
		SkillSprite skillSprite = (SkillSprite)inputParam;
//		for(SkillWithArea skillWithArea : skillSprite.getSkillWithAreas()){
			for(Sprite sprite : skillSprite.getDetectedSpritesInSkillArea()){
				PointF position = new PointF(sprite.getCenterX(), sprite.getCenterY());
				double distance = Math.sqrt(Math.pow(skillSprite.getCenterX() - position.x, 2) + Math.pow(skillSprite.getCenterY() - position.y, 2));
				spriteSortByDistance.put(distance, sprite);		
			}
			
			for(Entry<Double, Sprite> sprite : spriteSortByDistance.entrySet()){
				update(skillSprite, sprite, null);
			}
//		}	
			class SkillSelectorTask extends SelectorTask {
				private final SkillWithArea skillWithArea;
				
				public SkillSelectorTask(Node pNode, SkillWithArea skillWithArea) {
					super(pNode);
					this.skillWithArea = skillWithArea;
					// TODO Auto-generated constructor stub
				}

				@Override
				public void OnInit(Object inputParam) {
					// TODO Auto-generated method stub
					CompositeNode compositeNode = this.GetCompositeNode();
					Input input = (Input) inputParam;
					for(SkillWithArea skillWithArea : input.skillSprite.getSkillWithAreas()){
						compositeNode.AddChild(BehaviorTreeFactory.createTerminateNode(SkillTask.class));
					}
				}

				@Override
				public BevRunningStatus OnUpdate(Object inputParam, Object outputParam) {
					// TODO Auto-generated method stub
					Input input = (Input) inputParam;
					PointF position = new PointF(input.sprite.getCenterX(), input.sprite.getCenterY());
					if(skillWithArea.checkDetectArea(new DetectAreaPoint(position))){
						return BevRunningStatus.k_BRS_Finish;
					}
						
					return BevRunningStatus.k_BRS_Failure;
				}

				@Override
				public void OnTerminate(Object inputParam) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void stop() {
					// TODO Auto-generated method stub
					
				}
				
			}
	}
	
	public void update(SkillSprite skillSprite, Object inputO, Object output){
		Behavior bevTreeRoot = null;
		Node sBevTree = null;
//		bevTreeRoot = new Behavior();
		BevRunningStatus isFinish = BevRunningStatus.k_BRS_Invalid;
		
		Input input = new Input();
		input.skillSprite = skillSprite;
		input.sprite = (ArmySprite) inputO;
		
		if(sBevTree==null)
			{
//				input1 = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
//				output1 = new PointOutputForTest(new Point());
				
				CompositeNode pSe = new CompositeNode_Sequence();
					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				CompositeNode pPa = new CompositeNode_Parallel();
					pPa.AddChild(pSe);
					pPa.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				
					
//					CompositeNode skillPrioritySelector = new CompositeNode_Selector();
//					skillPrioritySelector.AddChild(node);
				CompositeNode detectEnemy = new CompositeNode_Selector();
					detectEnemy.AddChild(createIsEnemyTerminateNode());
					detectEnemy.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());	
				CompositeNode detectAllies = new CompositeNode_Selector();
					List<Class<? extends TaskDecorator>> decorators = new ArrayList<Class<? extends TaskDecorator>>();
					decorators.add(TaskResultInverseDecorator.class);
					detectAllies.AddChild(BehaviorTreeFactory.createTerminateNode(CheckIsEnemyTask.class, decorators));
					detectAllies.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				CompositeNode root = new CompositeNode_Sequence();
					root.AddChild(detectEnemy);
					root.AddChild(detectAllies);
					
					
					root = new CompositeNode_Selector();
					CompositeNode skillDoTask = new CompositeNode_Sequence();
					for(SkillWithArea skillWithArea : skillSprite.getSkillWithAreas()){
						if(skillWithArea.isTargetToEnemy())
							skillDoTask.AddChild(detectEnemy);
						else
							skillDoTask.AddChild(detectAllies);
						skillDoTask.AddChild(BehaviorTreeFactory.createTerminateNode(SkillTask.class));
					}
					root.AddChild(detectEnemy);
					root.AddChild(detectAllies);
//				sBevTree = pRo;
			}
			
			if(bevTreeRoot==null){
				bevTreeRoot = new Behavior();
				bevTreeRoot.Install(sBevTree);
			}
			
			isFinish = bevTreeRoot.Update(input, output);
	}
	
	private TerminateNode createIsEnemyTerminateNode(){
		return BehaviorTreeFactory.createTerminateNode(CheckIsEnemyTask.class);
	}
}



class CloserTask extends Task {

	public CloserTask(Node pNode) {
		super(pNode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OnInit(Object inputParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BevRunningStatus OnUpdate(Object inputParam, Object outputParam) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void OnTerminate(Object inputParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
}

class SkillNode extends TerminateNode{
	private SkillWithArea skillWithArea;
	
	public SkillNode(Class<? extends Task> taskClass) {
		super(taskClass);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Task CreateTask() {
		// TODO Auto-generated method stub
		super.CreateTask();
		return super.CreateTask();
	}
}

class SkillCheckNode extends CompositeNode_Selector{
	
	@Override
	public Task CreateTask() {
		// TODO Auto-generated method stub
		return super.CreateTask();
	}
	
	@Override
	public void DestroyTask(Task pTask) {
		// TODO Auto-generated method stub
		super.DestroyTask(pTask);
	}
}

class SkillTask extends Task {
	private SkillWithArea skillWithArea;
	
	public SkillTask(Node pNode, SkillWithArea skillWithArea) {
		super(pNode);
		this.skillWithArea = skillWithArea;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OnInit(Object inputParam) {
		// TODO Auto-generated method stub
//		this.m_pNode.
		Input input = (Input) inputParam;
		skillWithArea = input.skillSprite.getSkillWithAreas().get(input.skillWithAreaIndex);
		input.skillWithAreaIndex++; 
	}

	@Override
	public BevRunningStatus OnUpdate(Object inputParam, Object outputParam) {
		// TODO Auto-generated method stub
		Input input = (Input) inputParam;
		PointF position = new PointF(input.sprite.getCenterX(), input.sprite.getCenterY());
		if(skillWithArea.checkDetectArea(new DetectAreaPoint(position))){
			skillWithArea.getSkill();
			return BevRunningStatus.k_BRS_Finish;
		}
			
		return BevRunningStatus.k_BRS_Failure;
	}

	@Override
	public void OnTerminate(Object inputParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
}

class DoSkillTask extends Task {

	public DoSkillTask(Node pNode) {
		super(pNode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OnInit(Object inputParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BevRunningStatus OnUpdate(Object inputParam, Object outputParam) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void OnTerminate(Object inputParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
}

//class SkillPrioritySelectorTask extends SelectorTask{
//	
//}