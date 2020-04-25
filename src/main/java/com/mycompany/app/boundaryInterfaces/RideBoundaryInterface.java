package com.mycompany.app.boundaryInterfaces;

import java.util.*;

import com.mycompany.app.models.JoinRequest;
import com.mycompany.app.models.Report;
import com.mycompany.app.models.Ride;
import com.mycompany.app.models.Search;
import com.mycompany.app.models.SearchResult;

//interface to manage everything ride related
public interface RideBoundaryInterface {
    int postRide(Ride ride);

    boolean updateRide(Ride ride, int rid);

    boolean deleteRide(int rid);

    List<Map<String, Object>> rides(String from, String to, String date);

    Map<String, Object> ride(int rid, UserBoundaryInterface serRepository);

    SearchResult searchRides(Search search);

    void joinRide(Ride ride, JoinRequest joinRequest);

    void confirmRideStarted();

    void confirmRiderOnboard();

    Report generateReport();
}