package com.mycompany.app.boundaryInterfaces;

import java.util.*;

import com.mycompany.app.models.*;

//interface to manage everything ride related
public interface RideBoundaryInterface {
    int postRide(Ride ride);

    boolean updateRide(Ride ride, int rid);

    boolean deleteRide(int rid);

    List<Map<String, Object>> rides(String from, String to, String date);

    List<Map<String, Object>> messages(int rid);

    Map<String, Object> ride(int rid, UserBoundaryInterface serRepository);

    SearchResult searchRides(Search search);

    int joinRide(int rid, JoinRequest joinRequest);

    boolean rideRequestStatus(int rid, int jid, RideRequestStatus rideRequestStatus);

    boolean ridePickupStatus(int rid, int jid, RideRequestStatus rideRequestStatus);

    int addMessage(int rid, Message message);

    Report generateReport();
}