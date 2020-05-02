package com.mycompany.app.repositories;

import java.util.*;
import com.mycompany.app.models.*;

import org.junit.Test;

public class RideRepositoryTest {

    @Test
    public void postRideSuccessful() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());
        Ride ride = rideRepository.getRide(mockRide.getRid());
        assert (ride.getRid() == mockRide.getRid());
    }

    @Test
    public void postRideUnsuccessful() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(5, mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        int rid = rideRepository.postRide(mockRide, userRepository);

        assert (rid == -2);
    }

    @Test
    public void postRideUnsuccessful1() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", false);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        int rid = rideRepository.postRide(mockRide, userRepository);

        assert (rid == -1);
    }

    @Test
    public void updateRideSuccessful() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());
        Ride ride = rideRepository.getRide(mockRide.getRid());
        assert (ride.getRid() == mockRide.getRid());
    }

    @Test
    public void updateNullRide() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        boolean updated = rideRepository.updateRide(mockRide, mockRide.getRid());
        assert (!updated);
    }

    @Test
    public void deleteRideSuccessful() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());
        Ride ride = rideRepository.getRide(mockRide.getRid());
        assert (ride.getRid() == mockRide.getRid());

        rideRepository.deleteRide(mockRide.getRid());
        assert (!rideRepository.rides.contains(mockRide));
    }

    @Test
    public void deleteNullRide() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);

        boolean deleted = rideRepository.deleteRide(mockRide.getRid());
        assert (!deleted);
    }

    @Test
    public void ridesSuccessful() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        List<Map<String, Object>> result = rideRepository.rides(null, null, null);
        assert (!result.isEmpty());
    }

    @Test
    public void ridesSuccessfulNot() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime("", "");
        LocationInfo mockLocationInfo = new LocationInfo("", "", "", "");
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        List<Map<String, Object>> result = rideRepository.rides("", "", "");
        assert (!result.isEmpty());
    }

    @Test
    public void ridesSuccessfulNotNull() {
        RideRepository rideRepository = new RideRepository();

        List<Map<String, Object>> result = rideRepository.rides("", "", "");
        assert (result.isEmpty());
    }

    @Test
    public void messagesSuccessful() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());
        Ride ride = rideRepository.getRide(mockRide.getRid());

        List<Map<String, Object>> result = rideRepository.messages(ride.getRid());
        assert (result.isEmpty());
    }

    @Test
    public void messagesSuccessful1() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        Message mockMessage = new Message("", mockUser.getAid());
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());
        rideRepository.addMessage(mockRide.getRid(), mockMessage);
        Ride ride = rideRepository.getRide(mockRide.getRid());

        List<Map<String, Object>> result = rideRepository.messages(ride.getRid());
        assert (!result.isEmpty());
    }

    @Test
    public void messagesNullRide() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        List<Map<String, Object>> result = rideRepository.messages(5);
        assert (result == null);
    }

    @Test
    public void rideSuccessful() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        Rating mockRating = new Rating(mockRide.getRid(), mockUser.getAid(), 5, "");
        userRepository.createRating(mockRating, mockUser.getAid());

        Map<String, Object> ride = rideRepository.ride(mockRide.getRid(), userRepository);

        assert (ride != null);
    }

    @Test
    public void rideNull() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        Map<String, Object> ride = rideRepository.ride(mockRide.getAid() + 1, userRepository);

        assert (ride == null);
    }

    @Test
    public void rideRequestStatusSuccessful() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);
        JoinRequest mockJoinRequest = new JoinRequest();
        RideRequestStatus mockRideRequestStatus = new RideRequestStatus(mockUser.getAid(), true);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        boolean status = rideRepository.rideRequestStatus(mockRide.getRid(), mockJoinRequest.getJid(),
                mockRideRequestStatus);

        assert (!status);
    }

    @Test
    public void ridePickupStatusSuccessful() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);
        JoinRequest mockJoinRequest = new JoinRequest();
        RideRequestStatus mockRideRequestStatus = new RideRequestStatus(mockUser.getAid(), true);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        boolean status = rideRepository.ridePickupStatus(mockRide.getRid(), mockJoinRequest.getJid(),
                mockRideRequestStatus);

        assert (!status);
    }

    @Test
    public void addMessageSuccessful() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);
        Message mockMessage = new Message();

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());
        Ride ride = rideRepository.getRide(mockRide.getRid());

        int mid = rideRepository.addMessage(ride.getRid(), mockMessage);

        assert (mid > 1);
    }

    @Test
    public void generateReportSuccessful() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        List<Report> reports = rideRepository.generateReport();

        assert (!reports.isEmpty());
    }

    @Test
    public void getReportSuccessful() throws Exception {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime("26-Apr-2020", "");
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        List<Report> reports = rideRepository.generateReport();

        Report report = reports.get(0);

        rideRepository.getReport(report.getPid(), "26-Apr-2020", "");
    }

    @Test
    public void getReportSuccessful1() throws Exception {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime("26-Apr-2020", "");
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        List<Report> reports = rideRepository.generateReport();

        Report report = reports.get(0);

        rideRepository.getReport(report.getPid(), "", "26-Apr-2020");
    }

    @Test
    public void getReportSuccessful2() throws Exception {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime("26-Apr-2020", "");
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        List<Report> reports = rideRepository.generateReport();

        Report report = reports.get(0);

        rideRepository.getReport(report.getPid(), "", "");
    }

    @Test
    public void getReportSuccessful3() throws Exception {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime("26-Apr-2020", "");
        RideDateTime mockRideDateTime1 = new RideDateTime("29-Apr-2020", "");
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        Ride mockRide1 = new Ride(mockUser.getAid(), mockRideDateTime1, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide1, mockRide.getRid());

        List<Report> reports = rideRepository.generateReport();

        Report report = reports.get(0);

        rideRepository.getReport(report.getPid(), "26-Apr-2020", "29-Apr-2020");
    }

    @Test
    public void getNullReport() throws Exception {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime("26-Apr-2020", "");
        RideDateTime mockRideDateTime1 = new RideDateTime("29-Apr-2020", "");
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        Ride mockRide1 = new Ride(mockUser.getAid(), mockRideDateTime1, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide1, mockRide.getRid());

        List<Report> reports = rideRepository.generateReport();

        Report report = reports.get(0);

        rideRepository.getReport(report.getPid() + 1, "26-Apr-2020", "29-Apr-2020");
    }

    @Test
    public void joinRideNullUser() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime("26-Apr-2020", "");
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        JoinRequest joinRequest = new JoinRequest(mockUser.getAid() + 1, 2, true, true);
        int jid = rideRepository.joinRide(mockRide.getRid(), joinRequest, userRepository);
        assert (jid == -1);
    }

    @Test
    public void joinRideNotActiveUser() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", false);
        RideDateTime mockRideDateTime = new RideDateTime("26-Apr-2020", "");
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        JoinRequest joinRequest = new JoinRequest(mockUser.getAid(), 2, true, true);
        int jid = rideRepository.joinRide(mockRide.getRid(), joinRequest, userRepository);
        assert (jid == -1);
    }

    @Test
    public void joinRideSucess() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime("26-Apr-2020", "");
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        JoinRequest joinRequest = new JoinRequest(mockUser.getAid(), 2, true, true);
        int jid = rideRepository.joinRide(mockRide.getRid(), joinRequest, userRepository);
        assert (jid > 0);
    }

    @Test
    public void rideRequestStatus() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime("26-Apr-2020", "");
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        JoinRequest joinRequest = new JoinRequest(mockUser.getAid(), 2, true, true);
        rideRepository.joinRide(mockRide.getRid(), joinRequest, userRepository);
        RideRequestStatus requestStatus = new RideRequestStatus(mockUser.getAid(), true);
        rideRepository.rideRequestStatus(mockRide.getRid(), joinRequest.getJid(), requestStatus);
    }

    @Test
    public void ridePickupStatus() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime("26-Apr-2020", "");
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        JoinRequest joinRequest = new JoinRequest(mockUser.getAid(), 2, true, true);
        rideRepository.joinRide(mockRide.getRid(), joinRequest, userRepository);
        RideRequestStatus requestStatus = new RideRequestStatus(mockUser.getAid(), true);
        rideRepository.ridePickupStatus(mockRide.getRid(), joinRequest.getJid(), requestStatus);
    }
}