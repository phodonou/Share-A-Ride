package com.mycompany.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RideDateTime {

    private String date;
    private String time;

    public RideDateTime() {
    }

    public RideDateTime(String date, String time) {
        this.date = date;
        this.time = time;
    }
    
    @JsonProperty("date")
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("time")
    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}