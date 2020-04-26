package com.mycompany.app;

import java.util.*;
import java.util.regex.Pattern;

import javax.ws.rs.*;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;
import io.dropwizard.jersey.PATCH;

import com.mycompany.app.models.*;
import com.mycompany.app.repositories.*;
import com.mycompany.app.boundaryInterfaces.*;
import com.codahale.metrics.annotation.Timed;

@Path("/sar")
public class SarService {

    RideBoundaryInterface rideRepo = new RideRepository();
    UserBoundaryInterface userRepo = new UserRepository();

    final String PHONE_NUMBER_REGEX = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";

    public SarService() {
    }

    private Map<String, Object> validationErrorResponse(String detail, String instance) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation");
        jsonObject.put("title", "Your request data didn't pass validation");
        jsonObject.put("detail", detail);
        jsonObject.put("status", 400);
        jsonObject.put("instance", instance);
        return jsonObject.toMap();
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/accounts")
    public Response createAccount(User user) {
        if (user.getFirstName() == null) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("Invalid first name", "/accounts")).build();
        }
        if (user.getLastName() == null) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("Invalid last number", "/accounts")).build();
        }
        if (!Pattern.compile(PHONE_NUMBER_REGEX).matcher(user.getCellPhone()).matches()) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("Invalid phone number", "/accounts")).build();
        }
        int aid = userRepo.createAccount(user);
        JSONObject jsonObject = (new JSONObject().put("aid", aid));
        return Response.created(UriBuilder.fromPath("accounts/" + aid).build()).entity(jsonObject.toMap()).build();
    }

    @PUT
    @Timed
    @Path("/accounts/{aid}/status")
    public Response changeAccountStatus(@PathParam("aid") int aid) {
        boolean result = userRepo.confirmAccount(aid);
        if (!result)
            return Response.status(Status.NOT_FOUND).build();
        return Response.noContent().build();
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/accounts/{aid}")
    public Response updateAccount(@PathParam("aid") int aid, User user) {
        if (user.getFirstName() == null) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("first_name invalid", "/accounts/" + aid)).build();
        }
        if (user.getLastName() == null) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("last_name invalid", "/accounts/" + aid)).build();
        }
        boolean result = userRepo.updateAccount(user, aid);
        if (!result)
            return Response.status(Status.NOT_FOUND).build();
        return Response.noContent().build();
    }

    @DELETE
    @Timed
    @Path("/accounts/{aid}")
    public Response deleteAccount(@PathParam("aid") int aid) {
        boolean result = userRepo.deleteAccount(aid);
        if (!result)
            return Response.status(Status.NOT_FOUND).build();
        return Response.noContent().build();
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
        if (rating.getRid() == 0) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("rid is invalid", "/accounts/" + aid + "/ratings")).build();
        }
        if (rating.getSentBy() == 0) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("sent_by_id is invalid", "/accounts/" + aid + "/ratings")).build();
        }

        if (rating.getRating() < 1 || rating.getRating() > 5) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("rating is invalid: must be between 1 and 5",
                            "/accounts/" + aid + "/ratings"))
                    .build();
        }
        int sid = userRepo.createRating(rating, aid);
        JSONObject jsonObject = (new JSONObject().put("sid", sid));
        return Response.created(UriBuilder.fromPath("accounts/" + aid + "/ratings/" + sid).build())
                .entity(jsonObject.toMap()).build();
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
        if (ride.getAid() == 0) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("aid invalid", "/rides")).build();
        }
        if (ride.getLocationInfo().getFromCity() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("from_city invalid", "/rides"))
                    .build();
        }
        if (ride.getLocationInfo().getToCity() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("to_city invalid", "/rides"))
                    .build();
        }
        if (ride.getRideDateTime().getDate() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("date invalid", "/rides"))
                    .build();
        }
        if (ride.getRideDateTime().getTime() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("time invalid", "/rides"))
                    .build();
        }
        if (ride.getCar().getMake() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("make invalid", "/rides"))
                    .build();
        }
        if (ride.getCar().getModel() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("model invalid", "/rides"))
                    .build();
        }
        if (ride.getCar().getColor() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("color invalid", "/rides"))
                    .build();
        }
        if (ride.getCar().getPlateState() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("plate_state invalid", "/rides"))
                    .build();
        }
        if (ride.getCar().getPlateSerial() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("plate_serial invalid", "/rides"))
                    .build();
        }
        if (ride.getConditions() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("conditions invalid", "/rides"))
                    .build();
        }
        if (ride.getMaxPassengers() < 0) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("max passengers must be greater than 0", "/rides")).build();
        }
        if (ride.getAmountPerPassenger() < 0) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("ammount per passenger must be greater than 0", "/rides")).build();
        }
        int rid = rideRepo.postRide(ride, userRepo);
        if (rid < 0)
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse(
                            "This account (" + ride.getAid() + ") is not active, may not create a ride.", "/rides"))
                    .build();
        JSONObject jsonObject = (new JSONObject().put("rid", rid));
        return Response.created(UriBuilder.fromPath("rides/" + rid).build()).entity(jsonObject.toMap()).build();
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/rides/{rid}")
    public Response updateRide(@PathParam("rid") int rid, Ride ride) {
        if (ride.getAid() == 0) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("aid invalid", "/rides/" + rid))
                    .build();
        }
        if (ride.getLocationInfo().getFromCity() == null) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("from_city invalid", "/rides/" + rid)).build();
        }
        if (ride.getLocationInfo().getToCity() == null) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("to_city invalid", "/rides/" + rid)).build();
        }
        if (ride.getRideDateTime().getDate() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("date invalid", "/rides/" + rid))
                    .build();
        }
        if (ride.getRideDateTime().getTime() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("time invalid", "/rides/" + rid))
                    .build();
        }
        if (ride.getCar().getMake() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("make invalid", "/rides/" + rid))
                    .build();
        }
        if (ride.getCar().getModel() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("model invalid", "/rides/" + rid))
                    .build();
        }
        if (ride.getCar().getColor() == null) {
            return Response.status(Status.BAD_REQUEST).entity(validationErrorResponse("color invalid", "/rides/" + rid))
                    .build();
        }
        if (ride.getCar().getPlateState() == null) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("plate_state invalid", "/rides/" + rid)).build();
        }
        if (ride.getCar().getPlateSerial() == null) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("plate_serial invalid", "/rides/" + rid)).build();
        }
        boolean result = rideRepo.updateRide(ride, rid);
        if (!result)
            return Response.status(Status.NOT_FOUND).build();
        return Response.noContent().build();
    }

    @DELETE
    @Timed
    @Path("/rides/{rid}")
    public Response deleteRide(@PathParam("rid") int rid) {
        boolean result = rideRepo.deleteRide(rid);
        if (!result)
            return Response.status(Status.NOT_FOUND).build();
        return Response.noContent().build();
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

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/rides/{rid}/join_requests")
    public Response joinRequest(@PathParam("rid") int rid, JoinRequest joinRequest) {
        if (joinRequest.getAid() == 0) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("aid invalid", "/rides/" + rid + "/join_requests")).build();
        }
        if (joinRequest.getPassengers() == 0) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("passengers invalid", "/rides/" + rid + "/join_requests")).build();
        }
        int jid = rideRepo.joinRide(rid, joinRequest);
        JSONObject jsonObject = (new JSONObject().put("jid", jid));
        return Response.created(UriBuilder.fromPath("rides/" + rid + "/join_requests/" + jid).build())
                .entity(jsonObject.toMap()).build();
    }

    @PATCH
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/rides/{rid}/join_requests/{jid}")
    public Response rideRequestStatus(@PathParam("rid") int rid, @PathParam("jid") int jid,
            RideRequestStatus rideRequestStatus) {
        boolean success = rideRepo.rideRequestStatus(rid, jid, rideRequestStatus);
        boolean success1 = rideRepo.ridePickupStatus(rid, jid, rideRequestStatus);
        if (!success && !success1)
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok().build();
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/rides/{rid}/messages")
    public Response addMessage(@PathParam("rid") int rid, Message message) {
        if (message.getAid() == 0) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("aid invalid", "/rides/" + rid + "/messages")).build();
        }
        if (message.getMessage() == null) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(validationErrorResponse("message invalid", "/rides/" + rid + "/messages")).build();
        }
        int mid = rideRepo.addMessage(rid, message);
        JSONObject jsonObject = (new JSONObject().put("mid", mid));
        return Response.created(UriBuilder.fromPath("rides/" + rid + "/messages/" + mid).build())
                .entity(jsonObject.toMap()).build();
    }

    @GET
    @Timed
    @Path("/rides/{rid}/messages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response message(@PathParam("rid") int rid) {
        List<Map<String, Object>> messages = rideRepo.messages(rid);
        if (messages == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(messages, MediaType.APPLICATION_JSON).build();
    }
}