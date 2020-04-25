package com.mycompany.app.repositories;

import java.util.*;
import org.json.JSONObject;
import java.time.LocalDateTime;
import com.mycompany.app.models.*;
import java.time.format.DateTimeFormatter;

import com.mycompany.app.boundaryInterfaces.RideBoundaryInterface;
import com.mycompany.app.boundaryInterfaces.UserBoundaryInterface;

//Manages everything ride related
//Will have a list of all [Rides] 
public class RideRepository implements RideBoundaryInterface {

    List<Ride> rides = new ArrayList<Ride>();

    @Override
    public int postRide(Ride ride) {
        ride.setJoinRequest();
        ride.setMessages();
        int rid = ride.setRid();
        rides.add(ride);
        return rid;
    }

    @Override
    public boolean updateRide(Ride ride, int rid) {
        Ride currentRide = getRide(rid);
        if (currentRide == null)
            return false;
        rides.remove(currentRide);
        currentRide.replaceRid(currentRide.getRid());
        rides.add(ride);
        return true;
    }

    @Override
    public boolean deleteRide(int rid) {
        Ride ride = getRide(rid);
        if (ride == null)
            return false;
        rides.remove(ride);
        return true;
    }

    @Override
    public List<Map<String, Object>> rides(String from, String to, String date) {
        if (from != null && to != null && date != null) {
            List<Map<String, Object>> jsonRides = new ArrayList<Map<String, Object>>();
            List<Ride> queriedRides = getRides(from, to, date);
            for (Ride ride : queriedRides) {
                jsonRides.add(jsonRide(ride).toMap());
            }
            return jsonRides;
        } else {
            List<Map<String, Object>> jsonRides = new ArrayList<Map<String, Object>>();
            for (Ride ride : rides) {
                jsonRides.add(jsonRide(ride).toMap());
            }
            return jsonRides;
        }
    }

    @Override
    public List<Map<String, Object>> messages(int rid) {
        Ride ride = getRide(rid);
        if (ride == null)
            return null;
        List<Map<String, Object>> jsonMessages = new ArrayList<>();
        for (Message message : ride.getMessages()) {
            jsonMessages.add(jsonMessage(message).toMap());
        }
        return jsonMessages;
    }

    @Override
    public Map<String, Object> ride(int rid, UserBoundaryInterface userRepository) {
        Ride ride = getRide(rid);
        if (ride == null)
            return null;
        return jsonRideDetailed(ride, userRepository).toMap();
    }

    @Override
    public SearchResult searchRides(Search search) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int joinRide(int rid, JoinRequest joinRequest) {
        int id = joinRequest.setJid();
        Ride ride = getRide(rid);
        ride.addJoinRequest(joinRequest);
        return id;
    }

    @Override
    public boolean rideRequestStatus(int rid, int jid, RideRequestStatus rideRequestStatus) {
        JoinRequest joinRequest = getJoinRequest(rid, jid);
        if (joinRequest == null || rideRequestStatus.getAid() != joinRequest.getAid())
            return false;
        joinRequest.setRideConfirmed(rideRequestStatus.getRideConfirmed());
        return true;
    }

    @Override
    public boolean ridePickupStatus(int rid, int jid, RideRequestStatus rideRequestStatus) {
        JoinRequest joinRequest = getJoinRequest(rid, jid);
        if (joinRequest == null || rideRequestStatus.getAid() != joinRequest.getAid())
            return false;
        joinRequest.setPickUpConifrmed(rideRequestStatus.getPickupConfirmed());
        return true;
    }

    @Override
    public int addMessage(int rid, Message message) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy, HH:mm:ss");
        message.setDateCreated(dtf.format(dateTime));
        Ride ride = getRide(rid);
        int id = message.setMid();
        ride.addMessage(message);
        return id;
    }

    @Override
    public Report generateReport() {
        // TODO Auto-generated method stub
        return null;
    }

    private Ride getRide(int rid) {
        for (Ride ride : this.rides) {
            if (ride.getRid() == rid)
                return ride;
        }
        return null;
    }

    private JoinRequest getJoinRequest(int rid, int jid) {
        Ride ride = getRide(rid);
        if (ride == null)
            return null;
        for (JoinRequest joinRequest : ride.getJoinRequests()) {
            if (joinRequest.getJid() == jid)
                return joinRequest;
        }
        return null;
    }

    private List<Ride> getRides(String from, String to, String date) {
        List<Ride> queriedRides = new ArrayList<Ride>();
        String fromKey = from.toLowerCase().trim();
        String toKey = to.toLowerCase().trim();
        String dateKey = date.toLowerCase().trim();
        for (Ride ride : rides) {
            String rideFrom = ride.getLocationInfo().getFromCity().toLowerCase().trim();
            String rideTo = ride.getLocationInfo().getToCity().toLowerCase().trim();
            String rideDate = ride.getRideDateTime().getDate().toLowerCase().trim();
            boolean queryCorrect = fromKey.equals(rideFrom) && toKey.equals(rideTo) && dateKey.equals(rideDate);
            if (queryCorrect) {
                queriedRides.add(ride);
            }
        }
        return queriedRides;
    }

    public JSONObject jsonMessage(Message message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mid", message.getMid());
        jsonObject.put("sent_by_aid", message.getAid());
        jsonObject.put("date", message.getDateCreated());
        jsonObject.put("body", message.getMessage());
        return jsonObject;
    }

    private JSONObject jsonRide(Ride ride) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rid", ride.getRid());
        jsonObject.put("location_info", ride.getLocationInfo());
        jsonObject.put("date_time", ride.getRideDateTime());
        return jsonObject;
    }

    private JSONObject jsonRating(Rating rating) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rid", rating.getRid());
        jsonObject.put("rating", rating.getRating());
        jsonObject.put("comment", rating.getComment());
        jsonObject.put("date", rating.getRatingDate());
        return jsonObject;
    }

    private List<JSONObject> jsonRatings(ArrayList<Rating> ratings) {
        List<JSONObject> jsonRatings = new ArrayList<>();
        for (Rating rating : ratings) {
            jsonRatings.add(jsonRating(rating));
        }
        return jsonRatings;
    }

    double calculateAvgRating(ArrayList<Rating> ratings) {
        if (ratings.size() == 0)
            return 0;
        int sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getRating();
        }
        return (sum / (double) ratings.size());
    }

    private JSONObject jsonRideDetailed(Ride ride, UserBoundaryInterface userRepository) {
        int aid = ride.getAid();
        User user = userRepository.getUser(aid);
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> comments = jsonRatings(user.getRatings());
        jsonObject.put("rid", ride.getRid());
        jsonObject.put("location_info", ride.getLocationInfo());
        jsonObject.put("date_time", ride.getRideDateTime());
        jsonObject.put("car_info", ride.getCar());
        jsonObject.put("driver", user.getFirstName());
        jsonObject.put("driver_picture", user.getPicture());
        jsonObject.put("rides", user.getNumOfRides());
        jsonObject.put("ratings", user.getRatings().size());
        jsonObject.put("average_rating", calculateAvgRating(user.getRatings()));
        jsonObject.put("comments_about_driver", comments);
        return jsonObject;
    }

}