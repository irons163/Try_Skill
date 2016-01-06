package com.example.try_skill.behavior_tree;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import android.test.AndroidTestCase;

import com.example.try_skill.behavior_tree.Task.BevRunningStatus;
import com.example.try_skill.sprite_skill.SkillSprite;

public class BehaviorTreeTest extends AndroidTestCase{
	
//	public void testMovementActionInfo() throws Exception{
//		
//		Behavior	m_BevTreeRoot = null;
//		PointInputForTest	m_BevTreeInputData;
//		PointOutputForTest	m_BevTreeOutputdata;
//		float	m_TimeToFindNewTargetPos;
//		
//		PointInputForTest input = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
//		PointOutputForTest output = new PointOutputForTest(new Point());
//		
//		Node sBevTree = null;
//		m_BevTreeRoot = new Behavior();
//		BevRunningStatus isFinish = BevRunningStatus.k_BRS_Invalid;
//		
//		for(int i = 0; i < 10; i++){
//			
//			if(sBevTree==null)
//			{
//				CompositeNode pSe = new CompositeNode_Sequence();
//					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNode());
//					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNode());
//				CompositeNode pPa = new CompositeNode_Parallel();
//					pPa.AddChild(pSe);
//					pPa.AddChild(BehaviorTreeFactory.createMoveToTerminateNode());
//				CompositeNode pRo = new CompositeNode_Selector();
//					pRo.AddChild(pPa);
//					pRo.AddChild(BehaviorTreeFactory.createMoveToTerminateNode());
//						
//				sBevTree = pRo;
//				
//				m_BevTreeRoot.Install(sBevTree);
//			}
//			
//			isFinish = m_BevTreeRoot.Update(input, output);
//			
//			switch (i) {
//			case 0:
//				assertEquals(true, output.nextPosition.x == 12 && output.nextPosition.y == 12);
//				break;
//			case 1:
//				assertEquals(true, output.nextPosition.x == 14 && output.nextPosition.y == 14);
//				break;
//			case 2:
//				assertEquals(true, output.nextPosition.x == 16 && output.nextPosition.y == 16);
//				break;
//			case 3:
//				assertEquals(true, output.nextPosition.x == 18 && output.nextPosition.y == 18);
//				break;
//			case 4:
//				assertEquals(true, output.nextPosition.x == 20 && output.nextPosition.y == 20);
//				break;
//			default:
//				assertEquals(true, false);
//				break;
//			}
//			
//			if(isFinish==BevRunningStatus.k_BRS_Finish)
//				break;
//			
//		}
//		
//		BehaviorTreeTest.this.getContext();
//	}
	
