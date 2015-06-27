package com.example.moodleattendanceapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Attendance extends JSONObject
{
    private String id;

    private Sessions[] sessions;

    private String name;

    private String grade;

    private Statuses[] statuses;
    
    public Attendance(JSONObject obj) throws JSONException
    {
    	try
    	{
    		id=obj.getString("id");
    		name=obj.getString("name");
    		grade=obj.getString("grade");
    		
    		JSONArray sessionsArr=obj.getJSONArray("sessions");
    		JSONArray statusesArr=obj.getJSONArray("statuses");
    		
    		for(int i=0;i<sessionsArr.length();i++)
    		{
    			Sessions s=new Sessions(sessionsArr.getJSONObject(i));
    			sessions[i]=s;
    		}
    		
    		for(int i=0;i<statusesArr.length();i++)
    		{
    			Statuses s=new Statuses(statusesArr.getJSONObject(i));
    			statuses[i]=s;
    		}
    		
    	}
    	catch(JSONException e)
    	{
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

    public Sessions[] getSessions ()
    {
        return sessions;
    }

    public void setSessions (Sessions[] sessions)
    {
        this.sessions = sessions;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getGrade ()
    {
        return grade;
    }

    public void setGrade (String grade)
    {
        this.grade = grade;
    }

    public Statuses[] getStatuses ()
    {
        return statuses;
    }

    public void setStatuses (Statuses[] statuses)
    {
        this.statuses = statuses;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", sessions = "+sessions+", name = "+name+", grade = "+grade+", statuses = "+statuses+"]";
    }
}
