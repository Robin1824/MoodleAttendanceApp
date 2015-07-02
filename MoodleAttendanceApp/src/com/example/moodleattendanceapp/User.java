package com.example.moodleattendanceapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;

public class User extends JSONObject
{
    private Course[] course;

    private String id;

    private String profile_pic_url;

    private String first_name;

    private String user_name;

    private String role_id;

    private String role_short_name;

    private String last_name;

    private String full_name;
    
    public User(JSONObject obj) throws JSONException
    {
    	try
    	{
    		id=obj.getString("id");
    		profile_pic_url=obj.getString("profile_pic_url");
    		first_name=obj.getString("first_name");
    		user_name=obj.getString("user_name");
    		role_id=obj.getString("role_id");
    		role_short_name=obj.getString("role_short_name");
    		last_name=obj.getString("last_name");
    		full_name=obj.getString("full_name");
    	}
    	catch(JSONException e)
    	{
    		throw e;
    	}
    }

    public Course[] getCourse ()
    {
        return course;
    }

    public void setCourse (Course[] course)
    {
        this.course = course;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getProfile_pic_url ()
    {
        return profile_pic_url;
    }

    public void setProfile_pic_url (String profile_pic_url)
    {
        this.profile_pic_url = profile_pic_url;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getUser_name ()
    {
        return user_name;
    }

    public void setUser_name (String user_name)
    {
        this.user_name = user_name;
    }

    public String getRole_id ()
    {
        return role_id;
    }

    public void setRole_id (String role_id)
    {
        this.role_id = role_id;
    }

    public String getRole_short_name ()
    {
        return role_short_name;
    }

    public void setRole_short_name (String role_short_name)
    {
        this.role_short_name = role_short_name;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
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
        return "ClassPojo [course = "+course+", id = "+id+", profile_pic_url = "+profile_pic_url+", first_name = "+first_name+", user_name = "+user_name+", role_id = "+role_id+", role_short_name = "+role_short_name+", last_name = "+last_name+", full_name = "+full_name+"]";
    }
}
