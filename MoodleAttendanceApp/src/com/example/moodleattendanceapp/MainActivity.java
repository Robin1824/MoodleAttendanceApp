package com.example.moodleattendanceapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	ActionBar mActionBar;
	EditText etUserName, etPassword;
	Button btnUserLogin;
	
	// flag for Internet connection status
    Boolean isInternetPresent = false;
     
    // Connection detector class
    ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SetActionBar();
		etUserName = (EditText) findViewById(R.id.etUsername);
		etPassword = (EditText) findViewById(R.id.etPassword);
		btnUserLogin = (Button) findViewById(R.id.btnLogin);
		
		// creating connection detector class instance
        cd = new ConnectionDetector(getApplicationContext());

		btnUserLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserLogin(v);
			}
		});
	}

	public void SetActionBar() {
		mActionBar = getActionBar();
		// mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(true);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FFB917")));
		mActionBar.setTitle("Login");
	}

	public void UserLogin(View v) {

		if (!isEmpty(etUserName) && !isEmpty(etPassword)) {
			//make HTTP request
			
			// get Internet status
            isInternetPresent = cd.isConnectingToInternet();

            // check for Internet status
            if (isInternetPresent) {
                // Internet Connection is Present
                // make HTTP requests
                openAlert("Internet Connection","You have internet connection");
            } else {
                // Internet connection is not present
                // Ask user to connect to Internet
                openAlert("No Internet Connection","You don't have internet connection.");
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
				openAlert("Message","Please Enter Username");
			}
			else if (isEmpty(etPassword)) {
				etPassword.setHint("Enter Password");
				openAlert("Message","Please Enter Password");
			}
		}
	}

	private boolean isEmpty(EditText etText) {
		return etText.getText().toString().trim().length() == 0;
	}

	private void openAlert(String mTitle,String msg) {
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

	@Override
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
	}
}
