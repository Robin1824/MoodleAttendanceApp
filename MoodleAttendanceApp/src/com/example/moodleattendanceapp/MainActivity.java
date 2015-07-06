package com.example.moodleattendanceapp;

import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ActionBar mActionBar;
	EditText etUserName, etPassword;
	Button btnUserLogin;
	String uname = "", pwd = "", response = "";
	//Fragment fragment_UserCourse = null;
	// flag for Internet connection status
	Boolean isInternetPresent = false, flagResponse = false;
	//FrameLayout LayoutUserLoginScreen;
	//LinearLayout LayoutCourseListScreen;
	User u;
	// Connection detector class
	ConnectionDetector cd;

	//ListView CourseList;
	//ImageView imgProPic;
	//TextView tvUserFullName, tvRoleName;
	SharedPreferences mSharedPreferences;
	Editor mEditor;

	 // Progress Dialog Object
    ProgressDialog prgDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_main);
		SetActionBar();
		
		//Log.i("bef call frag", "Login frag");

		//Log.i("aftr call", "Login frag");

		mSharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);

		etUserName = (EditText) findViewById(R.id.etUsername); 
		  etPassword =
		  (EditText) findViewById(R.id.etPassword); 
		
		if ((mSharedPreferences.contains("Username") && mSharedPreferences
				.contains("Password"))) 
		{
			Log.i("data in pref", "ok");
			
			etUserName.setText(mSharedPreferences.getString("Username", ""));
			etPassword.setText(mSharedPreferences.getString("Password", ""));

			//Intent i=new Intent(getApplicationContext(),UserCourseActivity.class);
			//startActivity(i);
		}
		/*else
		{
			Log.i("data in pref", "ok");
			
			Bundle b=new Bundle();
			UserCourseFragment sf = new UserCourseFragment();
			b.putString("user_id","0");
			b.putString("user_fullname", "1");
			b.putString("user_role_name", "2");
			b.putString("user_propic_url", "http://rutvik.ddns.net//pluginfile.php//119//user//icon//f1");
			sf.setArguments(b);
			
			
			getFragmentManager().beginTransaction()
					.replace(R.id.frame_layout, sf).commit();
		}*/

		  // Instantiate Progress Dialog object
	        prgDialog = new ProgressDialog(this);
	     // Set Progress Dialog Text
	        prgDialog.setMessage("Please wait...");
	        // Set Cancelable as False
	        prgDialog.setCancelable(false);
	        
		  btnUserLogin = (Button) findViewById(R.id.btnLogin);
		  
		  /*LayoutUserLoginScreen=(FrameLayout)findViewById(R.id.
		  LayoutUserLoginScreen);
		  LayoutCourseListScreen=(LinearLayout)findViewById
		  (R.id.LayoutCourseListScreen);*/
		  
		 /* imgProPic=(ImageView)findViewById(R.id.imgUserProPic);
		  CourseList=(ListView)findViewById(R.id.lvCourseList);
		  tvUserFullName=(TextView)findViewById(R.id.tvUserFullName);
		  tvRoleName=(TextView)findViewById(R.id.tvUserRole);*/
		  
		  // creating connection detector class instance 
		  cd = new ConnectionDetector(getApplicationContext());
		  
		  btnUserLogin.setOnClickListener(new OnClickListener() {
		  
		 @Override public void onClick(View v) 
		 { // TODO Auto-generated method stub 
		  UserLogin(v); 
		  } 
		 });
	}

	public void SetActionBar() {
		mActionBar = getActionBar();
		mActionBar.setDisplayShowTitleEnabled(true);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FFB917")));
		mActionBar.setTitle("Login");
	}

	public void UserLogin(View v) {

		if (!isEmpty(etUserName) && !isEmpty(etPassword)) {
			// make HTTP request

			// get Internet status
			isInternetPresent = cd.isConnectingToInternet();

			// check for Internet status
			if (isInternetPresent) {
				// Internet Connection is Present
				// make HTTP requests
				// openAlert("Internet Connection","You have internet connection");
				uname = etUserName.getText().toString();
				pwd = etPassword.getText().toString();

				AsyncCallWS task = new AsyncCallWS();
				task.execute("");

			} else {
				// Internet connection is not present
				// Ask user to connect to Internet
				openAlert("No Internet Connection",
						"You don't have internet connection.");
			}

			/*
			 * uname = et_Uname.getText().toString().toUpperCase(); pwd =
			 * et_pwd.getText().toString();
			 */

			/*
			 * mEditor.putString("URL", url); mEditor.putString("Username",
			 * uname); mEditor.putString("Password", pwd);
			 * mEditor.putString("OrgName", orgname); mEditor.putString("OrgID",
			 * orgid); mEditor.commit();
			 */
			Log.i("orgid save", "ok");

		} else {
			if (isEmpty(etUserName)) {
				etUserName.setHint("Enter Username");
				openAlert("Message", "Please Enter Username");
			} else if (isEmpty(etPassword)) {
				etPassword.setHint("Enter Password");
				openAlert("Message", "Please Enter Password");
			}
		}
	}

	private boolean isEmpty(EditText etText) {
		return etText.getText().toString().trim().length() == 0;
	}

	private void openAlert(String mTitle, String msg) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				MainActivity.this);

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
			prgDialog.hide();
			//setProgressBarIndeterminateVisibility(Boolean.FALSE);
			if (flagResponse == true) {
				//LayoutUserLoginScreen.setVisibility(View.GONE);
				//LayoutCourseListScreen.setVisibility(View.VISIBLE);

				//tvUserFullName.setText(u.getFull_name());
				//tvRoleName.setText(u.getRole_short_name());
				
				mEditor = mSharedPreferences.edit();
				
				mEditor.putString("Username", uname);
				mEditor.putString("Password", pwd);
				
				mEditor.putString("user_id", u.getId());
				mEditor.putString("user_token", u.getToken());
				mEditor.putString("user_fullname", u.getFull_name());
				mEditor.putString("user_propic_url", u.getProfile_pic_url());
				mEditor.putString("user_role_name", u.getRole_short_name());
				mEditor.commit();
				
				Bundle b=new Bundle();
				b.putParcelableArrayList("courses", u.getCourse());
				b.putString("user_propic_url", u.getProfile_pic_url());
				Intent i=new Intent(getApplicationContext(),UserCourseActivity.class);
				//b.putParcelable("user", u);
				i.putExtras(b);
				
				Log.i("pass","ok"+u.getFull_name());
				startActivity(i);

			} else if (flagResponse == false) {
				openAlert("Login Incorrect",
						"Please enter correct Username and Password");
			}
		}

		@Override
		protected void onPreExecute() {
			prgDialog.show();
			//setProgressBarIndeterminateVisibility(Boolean.TRUE);
		}
	}

	@SuppressWarnings("deprecation")
	public void fetchJSON() {
		Log.i("In fetchJson", "ok");

		Log.i("In C==null", "ok");
		try {
			// Log.i("URL", url);
			String FinalURL = "http://rutvik.ddns.net/webservice.php?method=login&user_name="
					+ uname + "&password=" + pwd;
			// Log.i("Final URL", FinalURL);
			HttpPost post = new HttpPost(FinalURL);
			post.setHeader("Accept", "application/json; charset=UTF-8");

			/*
			 * List<NameValuePair> nameValuePairs = new
			 * ArrayList<NameValuePair>( 2);
			 * //user_name=131040119001&password=Reset@123 nameValuePairs
			 * .add(new BasicNameValuePair("user_name", uname));
			 * nameValuePairs.add(new BasicNameValuePair("password", pwd));
			 * 
			 * Log.i("pass", nameValuePairs.toString()); post.setEntity(new
			 * UrlEncodedFormEntity(nameValuePairs));
			 */
			HttpClient client = new DefaultHttpClient();

			HttpResponse resp = (HttpResponse) client.execute(post);

			HttpEntity entity = resp.getEntity();

			response = EntityUtils.toString(entity);
			Log.v("response info : ", "Hello My Res : " + response);
			if (response.indexOf("error") == -1) {
				// JSONObject UserLogin=new JSONObject(response);
				JSONObject ja = new JSONObject(response);
				Log.i("after json", "ok");

				u = new User(ja.getJSONObject("user"));

				Log.i("role short nm", "Hello: " + u.getRole_short_name());
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

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
}
