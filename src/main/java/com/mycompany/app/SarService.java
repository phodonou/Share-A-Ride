package com.mycompany.app;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.mycompany.app.repositories.*;

import org.json.JSONObject;

import com.mycompany.app.boundaryInterfaces.*;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.models.*;

@Path("/sar")
public class SarService {

    RideBoundaryInterface rideRepo = new RideRepository();
    UserBoundaryInterface userRepo = new UserRepository();

    ObjectMapper mapper = new ObjectMapper();

    public SarService() {

    }

    @POST
    @Timed
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/accounts")
    public String createAccount(User user) {
        String aid = userRepo.createAccount(user);
        String jsonString = (new JSONObject().put("aid", aid)).toString();
        return jsonString;
    }
}