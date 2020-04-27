package com.mycompany.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.mycompany.app.utilities.UniqueIdGenerator;

public class JoinRequest {

    private int jid;
    private int aid;
    private int passengers;
    private boolean rideConfirmed;
    private boolean pickUpConifrmed;

    public JoinRequest() {

    }

    public JoinRequest(int aid, int passengers, boolean rideConfirmed, boolean pickUpConifrmed) {
        this.aid = aid;
        this.passengers = passengers;
        this.rideConfirmed = rideConfirmed;
        this.pickUpConifrmed = pickUpConifrmed;
    }

    @JsonProperty("aid")
    public int getAid() {
        return this.aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @JsonProperty("passengers")
    public int getPassengers() {
        return this.passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public boolean isRideConfirmed() {
        return this.rideConfirmed;
    }

    @JsonProperty("ride_confirmed")
    public boolean getRideConfirmed() {
        return this.rideConfirmed;
    }

    public void setRideConfirmed(boolean rideConfirmed) {
        this.rideConfirmed = rideConfirmed;
    }

    public boolean isPickUpConifrmed() {
        return this.pickUpConifrmed;
    }

    @JsonProperty("pickup_confirmed")
    public boolean getPickUpConifrmed() {
        return this.pickUpConifrmed;
    }

    public void setPickUpConifrmed(boolean pickUpConifrmed) {
        this.pickUpConifrmed = pickUpConifrmed;
    }

    public int setJid() {
        int id = UniqueIdGenerator.getUniqueID();
        this.jid = id;
        return id;
    }

    public int getJid() {
        return jid;
    }

}