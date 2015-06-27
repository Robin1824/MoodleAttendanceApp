package com.example.moodleattendanceapp;

import org.json.JSONException;
import org.json.JSONObject;

public class Statuses extends JSONObject
{
    private String id;

    private String visible;

    private String acronym;

    private String description;

    private String grade;

    private String deleted;
    
    public Statuses(JSONObject obj) throws JSONException
    {
    	try
    	{
	    	id=obj.getString("id");
	    	visible=obj.getString("visible");
	    	acronym=obj.getString("acronym");
	    	description=obj.getString("description");
	    	grade=obj.getString("deleted");
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

    public String getVisible ()
    {
        return visible;
    }

    public void setVisible (String visible)
    {
        this.visible = visible;
    }

    public String getAcronym ()
    {
        return acronym;
    }

    public void setAcronym (String acronym)
    {
        this.acronym = acronym;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getGrade ()
    {
        return grade;
    }

    public void setGrade (String grade)
    {
        this.grade = grade;
    }

    public String getDeleted ()
    {
        return deleted;
    }

    public void setDeleted (String deleted)
    {
        this.deleted = deleted;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", visible = "+visible+", acronym = "+acronym+", description = "+description+", grade = "+grade+", deleted = "+deleted+"]";
    }
}
