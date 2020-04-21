package com.mycompany.app.repositories;

import java.util.*;

import com.mycompany.app.models.*;
import com.mycompany.app.boundaryInterfaces.RideBoundaryInterface;

//Manages everything ride related
//Will have a list of all [Rides] and a list of all [JoinRequests]
public class RideRepository implements RideBoundaryInterface {

    List<Ride> rides = new ArrayList<Ride>();

    @Override
    public int postRide(Ride ride) {
        int rid = ride.setRid();
        rides.add(ride);
        return rid;
    }

    @Override
    public SearchResult searchRides(Search search) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void joinRide(Ride ride, JoinRequest joinRequest) {
        // TODO Auto-generated method stub

    }

    @Override
    public void confirmRideStarted() {
        // TODO Auto-generated method stub

    }

    @Override
    public void confirmRiderOnboard() {
        // TODO Auto-generated method stub

    }

    @Override
    public Report generateReport() {
        // TODO Auto-generated method stub
        return null;
    }

}