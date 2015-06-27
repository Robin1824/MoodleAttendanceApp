package com.example.moodleattendanceapp;

public class Attendance
{
    private String id;

    private Sessions[] sessions;

    private String name;

    private String grade;

    private Statuses[] statuses;

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
