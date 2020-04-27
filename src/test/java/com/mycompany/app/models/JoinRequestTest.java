package com.mycompany.app.models;

import org.junit.Test;

public class JoinRequestTest { 

    @Test
    public void testJoin(){
        JoinRequest j = new JoinRequest(1,1, true, true);

        LocationInfo l = new LocationInfo("", "", "", "");

        RideRequestStatus r = new RideRequestStatus();

        r.getAid();
        r.setAid(1);
        r.isPickupConfirmed();
        r.isRideConfirmed();
        r.setRideConfirmed(true);
        r.setPickupConfirmed(true);

        j.setAid(1);
        j.setPassengers(1);
        j.isRideConfirmed();
        j.setRideConfirmed(true);
        j.setPickUpConifrmed(true);
        j.getAid();
        j.getPassengers();
        j.isRideConfirmed();
        j.isPickUpConifrmed();
        j.getRideConfirmed();

        l.setFromCity("");
        l.setFromZip("");
        l.getFromCity();
        l.getToZip();
        l.setToCity("");
        l.setToZip("");
        l.getToCity();
    }

}