package com.mycompany.app.boundaryInterfaces;

import com.mycompany.app.models.JoinRequest;
import com.mycompany.app.models.Report;
import com.mycompany.app.models.Ride;
import com.mycompany.app.models.Search;
import com.mycompany.app.models.SearchResult;

//interface to manage everything ride related
public interface RideBoundaryInterface {
    void postRide(Ride ride);

    SearchResult searchRides(Search search);

    void joinRide(Ride ride, JoinRequest joinRequest);

    void confirmRideStarted();

    void confirmRiderOnboard();

    Report generateReport();
}