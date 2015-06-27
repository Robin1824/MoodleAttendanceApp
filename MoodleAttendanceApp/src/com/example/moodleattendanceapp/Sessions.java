package com.example.moodleattendanceapp;

public class Sessions
{
    private String id;

    private String duration;

    private String studentscanmark;

    private String lasttaken;

    private String description;

    private String descriptionformat;

    private String timemodified;

    private String lasttakenby;

    private String groupid;

    private String sessdate;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String getStudentscanmark ()
    {
        return studentscanmark;
    }

    public void setStudentscanmark (String studentscanmark)
    {
        this.studentscanmark = studentscanmark;
    }

    public String getLasttaken ()
    {
        return lasttaken;
    }

    public void setLasttaken (String lasttaken)
    {
        this.lasttaken = lasttaken;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getDescriptionformat ()
    {
        return descriptionformat;
    }

    public void setDescriptionformat (String descriptionformat)
    {
        this.descriptionformat = descriptionformat;
    }

    public String getTimemodified ()
    {
        return timemodified;
    }

    public void setTimemodified (String timemodified)
    {
        this.timemodified = timemodified;
    }

    public String getLasttakenby ()
    {
        return lasttakenby;
    }

    public void setLasttakenby (String lasttakenby)
    {
        this.lasttakenby = lasttakenby;
    }

    public String getGroupid ()
    {
        return groupid;
    }

    public void setGroupid (String groupid)
    {
        this.groupid = groupid;
    }

    public String getSessdate ()
    {
        return sessdate;
    }

    public void setSessdate (String sessdate)
    {
        this.sessdate = sessdate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", duration = "+duration+", studentscanmark = "+studentscanmark+", lasttaken = "+lasttaken+", description = "+description+", descriptionformat = "+descriptionformat+", timemodified = "+timemodified+", lasttakenby = "+lasttakenby+", groupid = "+groupid+", sessdate = "+sessdate+"]";
    }
}
