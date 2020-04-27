package com.mycompany.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RideRequestStatus {

    private int aid;
    private boolean rideConfirmed;
    private boolean pickupConfirmed;

    public RideRequestStatus() {
    }

    public RideRequestStatus(int aid, boolean rideConfirmed) {
        this.aid = aid;
        this.rideConfirmed = rideConfirmed;
    }

    @JsonProperty("aid")
    public int getAid() {
        return this.aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public boolean isRideConfirmed() {
        return this.rideConfirmed;
    }

    public boolean isPickupConfirmed() {
        return this.pickupConfirmed;
    }

    @JsonProperty("ride_confirmed")
    public boolean getRideConfirmed() {
        return this.rideConfirmed;
    }

    public void setRideConfirmed(boolean rideConfirmed) {
        this.rideConfirmed = rideConfirmed;
    }

    @JsonProperty("pickup_confirmed")
    public boolean getPickupConfirmed() {
        return pickupConfirmed;
    }

    public void setPickupConfirmed(boolean pickupConfirmed) {
        this.pickupConfirmed = pickupConfirmed;
    }
}