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

        Map<String, Object> ride = rideRepository.ride(mockRide.getRid(), userRepository);

        assert (ride != null);
    }

    @Test
    public void joinRideSuccessfulSuccessful() {
        RideRepository rideRepository = new RideRepository();
        UserRepository userRepository = new UserRepository();
        Car mockCar = new Car();
        User mockUser = new User("", "", "", "", true);
        RideDateTime mockRideDateTime = new RideDateTime();
        LocationInfo mockLocationInfo = new LocationInfo();
        userRepository.createAccount(mockUser);
        JoinRequest mockJoinRequest = new JoinRequest();

        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        rideRepository.postRide(mockRide, userRepository);
        rideRepository.updateRide(mockRide, mockRide.getRid());

        int jid = rideRepository.joinRide(mockRide.getRid(), mockJoinRequest);

        assert (jid > 0);
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

}