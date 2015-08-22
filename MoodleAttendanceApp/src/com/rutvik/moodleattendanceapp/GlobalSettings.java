package com.rutvik.moodleattendanceapp;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class GlobalSettings {
	
	private static GlobalSettings instance=null;
	
	private String host;
	
	private SharedPreferences mSharedPreferences;
	
	public synchronized static GlobalSettings getInstance()
	{
		
		if(instance==null)
		{
			instance=new GlobalSettings();
		}
		
		return instance;
		
	}
	
	
	public String getStatusColor(SharedPreferences sp,String acronym)
	{
		return sp.getString(acronym, "#EF6C00");
	}


	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host=host;
	}

	public void setHost(SharedPreferences sp,String host) {
		
		
			Editor editor=sp.edit();
			editor.putString("host", host);
			editor.commit();
			
			
		
	}


	public SharedPreferences getmSharedPreferences() {
		return mSharedPreferences;
	}


	public void setmSharedPreferences(SharedPreferences mSharedPreferences) {
		this.mSharedPreferences = mSharedPreferences;
	}
	
	
	

}
