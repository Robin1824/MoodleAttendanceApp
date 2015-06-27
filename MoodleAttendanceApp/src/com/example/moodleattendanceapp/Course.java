package com.example.moodleattendanceapp;

public class Course {
	
	private String id;

    private Attendance[] attendance;

    private String short_name;

    private EnrolledStudents[] enrolledStudents;

    private String full_name;

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
