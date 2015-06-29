package com.example.moodleattendanceapp;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class UserCourseFragment extends Fragment{
	
	ListView CourseList;
	ImageView imgProPic;
	TextView tvUserFullName,tvRoleName;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.user_course_fragment,
				container, false);
		imgProPic=(ImageView)rootView.findViewById(R.id.imgUserProPic);
		CourseList=(ListView)rootView.findViewById(R.id.lvCourseList);
		tvUserFullName=(TextView)rootView.findViewById(R.id.tvUserFullName);
		tvRoleName=(TextView)rootView.findViewById(R.id.tvUserRole);
		
		return rootView;
	}
}
