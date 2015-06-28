package com.example.moodleattendanceapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Course extends JSONObject {
	
	private String id;

    private Attendance[] attendance;

    private String short_name;

    private EnrolledStudents[] enrolledStudents;

    private String full_name;
    
    public Course(JSONObject obj) throws JSONException
    {
    	try
    	{
    		id=obj.getString("id");
    		short_name=obj.getString("short_name");
    		full_name=obj.getString("full_name");
    		JSONArray attendanceArr=obj.getJSONArray("attendance");
    		for(int i=0;i<attendanceArr.length();i++)
    		{
    			Attendance a=new Attendance(attendanceArr.getJSONObject(i));
    			attendance[i]=a;
    		}
    	}
    	catch (JSONException e) {
			throw e;
		}
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Attendance[] getAttendance ()
    {
        return attendance;
    }

    public void setAttendance (Attendance[] attendance)
    {
        this.attendance = attendance;
    }

    public String getShort_name ()
    {
        return short_name;
    }

    public void setShort_name (String short_name)
    {
        this.short_name = short_name;
    }

    public EnrolledStudents[] getEnrolledStudents ()
    {
        return enrolledStudents;
    }

    public void setEnrolled_students (EnrolledStudents[] enrolledStudents)
    {
        this.enrolledStudents = enrolledStudents;
    }

    public String getFull_name ()
    {
        return full_name;
    }

    public void setFull_name (String full_name)
    {
        this.full_name = full_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", attendance = "+attendance+", short_name = "+short_name+", enrolled_students = "+enrolledStudents+", full_name = "+full_name+"]";
    }

}
