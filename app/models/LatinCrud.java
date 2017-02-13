package models;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class LatinCrud
{
    public int userId;
    public int id;
    public String title;
    public String body;


    public int getUserId()
    {
        return userId;
    }

    @JsonSetter("userId")
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getId()
    {
        return id;
    }

    @JsonSetter("id")
    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    @JsonSetter("title")
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getBody()
    {
        return body;
    }

    @JsonSetter("body")
    public void setBody(String body)
    {
        this.body = body;
    }


}
