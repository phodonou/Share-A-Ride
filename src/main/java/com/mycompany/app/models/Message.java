package com.mycompany.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.mycompany.app.utilities.UniqueIdGenerator;

public class Message {

    private int mid;
    private String message;
    private int aid;
    private String dateCreated;

    public Message() {

    }

    public Message(String message, int aid) {
        this.message = message;
        this.aid = aid;
    }

    @JsonProperty("aid")
    public int getAid() {
        return this.aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @JsonProperty("msg")
    public String getMessage() {
        return this.message;
    }

    public int getMid() {
        return this.mid;
    }

    public int setMid() {
        int id = UniqueIdGenerator.getUniqueID();
        this.mid = id;
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}