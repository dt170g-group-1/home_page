/*
Created by IntelliJ IDEA.
User: Johannes
Date: 2025-02-12
Time: 11:16
To change this template use File | Settings | File Templates.
 */

package com.example.home_page;

public class AddEvents
{
    private String event;
    private String date;
    private String time;

    public AddEvents(String event, String date, String time) {
        this.event = event;
        this.date = date;
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
