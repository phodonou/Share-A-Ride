package com.mycompany.app.models;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.mycompany.app.utilities.UniqueIdGenerator;

public class Ride {

    private int rid;
    private int aid;
    private RideDateTime rideDateTime;
    private int maxPassengers;
    private int amountPerPassenger;
    private String conditions;
    private Car car;
    private LocationInfo locationInfo;
    private ArrayList<JoinRequest> joinRequests;
    private ArrayList<Message> messages;

    public Ride() {
    }

    public Ride(int aid, RideDateTime rideDateTime, int maxPassengers, int amountPerPassenger, String conditions,
            Car car, LocationInfo locationInfo) {
        this.aid = aid;
        this.rideDateTime = rideDateTime;
        this.maxPassengers = maxPassengers;
        this.amountPerPassenger = amountPerPassenger;
        this.conditions = conditions;
        this.car = car;
        this.locationInfo = locationInfo;
    }

    public int getRid() {
        return this.rid;
    }

    public int setRid() {
        int id = UniqueIdGenerator.getUniqueID();
        this.rid = id;
        return id;
    }

    public void replaceRid(int rid) {
        this.rid = aid;
    }

    @JsonProperty("aid")
    public int getAid() {
        return this.aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @JsonProperty("date_time")
    public RideDateTime getRideDateTime() {
        return this.rideDateTime;
    }

    public void setRideDateTime(RideDateTime rideDateTime) {
        this.rideDateTime = rideDateTime;
    }

    @JsonProperty("max_passengers")
    public int getMaxPassengers() {
        return this.maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    @JsonProperty("amount_per_passenger")
    public int getAmountPerPassenger() {
        return this.amountPerPassenger;
    }

    public void setAmountPerPassenger(int amountPerPassenger) {
        this.amountPerPassenger = amountPerPassenger;
    }

    @JsonProperty("conditions")
    public String getConditions() {
        return this.conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    @JsonProperty("car_info")
    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @JsonProperty("location_info")
    public LocationInfo getLocationInfo() {
        return this.locationInfo;
    }

    public void setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
    }

    public void setJoinRequest() {
        joinRequests = new ArrayList<JoinRequest>();
    }

    public ArrayList<JoinRequest> getJoinRequests() {
        return joinRequests;
    }

    public void addJoinRequest(JoinRequest joinRequest) {
        this.joinRequests.add(joinRequest);
    }

    public void setMessages() {
        messages = new ArrayList<Message>();
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    @Override
    public String toString() {
        return "{" + " rid='" + getRid() + "'" + ", aid='" + getAid() + "'" + ", rideDateTime='" + getRideDateTime()
                + "'" + ", maxPassengers='" + getMaxPassengers() + "'" + ", amountPerPassenger='"
                + getAmountPerPassenger() + "'" + ", conditions='" + getConditions() + "'" + ", car='" + getCar() + "'"
                + ", locationInfo='" + getLocationInfo() + "'" + ", joinRequests='" + getJoinRequests() + "'"
                + ", messages='" + getMessages() + "'" + "}";
    }

}