	public void testActionInfo() throws Exception{
		Behavior	m_BevTreeRoot = null;
		PointInputForTest	m_BevTreeInputData;
		PointOutputForTest	m_BevTreeOutputdata;
		float	m_TimeToFindNewTargetPos;
		
		PointInputForTest input = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
		PointOutputForTest output = new PointOutputForTest(new Point());
		
		Node sBevTree = null;
		m_BevTreeRoot = new Behavior();
		BevRunningStatus isFinish = BevRunningStatus.k_BRS_Invalid;
		
		for(int i = 0; i < 10; i++){
	
			if(sBevTree==null)
			{
				CompositeNode pSe = new CompositeNode_Sequence();
					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				CompositeNode pPa = new CompositeNode_Parallel();
					pPa.AddChild(pSe);
					pPa.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				CompositeNode pRo = new CompositeNode_Selector();
					pRo.AddChild(pPa);
					pRo.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
						
				sBevTree = pRo;
				
				m_BevTreeRoot.Install(sBevTree);
			}
			
			isFinish = m_BevTreeRoot.Update(input, output);
			
			switch (i) {
			case 0:
				assertEquals(true, output.nextPosition.x == 12 && output.nextPosition.y == 12);
				break;
			case 1:
				assertEquals(true, output.nextPosition.x == 14 && output.nextPosition.y == 14);
				break;
			case 2:
				assertEquals(true, output.nextPosition.x == 16 && output.nextPosition.y == 16);
				break;
			case 3:
				assertEquals(true, output.nextPosition.x == 18 && output.nextPosition.y == 18);
				break;
			case 4:
				assertEquals(true, output.nextPosition.x == 20 && output.nextPosition.y == 20);
				break;
			default:
				assertEquals(true, false);
				break;
			}
			
			if(isFinish==BevRunningStatus.k_BRS_Finish)
				break;
			
		}
		
		
		Behavior bevTreeRoot = null;
		Behavior bevTreeRoot2 = null;
		PointInputForTest input1 = null;
		PointOutputForTest output1 = null;
		PointInputForTest input2 = null;
		PointOutputForTest output2 = null;
		
		sBevTree = null;
		
		for(int i = 0; i < 10; i++){
			
			if(sBevTree==null)
			{
				input1 = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
				output1 = new PointOutputForTest(new Point());
				
				CompositeNode pSe = new CompositeNode_Sequence();
					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				CompositeNode pPa = new CompositeNode_Parallel();
					pPa.AddChild(pSe);
					pPa.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				CompositeNode pRo = new CompositeNode_Selector();
					pRo.AddChild(pPa);
					pRo.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
						
				sBevTree = pRo;
			}
			
			if(bevTreeRoot==null){
				bevTreeRoot = new Behavior();
				bevTreeRoot.Install(sBevTree);
			}
			
			isFinish = bevTreeRoot.Update(input1, output1);
			
			switch (i) {
			case 0:
				assertEquals(true, output1.nextPosition.x == 12 && output1.nextPosition.y == 12);
				break;
			case 1:
				assertEquals(true, output1.nextPosition.x == 14 && output1.nextPosition.y == 14);
				break;
			case 2:
				assertEquals(true, output1.nextPosition.x == 16 && output1.nextPosition.y == 16);
				break;
			case 3:
				assertEquals(true, output1.nextPosition.x == 18 && output1.nextPosition.y == 18);
				break;
			case 4:
				assertEquals(true, output1.nextPosition.x == 20 && output1.nextPosition.y == 20);
				break;
			default:
				assertEquals(true, false);
				break;
			}
			
			
			if(bevTreeRoot2==null){
				input2 = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
				output2 = new PointOutputForTest(new Point());
				
				bevTreeRoot2 = new Behavior();
				bevTreeRoot2.Install(sBevTree);
			}
			
			isFinish = bevTreeRoot2.Update(input2, output2);
			
			switch (i) {
			case 0:
				assertEquals(true, output2.nextPosition.x == 12 && output2.nextPosition.y == 12);
				break;
			case 1:
				assertEquals(true, output2.nextPosition.x == 14 && output2.nextPosition.y == 14);
				break;
			case 2:
				assertEquals(true, output2.nextPosition.x == 16 && output2.nextPosition.y == 16);
				break;
			case 3:
				assertEquals(true, output2.nextPosition.x == 18 && output2.nextPosition.y == 18);
				break;
			case 4:
				assertEquals(true, output2.nextPosition.x == 20 && output2.nextPosition.y == 20);
				break;
			default:
				assertEquals(true, false);
				break;
			}
			
			if(isFinish==BevRunningStatus.k_BRS_Finish)
				break;
			
		}
		
		
	
		sBevTree = null;
		if(sBevTree==null)
		{
			input = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
			output = new PointOutputForTest(new Point());
			
			CompositeNode pSe = new CompositeNode_Sequence();
				pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
			CompositeNode pPa = new CompositeNode_Parallel();
				pPa.AddChild(pSe);
				pPa.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
			CompositeNode pRo = new CompositeNode_Selector();
				pRo.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				pRo.AddChild(pPa);	
					
			sBevTree = pRo;
		}
		m_BevTreeRoot.Install(sBevTree);
		
		m_BevTreeRoot.Update(input, output);
		
		assertEquals(true, output.nextPosition.x == 11 && output.nextPosition.y == 11);
		
		input = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
		output = new PointOutputForTest(new Point());
	
		sBevTree = null;
		if(sBevTree==null)
		{
			CompositeNode pSe = new CompositeNode_Sequence();
				pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
			CompositeNode pRo = new CompositeNode_Selector();
				pRo.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				pRo.AddChild(pSe);		
					
			sBevTree = pRo;
		}
		m_BevTreeRoot.Install(sBevTree);
		
		m_BevTreeRoot.Update(input, output);
		
		assertEquals(true, output.nextPosition.x == 11 && output.nextPosition.y == 11);
		
		
		input = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
		output = new PointOutputForTest(new Point());
	
		sBevTree = null;
		if(sBevTree==null)
		{
			CompositeNode pSe = new CompositeNode_Sequence();
				pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
			CompositeNode pRo = new CompositeNode_Selector();
				pRo.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				pRo.AddChild(pSe);		
			CompositeNode pPa = new CompositeNode_Parallel();
				pPa.AddChild(pRo);
				pPa.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
					
			sBevTree = pPa;
		}
		m_BevTreeRoot.Install(sBevTree);
		
		m_BevTreeRoot.Update(input, output);
		
		assertEquals(true, output.nextPosition.x == 12 && output.nextPosition.y == 12);
		
		
		input = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
		output = new PointOutputForTest(new Point());
	
		sBevTree = null;
		if(sBevTree==null)
		{
			
			CompositeNode pRo = new CompositeNode_Selector();
				pRo.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				pRo.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());		
			CompositeNode pSe = new CompositeNode_Sequence();
				pSe.AddChild(pRo);
				pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
			CompositeNode pPa = new CompositeNode_Parallel();
				pPa.AddChild(pSe);
				pPa.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
					
			sBevTree = pPa;
		}
		m_BevTreeRoot.Install(sBevTree);
		
