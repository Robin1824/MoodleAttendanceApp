package com.rutvik.moodleattendanceapp;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

	
	@Override
	public void onCreate() {
		
		Log.i("MAA", "application createdddd!");
		
		super.onCreate();
	}
	
	@Override
	public void onLowMemory() {
		
		Log.e("maa", "MEMORY LOWWWWWWWWWW!");
		
		super.onLowMemory();
	}
	
	
	
	
}
