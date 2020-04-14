package com.mycompany.app.repositories;

import com.mycompany.app.boundaryInterfaces.RideBoundaryInterface;
import com.mycompany.app.models.JoinRequest;
import com.mycompany.app.models.Report;
import com.mycompany.app.models.Ride;
import com.mycompany.app.models.Search;
import com.mycompany.app.models.SearchResult;

//Manages everything ride related
//Will have a list of all [Rides] and a list of all [JoinRequests]
public class RideRepository implements RideBoundaryInterface {

    @Override
    public void postRide(Ride ride) {
        // TODO Auto-generated method stub

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