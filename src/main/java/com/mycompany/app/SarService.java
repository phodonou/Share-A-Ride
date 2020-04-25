package com.mycompany.app;

import java.util.*;
import javax.ws.rs.*;
import org.json.JSONObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mycompany.app.models.*;
import com.mycompany.app.repositories.*;
import com.mycompany.app.boundaryInterfaces.*;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/sar")
public class SarService {

    RideBoundaryInterface rideRepo = new RideRepository();
    UserBoundaryInterface userRepo = new UserRepository();

    ObjectMapper mapper = new ObjectMapper();

    public SarService() {
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/accounts")
    public Response createAccount(User user) {
        int aid = userRepo.createAccount(user);
        JSONObject jsonObject = (new JSONObject().put("aid", aid));
        return Response.ok(jsonObject.toMap(), MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Timed
    @Path("/accounts/{aid}/status")
    public Response changeAccountStatus(@PathParam("aid") int aid) {
        boolean result = userRepo.confirmAccount(aid);
        if (!result)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok().build();
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/accounts/{aid}")
    public Response updateAccount(@PathParam("aid") int aid, User user) {
        boolean result = userRepo.updateAccount(user, aid);
        if (!result)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok().build();
    }

    @DELETE
    @Timed
    @Path("/accounts/{aid}")
    public Response deleteAccount(@PathParam("aid") int aid) {
        boolean result = userRepo.deleteAccount(aid);
        if (!result)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok().build();
    }

    @GET
    @Timed
    @Path("/accounts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response accounts(@QueryParam("key") String key) {
        List<Map<String, Object>> users = userRepo.accounts(key);
        return Response.ok(users, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Timed
    @Path("/accounts/{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response account(@PathParam("aid") int aid) {
        Map<String, Object> jsonUser = userRepo.account(aid);
        if (jsonUser == null)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(jsonUser, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Timed
    @Path("/accounts/{aid}/ratings")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rateAccount(@PathParam("aid") int aid, Rating rating) {
        int sid = userRepo.createRating(rating, aid);
        if (sid < 0)
            return Response.status(Status.NOT_FOUND).build();
        JSONObject jsonObject = (new JSONObject().put("sid", sid));
        return Response.ok(jsonObject.toMap(), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Timed
    @Path("/accounts/{aid}/driver")
    @Produces(MediaType.APPLICATION_JSON)
    public Response driverRatings(@PathParam("aid") int aid) {
        Map<String, Object> jsonUserRides = userRepo.getRating(aid);
        if (jsonUserRides == null)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(jsonUserRides, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Timed
    @Path("/accounts/{aid}/rider")
    @Produces(MediaType.APPLICATION_JSON)
    public Response riderRatings(@PathParam("aid") int aid) {
        Map<String, Object> jsonUserRides = userRepo.getRating(aid);
        if (jsonUserRides == null)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(jsonUserRides, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/rides")
    public Response createRide(Ride ride) {
        int rid = rideRepo.postRide(ride);
        JSONObject jsonObject = (new JSONObject().put("rid", rid));
        return Response.ok(jsonObject.toMap(), MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/rides/{rid}")
    public Response updateRide(@PathParam("rid") int rid, Ride ride) {
        boolean result = rideRepo.updateRide(ride, rid);
        if (!result)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok().build();
    }

    @DELETE
    @Timed
    @Path("/rides/{rid}")
    public Response deleteRide(@PathParam("rid") int rid) {
        boolean result = rideRepo.deleteRide(rid);
        if (!result)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok().build();
    }

    @GET
    @Timed
    @Path("/rides")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rides(@QueryParam("from") String from, @QueryParam("to") String to,
            @QueryParam("date") String date) {
        List<Map<String, Object>> rides = rideRepo.rides(from, to, date);
        return Response.ok(rides, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Timed
    @Path("/rides/{rid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ride(@PathParam("rid") int rid) {
        Map<String, Object> jsonRide = rideRepo.ride(rid, userRepo);
        if (jsonRide == null)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(jsonRide, MediaType.APPLICATION_JSON).build();
    }

}