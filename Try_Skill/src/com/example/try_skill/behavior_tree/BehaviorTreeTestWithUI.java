package com.example.try_skill.behavior_tree;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

import com.example.try_gameengine.action.MAction;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.framework.BitmapUtil;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_skill.behavior_tree.Task.BevRunningStatus;

public class BehaviorTreeTestWithUI extends ActivityUnitTestCase<MyActivity>{
	Intent mStartIntent;
	
//	public BehaviorTreeTestWithUI(Class<Activity> activityClass) {
//		super(activityClass);
//		// TODO Auto-generated constructor stub
//	}
	
//	@Override
//    protected void setUp() throws Exception {
//        super.setUp();
//
//        // default value for target context, as a default
//      mActivityContext = getInstrumentation().getTargetContext();
//    }

//	public BehaviorTreeTestWithUI(Class activityClass) {
//		super(activityClass);
//		// TODO Auto-generated constructor stub
//	}

	public BehaviorTreeTestWithUI(){
		super(MyActivity.class);
	}
	
	public BehaviorTreeTestWithUI(Class<MyActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	MyActivity activity;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		mStartIntent = new Intent(Intent.ACTION_MAIN);
		startActivity(mStartIntent, null, null);
//		activity = new Activity();
		activity = getActivity();
//		this.setActivity(testActivity);
	}
	
	public void testMovementActionInfo() throws Exception{
//		Activity activity = getActivity();
		
//		SurfaceView view = new SurfaceView(activity);
//		getActivity().setContentView(view);
//        view.getHolder().addCallback(this);
//		getActivity().setContentView(new SurfaceView(getActivity()));
		

		

		
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
				// TODO Auto-generated method stub
				
				Behavior	m_BevTreeRoot = null;
				PointInputForTest	m_BevTreeInputData;
				PointOutputForTest	m_BevTreeOutputdata;
				float	m_TimeToFindNewTargetPos;
				
				BitmapUtil.initBitmap(activity);
				BitmapUtil.initBitmapForTest();
				
				final Sprite sprite = new Sprite(10, 10, false);
				sprite.setBitmapAndAutoChangeWH(BitmapUtil.redPoint);
				
				Sprite sprite2 = new Sprite(20, 20, false);
				sprite2.setBitmapAndAutoChangeWH(BitmapUtil.bluePoint);
				
				MovementAction move = MAction.moveTo(20, 20, 1, 10);
				MovementAction move2 = MAction.moveTo(30, 30, 1, 10);
				
				move.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
					
					@Override
					public void onTick(float dx, float dy) {
						// TODO Auto-generated method stub
						sprite.move(dx, dy);
					}
				});
				
				sprite.runMovementAction(move);
				sprite2.runMovementAction(move2);
				
				MovementAction input = move;
				MovementAction output = null;
				
				Node sBevTree = null;
				m_BevTreeRoot = new Behavior();
				BevRunningStatus isFinish = BevRunningStatus.k_BRS_Invalid;	

		for(int i = 0; i < 10; i++){
			
			if(sBevTree==null)
			{
				CompositeNode pSe = new CompositeNode_Sequence();
					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNode());
					pSe.AddChild(BehaviorTreeFactory.createMoveToTerminateNode());
				CompositeNode pPa = new CompositeNode_Parallel();
					pPa.AddChild(pSe);
					pPa.AddChild(BehaviorTreeFactory.createMoveToTerminateNode());
				CompositeNode pRo = new CompositeNode_Selector();
					pRo.AddChild(pPa);
					pRo.AddChild(BehaviorTreeFactory.createMoveToTerminateNode());
						
				sBevTree = pRo;
				
				m_BevTreeRoot.Install(sBevTree);
			}
			
			isFinish = m_BevTreeRoot.Update(input, output);
			
//			Canvas canvas =null;
//			do{
//				canvas = activity.getSurfaceView().getHolder().lockCanvas();
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}while(canvas==null);
//
//			Paint paint = null;
//			
//			sprite.drawSelf(canvas, paint);
//			
//			activity.getSurfaceView().getHolder().unlockCanvasAndPost(canvas);
			
			sprite.frameTrig();
			sprite2.frameTrig();
			
			switch (i) {
			case 0:
				assertEquals(true, sprite.getX() == 12 && sprite.getY() == 12);
				break;
			case 1:
				assertEquals(true, sprite.getX() == 14 && sprite.getY() == 14);
				break;
			case 2:
				assertEquals(true, sprite.getX() == 16 && sprite.getY() == 16);
				break;
			case 3:
				assertEquals(true, sprite.getX() == 18 && sprite.getY() == 18);
				break;
			case 4:
				assertEquals(true, sprite.getX() == 20 && sprite.getY() == 20);
				break;
			default:
				assertEquals(true, false);
				break;
			}
			
			if(isFinish==BevRunningStatus.k_BRS_Finish)
				break;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//			}
//		});
//		
//		thread.start();
		
//		BehaviorTreeTest.this.getContext();
	}

}
