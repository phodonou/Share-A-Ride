package com.mycompany.app.boundaryInterfaces;

import java.util.*;

import com.mycompany.app.models.*;

//interface to manage everything ride related
public interface RideBoundaryInterface {
    int postRide(Ride ride, UserBoundaryInterface userRepository);

    boolean updateRide(Ride ride, int rid);

    boolean deleteRide(int rid);

    List<Map<String, Object>> rides(String from, String to, String date);

    List<Map<String, Object>> messages(int rid);

    Map<String, Object> ride(int rid, UserBoundaryInterface userRepository);

    int joinRide(int rid, JoinRequest joinRequest, UserBoundaryInterface userRepository);

    Ride getRide(int rid);

    boolean rideRequestStatus(int rid, int jid, RideRequestStatus rideRequestStatus);

    boolean ridePickupStatus(int rid, int jid, RideRequestStatus rideRequestStatus);

    int addMessage(int rid, Message message);

    List<Report> generateReport();

    Map<String, Object> getReport(int pid, String startDate, String endDate) throws Exception;
}