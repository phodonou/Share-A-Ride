package com.mycompany.app;

import com.mycompany.app.models.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

public class SarServiceTest {
    @Test
    public void createAccountNullFirstName() {
        SarService sarService = new SarService();
        User mockUser = new User(null, "", "", "", true);
        Response res = sarService.createAccount(mockUser);
        assert (res.getStatus() == Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void createAccountNullLastName() {
        SarService sarService = new SarService();
        User mockUser = new User("", null, "", "", true);
        Response res = sarService.createAccount(mockUser);
        assert (res.getStatus() == Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void createAccountInvalidNumber() {
        SarService sarService = new SarService();
        User mockUser = new User("f", "", "", "", true);
        Response res = sarService.createAccount(mockUser);
        assert (res.getStatus() == Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void createAccountNullSucess() {
        SarService sarService = new SarService();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        Response res = sarService.createAccount(mockUser);
        assert (res.getStatus() == Status.CREATED.getStatusCode());
    }

    @Test
    public void changeAccountStatus() {
        SarService sarService = new SarService();
        Response res = sarService.changeAccountStatus(1);
        assert (res.getStatus() == Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void changeAccountStatus1() {
        SarService sarService = new SarService();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        sarService.createAccount(mockUser);
        Response res = sarService.changeAccountStatus(mockUser.getAid());
        assert (res.getStatus() == Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void updateAccountNullFirstName() {
        SarService sarService = new SarService();
        User mockUser = new User(null, "", "", "", true);
        Response res = sarService.updateAccount(1, mockUser);
        assert (res.getStatus() == Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void updateAccountNullLastName() {
        SarService sarService = new SarService();
        User mockUser = new User("", null, "", "", true);
        Response res = sarService.updateAccount(1, mockUser);
        assert (res.getStatus() == Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void updateAccountFalse() {
        SarService sarService = new SarService();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        sarService.createAccount(mockUser);
        Response res = sarService.updateAccount(mockUser.getAid() + 4, mockUser);
        assert (res.getStatus() == Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void updateAccount() {
        SarService sarService = new SarService();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        sarService.createAccount(mockUser);
        Response res = sarService.updateAccount(mockUser.getAid(), mockUser);
        assert (res.getStatus() == Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void deleteAccountFail() {
        SarService sarService = new SarService();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        sarService.createAccount(mockUser);
        Response res = sarService.deleteAccount(mockUser.getAid() + 1);
        assert (res.getStatus() == Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void deleteAccount() {
        SarService sarService = new SarService();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        sarService.createAccount(mockUser);
        Response res = sarService.deleteAccount(mockUser.getAid());
        assert (res.getStatus() == Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void accounts() {
        SarService sarService = new SarService();
        Response res = sarService.accounts("");
        assert (res.getStatus() == Status.OK.getStatusCode());
    }

    @Test
    public void accountFail() {
        SarService sarService = new SarService();
        Response res = sarService.account(1);
        assert (res.getStatus() == Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void account() {
        SarService sarService = new SarService();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        sarService.createAccount(mockUser);
        Response res = sarService.account(mockUser.getAid());
        assert (res.getStatus() == Status.OK.getStatusCode());
    }

    @Test
    public void rateAccountFail() {
        SarService sarService = new SarService();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        Rating mockRating = new Rating(1, mockUser.getAid(), -1, "");
        Response res = sarService.rateAccount(mockUser.getAid(), mockRating);
        assert (res.getStatus() == Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void rateAccount() {
        SarService sarService = new SarService();
        RideDateTime mockRideDateTime = new RideDateTime("", "");
        LocationInfo mockLocationInfo = new LocationInfo("", "", "", "");
        Car mockCar = new Car();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        sarService.createAccount(mockUser);
        Ride mockRide = new Ride(mockUser.getAid(), mockRideDateTime, 0, 0, "", mockCar, mockLocationInfo);
        sarService.createRide(mockRide);
        Rating mockRating = new Rating(mockRide.getRid(), mockUser.getAid(), 3, "");
        Response res = sarService.rateAccount(mockUser.getAid(), mockRating);
        assert (res.getStatus() == Status.CREATED.getStatusCode());
    }

    @Test
    public void driverRatings() {
        SarService sarService = new SarService();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        sarService.createAccount(mockUser);
        Response res = sarService.driverRatings(mockUser.getAid());
        assert (res.getStatus() == Status.OK.getStatusCode());
    }

    @Test
    public void driverRatingsFail() {
        SarService sarService = new SarService();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        sarService.createAccount(mockUser);
        Response res = sarService.driverRatings(mockUser.getAid() + 1);
        assert (res.getStatus() == Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void riderRatings() {
        SarService sarService = new SarService();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        sarService.createAccount(mockUser);
        Response res = sarService.riderRatings(mockUser.getAid());
        assert (res.getStatus() == Status.OK.getStatusCode());
    }

    @Test
    public void rideratingsFail() {
        SarService sarService = new SarService();
        User mockUser = new User("Prince", "Hodonou", "773-382-9268", "", true);
        sarService.createAccount(mockUser);
        Response res = sarService.riderRatings(mockUser.getAid() + 1);
        assert (res.getStatus() == Status.NOT_FOUND.getStatusCode());
    }
}
