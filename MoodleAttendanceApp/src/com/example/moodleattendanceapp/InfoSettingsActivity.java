package com.example.moodleattendanceapp;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;


public class InfoSettingsActivity extends Activity {
	
	ActionBar mActionBar;
	
	EditText etMoodleHost;
	
	int count=1;
	
	SharedPreferences mSharedPreferences;
	
	TextView tv;
	
	@Override
	public void onBackPressed() {
		if(!TextUtils.isEmpty(etMoodleHost.getText()))
		{
			GlobalSettings.getInstance().setHost(mSharedPreferences, etMoodleHost.getText().toString());
		}
		
		super.onBackPressed();
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home)
		{
			if(!TextUtils.isEmpty(etMoodleHost.getText()))
			{
				GlobalSettings.getInstance().setHost(mSharedPreferences, etMoodleHost.getText().toString());
			}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SetActionBar();
		
		mSharedPreferences = getSharedPreferences("moodle_attendance_app_shared_pref", Context.MODE_PRIVATE);
		
		super.onCreate(savedInstanceState);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		setContentView(R.layout.activity_info_settings);
		
		
		
		etMoodleHost=(EditText) findViewById(R.id.etMoodleHost);
		
		tv=(TextView) findViewById(R.id.tvText);
		
		if(mSharedPreferences.contains("host"))
		{
			etMoodleHost.setText(mSharedPreferences.getString("host", "moodle"));
			etMoodleHost.setEnabled(false);
			tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					count=count+1;
					
					if(count>2)
					{
						etMoodleHost.setEnabled(true);
					}
					
				}
			});
		}
		
		
	}
	
	public void SetActionBar() {
		mActionBar = getActionBar();
		mActionBar.setDisplayShowTitleEnabled(true);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF6D00")));
		mActionBar.setTitle("Moodle Host and Info");
		
		mActionBar.setDisplayHomeAsUpEnabled(true);
	}


}
