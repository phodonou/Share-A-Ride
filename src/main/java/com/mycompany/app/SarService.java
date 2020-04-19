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
        String jsonString = (new JSONObject().put("aid", aid)).toString();
        return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
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
    public Response accounts() {
        List<User> users = userRepo.accounts();
        return Response.ok(users, MediaType.APPLICATION_JSON).build();
    }

}