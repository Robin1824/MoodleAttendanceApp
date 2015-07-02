package com.example.moodleattendanceapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class UserLoginFragment extends Fragment{

	EditText etUserName, etPassword;
	Button btnUserLogin;
	String uname = "", pwd = "", response="";
	
	// flag for Internet connection status
    Boolean isInternetPresent = false,flagResponse=false;
    User u;
    // Connection detector class
    ConnectionDetector cd;
    
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.user_login_fragment,
				container, false);
		
		
		
		return rootView;
	}
	
}
