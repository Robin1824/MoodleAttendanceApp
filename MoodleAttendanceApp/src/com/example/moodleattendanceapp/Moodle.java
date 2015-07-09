package com.example.moodleattendanceapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


public class Moodle extends AsyncTask<String, Void, String>
{
	
	ProgressDialog mDialog;
	Context context;
	
	Moodle(Context context)
	{
		this.context = context;
	}
	
	String call;
	String domainName="http://rutvik.ddns.net/webservice.php?";

	public void getToken(String uid, String pass)
	{
		call=domainName+"method=login&user_name="
				+ uid + "&password=" + pass;
		Log.i("url",""+call);
	}
	
	public void getSiteInfo(String token)
	{
		call=domainName+"/webservice/rest/server.php?wstoken="+token+"&wsfunction=moodle_webservice_get_siteinfo";
	}
	
	public void getUserInfo(String token,String id)
	{
		call=domainName+"/webservice/rest/server.php?wstoken="+token+"&wsfunction=moodle_user_get_users_by_id&userids[0]="+id;
	}
	
	public void getUserCourses(String token,String id)
	{
		call=domainName+"/webservice/rest/server.php?wstoken="+token+"&wsfunction=moodle_enrol_get_users_courses&userid="+id;
	}
	
	public void getFriends(String token,String courseId)
	{
		call=domainName+"/webservice/rest/server.php?wstoken="+token+"&wsfunction=moodle_enrol_get_enrolled_users&courseid="+courseId;
	}
	
	public void sendInstantMsg(String token,String toUserId,String msg)
	{
		call=domainName+"/webservice/rest/server.php?wstoken="+token+"&wsfunction=moodle_message_send_instantmessages&messages[0][touserid]="+toUserId+"&messages[0][text]="+msg+"&messages[0][textformat]=1";
	}
	
	public void getCourseContents(String token,String courseId)
	{
		call=domainName+"/webservice/rest/server.php?wstoken="+token+"&wsfunction=core_course_get_contents&courseid="+courseId;
	}
	
	@Override
    protected void onPreExecute() {
       super.onPreExecute();

     //   mDialog = new ProgressDialog(context);
       // mDialog.setMessage("Please wait...");
        //mDialog.show();
    }
	
	
	 @Override
	    protected void onPostExecute(String result) {
	        super.onPostExecute(result);
	      //  mDialog.dismiss();
	       
	    }
	

	@Override
	protected String doInBackground(String... params)
	{
		
		switch(Integer.parseInt(params[0]))
		{
			case 0:	getToken(params[1], params[2]);
					break;
					
			case 1:	getSiteInfo(params[1]);
					break;
					
			case 2:	getUserInfo(params[1],params[2]);
					break;
					
			case 3:	getUserCourses(params[1], params[2]);
					break;
					
			case 4:	getFriends(params[1], params[2]);
					break;
					
			case 5:	params[3]=params[3].replaceAll(" ", "%20");
					params[3]=params[3].replaceAll("\n", "%0A");
					sendInstantMsg(params[1], params[2], params[3]);
					break;
					
			case 6:	getCourseContents(params[1], params[2]);
					break;
					
			default:call="";
					break;
		}
		
		BufferedReader in =null;
		String data =null;
		
		try
		{
			
			HttpClient client=new DefaultHttpClient();			
			
			URI website=new URI(call);
			
			HttpGet request=new HttpGet();
			
			request.setURI(website);
			
			HttpResponse response=client.execute(request);
			
			in =new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			StringBuffer sb=new StringBuffer("");
			
			String l="";
			
			while((l=in.readLine())!=null)
			{
				sb.append(l);
			}
			
			in.close();
			
			data=sb.toString();
			
			return data;
			
		}
		catch (Exception e)
		{
			data="error";
			return data;
		}
		finally
		{
			if(in!=null)
			{
				try
				{
					in.close();
					return data;
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}			
		}
	}
	
}
