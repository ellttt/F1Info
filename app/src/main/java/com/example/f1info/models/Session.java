package com.example.f1info.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Session {

    private String sessionType;
    private ZonedDateTime sessionDateTime;

    public Session(String sessionType, ZonedDateTime dateTime){
        this.sessionType=sessionType;
        this.sessionDateTime=dateTime;

    }

    public String getSessionType() {
        return sessionType;
    }

    public ZonedDateTime getSessionDateTime() {
        return sessionDateTime;
    }

    public String getDate(){
        return sessionDateTime.format(DateTimeFormatter.ofPattern("MM/dd"));
    }

    public String getTime(){
        return sessionDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

}
