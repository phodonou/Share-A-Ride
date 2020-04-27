package com.mycompany.app.models;

import org.junit.Test;

public class RideTest {

    @Test
    public void testSetters(){
        Ride ride = new Ride();

        ride.setAid(1);
        ride.setRideDateTime(null);
        ride.setMaxPassengers(1);
        ride.setConditions("");
        ride.setCar(null);
        ride.setLocationInfo(null);
        ride.getMaxPassengers();
        ride.getConditions();
        ride.getAmountPerPassenger();
    }   
}