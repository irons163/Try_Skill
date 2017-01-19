package com.example.try_skill;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.try_gameengine.framework.BitmapUtil;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.scene.SceneManager;
import com.example.try_gameengine.stage.Stage;

public class MainActivity extends Stage {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		CommonUtil.screenHeight = dm.heightPixels;
		CommonUtil.screenWidth = dm.widthPixels;

//		AudioUtil.init(this);

		CommonUtil.statusBarHeight = CommonUtil.getStatusBarHeight(this);
		CommonUtil.screenHeight -= CommonUtil.statusBarHeight;

		BitmapUtil.initBitmap(this);

		initStage();
	}

	@Override
	public SceneManager initSceneManager() {
		// TODO Auto-generated method stub
//		LayerManager.getInstance().setLayerBySenceIndex(0);
		SceneManager sceneManager = new SceneManager();
		sceneManager.addScene(new MyScence(this, "game"));
		sceneManager.startScene(0);
		return sceneManager;
	}
}
