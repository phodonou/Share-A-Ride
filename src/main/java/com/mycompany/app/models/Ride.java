package com.mycompany.app.models;

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

}