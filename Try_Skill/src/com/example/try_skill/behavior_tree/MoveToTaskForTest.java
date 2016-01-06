package com.example.try_skill.behavior_tree;

import android.graphics.Point;

public class MoveToTaskForTest extends Task{

	public MoveToTaskForTest(Node pNode) {
		super(pNode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void OnInit(Object inputParam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BevRunningStatus OnUpdate(Object inputParam,
			Object outputParam) {
		// TODO Auto-generated method stub
		PointInputForTest input = (PointInputForTest) inputParam;
		PointOutputForTest output = (PointOutputForTest) outputParam;
		
		int sourceX = input.getSourcePoint().x;
		int sourceY = input.getSourcePoint().y;
		int offsetX = input.getOffsetPosition().x;
		int offsetY = input.getOffsetPosition().y;
		int targetX = input.getTargetPosition().x;
		int targetY = input.getTargetPosition().y;
		
		if(targetX - sourceX <= offsetX || targetX - sourceX <= offsetY){
			output.getNextPosition().x = targetX;
			output.getNextPosition().y = targetY;
			
			input.sourcePoint.x = output.getNextPosition().x;
			input.sourcePoint.y = output.getNextPosition().y;
			
			System.out.println("NextPosition x="+ output.getNextPosition().x + " y=" + output.getNextPosition().y);
			return BevRunningStatus.k_BRS_Finish;
		}else{
			output.getNextPosition().x = sourceX + offsetX;
			output.getNextPosition().y = sourceY + offsetY;
			
			input.sourcePoint.x = output.getNextPosition().x;
			input.sourcePoint.y = output.getNextPosition().y;
			System.out.println("NextPosition x="+ output.getNextPosition().x + " y=" + output.getNextPosition().y);
		}
//		output.set(input.x, input.y);
		return BevRunningStatus.k_BRS_Executing;
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

//virtual BevRunningStatus OnUpdate(const BevNodeInputParam& inputParam, BevNodeOutputParam& outputParam)
//{
//	const BevInputData& inputData = inputParam.GetRealDataType<BevInputData>();
//	BevOutputData& outputData = outputParam.GetRealDataType<BevOutputData>();
//	f32 timeStep = inputData.m_TimeStep;
//	Vec2 targetPoint2D = inputData.m_TargetPosition2D;
//	Vec3 curPosition3D = inputData.m_Owner->GetPosition();
//	Vec2 curPosition2D(curPosition3D.x, curPosition3D.y);
//	Vec2 dir = targetPoint2D - curPosition2D;
//	if(dir.LengthSq() < 2.f)
//	{
//		outputData.m_NextPosition = Vec3(targetPoint2D.x, targetPoint2D.y, 0);
//		return k_BRS_Finish;
//	}
//	else
//	{
//		Vec2 curFacing = inputData.m_CurrentFacing;
//		dir.Nomalize();
//		f32 dotValue = dir.DotProduct(curFacing);
//		f32 angle = Math::ACos(Math::Clamp<f32>(dotValue, -1.f, 1.f));
//		if(angle >= 0.1f)
//		{
//			return k_BRS_Failure;
//		}
//		Vec2 nextPos2D = curPosition2D + dir * timeStep * 100.f;
//		outputData.m_NextPosition = Vec3(nextPos2D.x, nextPos2D.y, 0);
//	}
//	return k_BRS_Executing;
//}