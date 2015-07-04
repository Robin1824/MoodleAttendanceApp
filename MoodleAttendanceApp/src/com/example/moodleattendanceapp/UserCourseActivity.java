package com.example.moodleattendanceapp;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class UserCourseActivity extends Activity {

	ListView CourseList;
	ImageView imgProPic;
	TextView tvUserFullName,tvRoleName;
	String response = "";
	String user_id,user_fullname,user_role_name,user_propic_url,token;
	Boolean isInternetPresent = false, flagResponse = false;
	Course c[];
	
	// Connection detector class
	ConnectionDetector cd;
	
	ActionBar mActionBar;
	
	SharedPreferences mSharedPreferences;
	Editor mEditor;
	
	private String[] mPlanetTitles;
	ArrayList<String> list = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_user_course);
		SetActionBar();
		imgProPic=(ImageView)findViewById(R.id.imgUserProPic);
		CourseList=(ListView)findViewById(R.id.lvCourseList);
		tvUserFullName=(TextView)findViewById(R.id.tvUserFullName);
		tvRoleName=(TextView)findViewById(R.id.tvUserRole);
		
		 int loader = R.drawable.ic_launcher;
		 Log.i("get pass ok","ok");
			//User u=getIntent().getParcelableExtra("user");
		 	Bundle b=getIntent().getExtras();
		 	
			ArrayList<Course> courses;
			courses=b.getParcelableArrayList("courses");
			user_propic_url=b.getString("user_propic_url");
			Log.i("get pass aftr","ok");			
			Log.i("moodle",""+ courses.size());
			Log.i("get pass print","ok");
			
			
			mSharedPreferences = getSharedPreferences(
					"Login", Context.MODE_PRIVATE);

			if ((mSharedPreferences.contains("Username") && mSharedPreferences
					.contains("Password"))) 
			{
				user_id=mSharedPreferences.getString("user_id", "");
				token=mSharedPreferences.getString("user_token", "");
				user_fullname=mSharedPreferences.getString("user_fullname", "");
				user_role_name=mSharedPreferences.getString("user_role_name", "");
				//user_propic_url=mSharedPreferences.getString("user_propic_url", "");
			}
		 
		// user_id=getArguments().getString("user_id");
			
			//user_fullname=getArguments().getString("user_fullname");
			//user_role_name = getArguments().getString("user_role_name");
			
			tvUserFullName.setText(user_fullname);
			
			if(user_role_name.equalsIgnoreCase("editingteacher"))
			{
				tvRoleName.setText("Faculty");
			}
			else if(user_role_name.equalsIgnoreCase("student"))
			{
				tvRoleName.setText("Student");
			}
			//user_propic_url = getArguments().getString("user_propic_url");
			
			 //ImageLoader class instance
	        ImageLoader imgLoader = new ImageLoader(getApplicationContext());
	        
	        // whenever you want to load an image from url
	        // call DisplayImage function
	        // url - image url to load
	        // loader - loader image, will be displayed before getting image
	        // image - ImageView 
	       imgLoader.DisplayImage(user_propic_url, loader, imgProPic);
			
	       
	     //  AsyncCallWS task = new AsyncCallWS();
	      // task.execute("");
	       
	       // mPlanetTitles = getResources().getStringArray(R.array.CountryArray);
	        
			Log.i("call course","course frag");
			
		//	CourseList.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
			//		android.R.layout.simple_list_item_1, mPlanetTitles));
		 
			for(int i=0;i<courses.size();i++)
			{
				list.add(courses.get(i).getFull_name());				
			}
			CourseList.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
							android.R.layout.simple_list_item_1, list));
			
	}
	public void SetActionBar() {
		mActionBar = getActionBar();
		mActionBar.setDisplayShowTitleEnabled(true);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FFB917")));
		mActionBar.setTitle("Enrolled Course");
	}
	
	private class AsyncCallWS extends AsyncTask<String, Void, Void> {
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			Log.i("in back", "ok");
			fetchJSON();
			Log.i("in back last", "ok");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			setProgressBarIndeterminateVisibility(Boolean.FALSE);
			if (flagResponse == true) {
				//LayoutUserLoginScreen.setVisibility(View.GONE);
				//LayoutCourseListScreen.setVisibility(View.VISIBLE);

				//tvUserFullName.setText(u.getFull_name());
				//tvRoleName.setText(u.getRole_short_name());
				
				
				
			} else if (flagResponse == false) {
				openAlert("Login Incorrect",
						"Please enter correct Username and Password");
			}
		}

		@Override
		protected void onPreExecute() {
			setProgressBarIndeterminateVisibility(Boolean.TRUE);
		}
	}

	@SuppressWarnings("deprecation")
	public void fetchJSON() {
		Log.i("In fetchJson", "ok");

		Log.i("In C==null", "ok");
		try {
			// Log.i("URL", url);
			String FinalURL = "http://rutvik.ddns.net/webservice.php?method=get_courses&token="
					+ token + "&user_id=" + user_id;
			
			//Log.i("Final URL", FinalURL);
			
			HttpPost post = new HttpPost(FinalURL);
			post.setHeader("Accept", "application/json; charset=UTF-8");

			HttpClient client = new DefaultHttpClient();

			HttpResponse resp = (HttpResponse) client.execute(post);

			HttpEntity entity = resp.getEntity();

			response = EntityUtils.toString(entity);
			Log.v("response info : ", "Hello My Res : " + response);
			
			if (response.indexOf("error") == -1) {
				JSONObject c=new JSONObject(response);

				
				//Log.i("fnm",""+c.getId());
				flagResponse = true;
				Log.v("end fetchJson()", "Hello run ok");
			} else {

				flagResponse = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * if (flagResponse == 1) { Log.i("Call readjson", "ok");
		 * readAndParseJSON(response); }
		 */

	}
	
	private void openAlert(String mTitle, String msg) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				UserCourseActivity.this);

		alertDialogBuilder.setTitle(mTitle);
		alertDialogBuilder.setMessage(msg);
		// set positive button: Yes message
		alertDialogBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		// show alert
		alertDialog.show();
	}
	
	
}