		m_BevTreeRoot.Update(input, output);
		
		assertEquals(true, output.nextPosition.x == 12 && output.nextPosition.y == 12);
		
		
		////////////////////////////////////////////////////////////
		////
		///////////////////////////////////////////////////////////
		
		
		
		
		//Tick
		//preparing data
//				m_BevTreeInputData.m_Owner = this;
//				m_BevTreeInputData.m_TimeStep = _fDeltaTime;
//				m_BevTreeInputData.m_CurrentFacing = m_Facing;
//				m_BevTreeOutputdata.m_NextFacing = m_Facing;

//		m_BevTreeOutputdata = 
		
//				m_TimeToFindNewTargetPos -= _fDeltaTime;
//				if(m_TimeToFindNewTargetPos <= 0)
//				{
//					m_BevTreeInputData.m_TargetPosition2D = Vec2(rand() % width, rand() % (height - 100));
//					m_TimeToFindNewTargetPos = rand() % 5 + 2;
//				}
				
//				m_BevTreeOutputdata.m_BodySize = m_BodySize;
//				m_BevTreeOutputdata.m_BodyColor = m_BodyColor;
//				m_BevTreeOutputdata = new Point(10, 10);

				//tick behavior tree
				

				//update object
//				m_BodySize = m_BevTreeOutputdata.m_BodySize;
//				m_BodyColor = m_BevTreeOutputdata.m_BodyColor;
//				SetPosition(m_BevTreeOutputdata.m_NextPosition);
//				m_Facing = m_BevTreeOutputdata.m_NextFacing;
				
//				Point newPoint = m_BevTreeOutputdata;
		
//		LayerManager.setNoSceneLayer();
//		BitmapUtil.initBitmap(mContext);
//		//BitmapUtil.initBitmapForTest();
//
//		List<MovementActionInfo> correctInfoList = new ArrayList<MovementActionInfo>();
//		correctInfoList.add(new MovementActionInfo(1000, 200, 40f, 0f));
//		correctInfoList.add(new MovementActionInfo(1000, 200, -40f, 0f));
//
//		EnemyFactory enemyFactory = new EnemyFactory();
//		Enemy enemy = enemyFactory.createSpecialEnemy(RedEnemy.class, RLMovementActionFactory.class, new int[]{0, 0});
//
//		MovementAction action = enemy.getAction();
//
//		List<MovementActionInfo> currentInfoList = new ArrayList<MovementActionInfo>();
//
//		for(MovementActionInfo movementActionInfo : action.getStartMovementInfoList()){
//			currentInfoList.add(movementActionInfo); 
//		}
//
//		assertEquals(true, correctInfoList.equals(currentInfoList));
//
//
//
//		correctInfoList.clear();
//		correctInfoList.add(new MovementActionInfo(1000, 200, 40f, 0f));
//		correctInfoList.add(new MovementActionInfo(1000, 200, -40f, 0f));
//		correctInfoList.add(new MovementActionInfo(1000, 200, 40f, 0f));
//		correctInfoList.add(new MovementActionInfo(1000, 200, -40f, 0f));
//
//		enemy = enemyFactory.createSpecialEnemy4(RedEnemy.class, RLMovementActionFactory.class, new int[]{0, 0}, MovementActionDecoratorFactory.createCopyMovementDecorator());
//		action = enemy.getAction();
//		currentInfoList.clear();
//
//		for(MovementActionInfo movementActionInfo : action.getStartMovementInfoList()){
//			currentInfoList.add(movementActionInfo); 
//		}
//
//		assertEquals(true, correctInfoList.equals(currentInfoList));
	}

	
	public void testTaskDecoratorInfo() throws Exception{
		Behavior bevTreeRoot = null;
		Node sBevTree = null;
//			bevTreeRoot = new Behavior();
		BevRunningStatus isFinish = BevRunningStatus.k_BRS_Invalid;
		
		PointInputForTest input = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
		PointOutputForTest output = new PointOutputForTest(new Point());
		
		if(sBevTree==null)
			{
//					input1 = new PointInputForTest(new Point(10, 10), new Point(1, 1), new Point(20, 20));
//					output1 = new PointOutputForTest(new Point());
				
				CompositeNode pSe = new CompositeNode_Sequence();
					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				CompositeNode pPa = new CompositeNode_Parallel();
					pPa.AddChild(pSe);
					pPa.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				
				CompositeNode detectEnemy = new CompositeNode_Selector();
					detectEnemy.AddChild(createIsEnemyTerminateNode());
					detectEnemy.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());					
				CompositeNode detectAllies = new CompositeNode_Selector();
					List<Class<? extends TaskDecorator>> decorators = new ArrayList<Class<? extends TaskDecorator>>();
					decorators.add(TaskResultInverseDecorator.class);
					detectAllies.AddChild(BehaviorTreeFactory.createTerminateNode(CheckIsEnemyTask.class, decorators));
//					detectAllies.AddChild(BehaviorTreeFactory.createMoveToTerminateNodeForTest());
				CompositeNode root = new CompositeNode_Sequence();
					root.AddChild(detectAllies);
//					root.AddChild(detectEnemy);
					
				sBevTree = root;
			}
			
			if(bevTreeRoot==null){
				bevTreeRoot = new Behavior();
				bevTreeRoot.Install(sBevTree);
			}
			
			isFinish = bevTreeRoot.Update(input, output);

			assertEquals(BevRunningStatus.k_BRS_Failure, isFinish);
	}


	private TerminateNode createIsEnemyTerminateNode(){
		return BehaviorTreeFactory.createTerminateNode(CheckIsEnemyTask.class);
	}
	
}

class CheckIsEnemyTask extends Task{

	public CheckIsEnemyTask(Node pNode) {
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
		if(inputParam!=null)
			return BevRunningStatus.k_BRS_Finish;
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

class PointInputForTest {
	Point sourcePoint , offsetPosition, targetPosition;

	public PointInputForTest(Point sourcePoint, Point offsetPosition,
			Point targetPosition) {
		this.sourcePoint = sourcePoint;
		this.offsetPosition = offsetPosition;
		this.targetPosition = targetPosition;
	}

	public Point getSourcePoint() {
		return sourcePoint;
	}

	public Point getOffsetPosition() {
		return offsetPosition;
	}

	public Point getTargetPosition() {
		return targetPosition;
	}
	
}

class PointOutputForTest {
	Point nextPosition;
	
	public PointOutputForTest(Point nextPosition) {
		// TODO Auto-generated constructor stub
		this.nextPosition = nextPosition;
	}
	
	public Point getNextPosition() {
		return nextPosition;
	}
}
