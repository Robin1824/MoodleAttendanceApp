package com.example.moodleattendanceapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FillAttendanceActivity extends Activity {
	
	int coursePosition,attendancePosition;
	
	String sessionId,actionBarTitle,actionBarSubTitle;
	
	ListView lvAttendanceData;
	
	SwipeRefreshLayout swipeRefreshLayout;
	
	AttendanceDataListAdapter attendanceDataListAdapter;
	
	ActionBar mActionBar;
	
	ArrayList<EnrolledStudents> students=new ArrayList<EnrolledStudents>();
	
	//ArrayList<AttendanceData> attendanceData=new ArrayList<AttendanceData>();
	
	HashMap<String, AttendanceData> attendanceDataMap=new HashMap<String, AttendanceData>();
	
	//ArrayList<PostAttendanceData> postAttendanceData=new ArrayList<PostAttendanceData>();
	
	HashMap<String, PostAttendanceData> postAttendanceDataMap=new HashMap<String, PostAttendanceData>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		actionBarSubTitle=getIntent().getStringExtra("sub_title");
		
		actionBarTitle=getIntent().getStringExtra("title");
		
		SetActionBar();
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_fill_attendance);
		
		coursePosition=getIntent().getIntExtra("course_position", 0);
		
		attendancePosition=getIntent().getIntExtra("attendance_position", 0);
		
		sessionId=getIntent().getStringExtra("session_id");		
		
		lvAttendanceData=(ListView) findViewById(R.id.lvAttendanceDataList);
		
		swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.attendanceSwipeContainer);
		

		students=GlobalJSONObjects.getInstance().getUser().getCourse().get(coursePosition).getEnrolledStudents();
		
		
		try
		{
			
			getAttendanceForSession(sessionId,this);			
			
			
		}
		catch (Exception e) {
			
			
			
		}
		
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh(){
				
				new AsyncTask<Void, Void, Void>(){
					
					String response="";
					
					@Override
					protected Void doInBackground(Void... params) {
						
						ServiceHandler sh=new ServiceHandler();
						response=sh.getAttendanceData(GlobalJSONObjects.getInstance().getUser().getToken(),sessionId);
						
						Log.i("MAA", " session id is: "+sessionId+"");
						Log.i("MAA", response);
						
						return null;
					}
					
					@Override
					protected void onPostExecute(Void result)
					{
						try
						{
							JSONObject obj=new JSONObject(response);
							JSONArray j=obj.getJSONArray("attendance_data");
							
							if(j.length()==0)
							{
								
							}
							else
							{
							
								for (int i = 0; i < j.length(); i++)
								{
									AttendanceData ad=new AttendanceData(j.getJSONObject(i));
									attendanceDataMap.put(ad.getId(), ad);
									postAttendanceDataMap.put(ad.getId(), new PostAttendanceData(ad.getId(), ad.getAcronym(), ad.getRemarks()));
								}
								
								attendanceDataListAdapter.notifyDataSetChanged();
							
							}
													
							
						}
						catch (JSONException e) {
							
							Log.i("MAA", "(PULL TO REFRESH) Inside Catch!!");
							
						}
						
						swipeRefreshLayout.setRefreshing(false);
						
					}
					
				}.execute();
				
			}
		});
		
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright, 
                android.R.color.holo_green_light, 
                android.R.color.holo_orange_light, 
                android.R.color.holo_red_light);
		
		
		
		
		
	}
	
	public void SetActionBar() {
		mActionBar = getActionBar();
		mActionBar.setDisplayShowTitleEnabled(true);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF6D00")));
		mActionBar.setTitle(actionBarTitle);
		mActionBar.setSubtitle(actionBarSubTitle);
		mActionBar.setDisplayHomeAsUpEnabled(true);
	}

	
	public void getAttendanceForSession(String sessionId,Context c)
	{
		final String sesId=sessionId;
		
		final Context context=c;
		
		new AsyncTask<Void, Void, Void>() {

			String response="";
			
			ProgressDialog progressDialog;
			
			@Override
			protected void onPreExecute() {
				progressDialog=null;
				progressDialog=ProgressDialog.show(context, "Please Wait...", "Preparing Attendance...",true);
				progressDialog.show();
			}
			
			@Override
			protected Void doInBackground(Void... params) {
				
				ServiceHandler sh=new ServiceHandler();
				response=sh.getAttendanceData(GlobalJSONObjects.getInstance().getUser().getToken(),sesId);
				
				Log.i("MAA", " session id is: "+sesId+"");
				Log.i("MAA", response);
				
				return null;
			}
			
			@Override
			protected void onPostExecute(Void result)
			{
				try
				{
					JSONObject obj=new JSONObject(response);
					JSONArray j=obj.getJSONArray("attendance_data");
					
					if(j.length()==0)
					{
						throw new JSONException("Empty JSON ARRAY");
					}
					else
					{
					
						for (int i = 0; i < j.length(); i++)
						{
							AttendanceData ad=new AttendanceData(j.getJSONObject(i));
							attendanceDataMap.put(ad.getId(), ad);
							postAttendanceDataMap.put(ad.getId(), new PostAttendanceData(ad.getId(), ad.getAcronym(), ad.getRemarks()));
						}
					
					}
					
					
					//success=true;
					
					attendanceDataListAdapter=new AttendanceDataListAdapter(context, students);
					
					lvAttendanceData.setAdapter(attendanceDataListAdapter);
					
					
					
				}
				catch (JSONException e) {
					
					Log.i("MAA", "Inside Catch!!");
					
					for(EnrolledStudents s:students)
					{
						Log.i("MAA", s.getFirst_name());
						postAttendanceDataMap.put(s.getUser_id(), new PostAttendanceData(s.getUser_id(), GlobalJSONObjects.getInstance().getUser().getCourse().get(coursePosition).getAttendance().get(attendancePosition).getStatuses().get(0).getAcronym(), ""));
					}
					
					attendanceDataListAdapter=new AttendanceDataListAdapter(context, students);
					
					lvAttendanceData.setAdapter(attendanceDataListAdapter);
					
				}
				
				progressDialog.dismiss();
				
			}
			
		}.execute();
	}
	
	
	class AttendanceDataListAdapter extends BaseAdapter
	{
		
		ArrayList<EnrolledStudents> enrolledStudents=new ArrayList<EnrolledStudents>();
	
		ArrayList<String> radioStatus=new ArrayList<String>();
		
		Context context;
		
		public AttendanceDataListAdapter(Context context,ArrayList<EnrolledStudents> enrolledStudents) {
			
			this.context=context;
			this.enrolledStudents=enrolledStudents;
			
			for(EnrolledStudents e:enrolledStudents)
			{
				//PostAttendanceData a=new PostAttendanceData(e.getUser_id(), "", "");
				//postAttendanceDataMap.put(a.getId(), a);
				Log.i("MAA", "*status: "+postAttendanceDataMap.get(e.getUser_id()).status);
				radioStatus.add(postAttendanceDataMap.get(e.getUser_id()).status);
			}
			
		}
		
		
		
		private class RadioBtn
		{
			
			ArrayList<RadioButton> radioArrList=new ArrayList<RadioButton>();
			
			public ArrayList<RadioButton> generateRadioButtons(ArrayList<Statuses> statuses)
			{
				
				for(Statuses s:statuses)
				{
					RadioButton r= new RadioButton(context);
					
					r.setText(s.getAcronym());
					r.setTag(s);
					RadioGroup.LayoutParams p = new RadioGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
					p.weight = 1;
					r.setLayoutParams(p);
					r.setTextColor(Color.WHITE);
					radioArrList.add(r);
					
				}
				
				return radioArrList;
				
			}
			
			public RadioButton getByAcronym(String a)
			{
				for(RadioButton r:radioArrList)
				{
					if(r.getText().equals(a))
					{
						return r;
					}
				}
				return null;
			}
			
		}
		
		
		
		private class ViewHolder
		{
			TextView tvStudentFullName;
			
			RadioGroup radioGroup;
			
			
			
			RadioBtn rb;
			
			public void setRadioBtn(ArrayList<Statuses> s)
			{
				rb=new RadioBtn();
				for(RadioButton r:rb.generateRadioButtons(s))
				{
					radioGroup.addView(r);
				}
			}			
			
		}
		
		

		@Override
		public int getCount() {
			
			return enrolledStudents.size();
		}

		@Override
		public Object getItem(int arg0) {
			
			return enrolledStudents.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return enrolledStudents.get(arg0).hashCode();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			final int pos=position;
			
			final ViewHolder holder;
			   //Log.v("ConvertView", String.valueOf(position));
			
			final View v=convertView;
			 
			   if (convertView == null)
			   {
				   LayoutInflater vi = (LayoutInflater)getSystemService(
				     Context.LAYOUT_INFLATER_SERVICE);
				   
				   convertView = vi.inflate(R.layout.single_attendance_data_row, null);
				 
				   holder = new ViewHolder();
				   holder.radioGroup=(RadioGroup) convertView.findViewById(R.id.rgAttendanceStatus);
				   holder.setRadioBtn(GlobalJSONObjects.getInstance().getUser().getCourse().get(coursePosition).getAttendance().get(attendancePosition).getStatuses());
				   
				   holder.tvStudentFullName = (TextView) convertView.findViewById(R.id.tvStudentFullName);

				   
				   
				   convertView.setTag(holder);
				   
			   
			   } 
			   else {
				   
			    holder = (ViewHolder) convertView.getTag();
			    
			   }
			   
			   

			   holder.tvStudentFullName.setText(enrolledStudents.get(position).getFull_name());
			   
			   holder.radioGroup.setOnCheckedChangeListener(null);
			   
			   String acronym=GlobalJSONObjects.getInstance().getUser().getCourse().get(coursePosition).getAttendance().get(attendancePosition).getStatuses().get(0).getAcronym();
			   
			   if(radioStatus.get(position).equals(acronym))
			   {
				   holder.rb.getByAcronym(radioStatus.get(position)).setChecked(true);
				   Log.i("MAA", "checking in if radioStatus at pos "+position +" is "+radioStatus.get(position));
				   
			   }
			   else
			   {
				   Log.i("MAA", "checking in else radioStatus at pos "+position +" is "+radioStatus.get(position));
				   holder.rb.getByAcronym(radioStatus.get(position)).setChecked(true);
			   }
			   
			   
			   if(radioStatus.get(position).equals("P") || radioStatus.get(position).equals("L"))
			   {
				   holder.radioGroup.setBackgroundColor(Color.parseColor("#8BC34A"));
				   holder.tvStudentFullName.setBackgroundColor(Color.parseColor("#8BC34A"));
			   }
			   else
			   {
				   if(radioStatus.get(position).equals("A"))
				   {
					   holder.radioGroup.setBackgroundColor(Color.parseColor("#F44336"));
					   holder.tvStudentFullName.setBackgroundColor(Color.parseColor("#F44336"));
				   }
				   else
				   {
					   holder.radioGroup.setBackgroundColor(Color.parseColor("#EF6C00"));
					   holder.tvStudentFullName.setBackgroundColor(Color.parseColor("#EF6C00"));
				   }
			   }
			   
				   
				   
			   holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
									   
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						
						RadioButton rb=(RadioButton) findViewById(checkedId);
						Statuses s=(Statuses) rb.getTag();
						
						radioStatus.set(pos, s.getAcronym());
						
						Log.i("MAA", "id: "+s.getId()+" description: "+s.getDescription());
						
						postAttendanceDataMap.get(students.get(pos).getUser_id()).setStatus(s.getId());						
						
						
						if(s.getAcronym().equals("P") || s.getAcronym().equals("L"))
						   {
							   holder.radioGroup.setBackgroundColor(Color.parseColor("#8BC34A"));
							   holder.tvStudentFullName.setBackgroundColor(Color.parseColor("#8BC34A"));
						   }
						   else
						   {
							   if(s.getAcronym().equals("A"))
							   {
								   holder.radioGroup.setBackgroundColor(Color.parseColor("#F44336"));
								   holder.tvStudentFullName.setBackgroundColor(Color.parseColor("#F44336"));
							   }
							   else
							   {
								   holder.radioGroup.setBackgroundColor(Color.parseColor("#EF6C00"));
								   holder.tvStudentFullName.setBackgroundColor(Color.parseColor("#EF6C00"));
							   }
						   }
						
						
						Log.i("MAA", "(present) id: "+postAttendanceDataMap.get(students.get(pos).getUser_id()).getId()+" status:"+postAttendanceDataMap.get(students.get(pos).getUser_id()).getStatus());
						Log.i("MAA", "radio status: "+radioStatus.get(pos));
						//Log.i("MAA", "{\"d\":"+AttendanceData.toJSON(attendanceData)+"}");
						
					}
				});
			 
			   return convertView;
			 
			  }
		
	}
	
}
