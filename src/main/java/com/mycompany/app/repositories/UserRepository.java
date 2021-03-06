package com.mycompany.app.repositories;

import java.util.*;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.mycompany.app.models.*;
import com.mycompany.app.boundaryInterfaces.*;

//Manages everything user related
//Will have a list of all [Users]
public class UserRepository implements UserBoundaryInterface {

    List<User> users = new ArrayList<User>();

    @Override
    public int createAccount(User user) {
        int aid = user.setAid();
        user.setRating();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy, HH:mm:ss");
        user.setDateCreated(dtf.format(dateTime));
        users.add(user);
        return aid;
    }

    public boolean confirmAccount(int aid) {
        User user = getUser(aid);
        if (user == null)
            return false;
        user.confirmAccount();
        return true;
    }

    public boolean deleteAccount(int aid) {
        User user = getUser(aid);
        if (user == null)
            return false;
        users.remove(user);
        return true;
    }

    @Override
    public boolean updateAccount(User user, int aid) {
        User currentUser = getUser(aid);
        if (currentUser == null)
            return false;
        users.remove(currentUser);
        user.replaceAid(currentUser.getAid());
        users.add(user);
        if (user.getRatings() == null) {
            user.setRating();
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> accounts(String key) {
        if (key == null || key.isEmpty()) {
            List<Map<String, Object>> usersJson = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < users.size(); i++) {
                usersJson.add(jsonUser(users.get(i)).toMap());
            }
            return usersJson;
        } else {
            List<Map<String, Object>> usersJson = new ArrayList<Map<String, Object>>();
            List<User> users = getUsers(key);
            for (User user : users) {
                usersJson.add(jsonUser(user).toMap());
            }
            return usersJson;
        }

    }

    @Override
    public Map<String, Object> account(int aid) {
        User user = getUser(aid);
        if (user == null)
            return null;
        return jsonUser(user).toMap();
    }

    @Override
    public int createRating(Rating rating, int aid) {
        User user = getUser(aid);
        if (user == null)
            ;
        int sid = rating.setSid();
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        rating.setRatingDate(dtf.format(dateTime));
        user.addRating(rating);
        return sid;
    }

    @Override
    public Map<String, Object> getRating(int aid) {
        User user = getUser(aid);
        if (user == null)
            return null;
        JSONObject jsonUserRides = jsonUserRides(user);
        return jsonUserRides.toMap();
    }

    @Override
    public boolean sendMessageNotification(User person) {
        return true;
    }

    @Override
    public boolean phoneNumberExists(String phoneNumber) {
        for (User user : this.users) {
            if (user.getCellPhone().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(int aid) {
        for (User user : this.users) {
            if (user.getAid() == aid) {
                return user;
            }
        }
        return null;
    }

    List<User> getUsers(String key) {
        ArrayList<User> users = new ArrayList<User>();
        String keyword = key.trim().toLowerCase();
        for (User user : this.users) {
            String userFirstName = user.getFirstName().trim().toLowerCase();
            String userLastName = user.getLastName().trim().toLowerCase();
            String userCellPhone = user.getCellPhone().trim().toLowerCase();
            boolean queryCorrect = userFirstName.contains(keyword) || userLastName.contains(keyword)
                    || userCellPhone.contains(keyword);
            if (queryCorrect) {
                users.add(user);
            }
        }
        return users;
    }

    private JSONObject jsonUser(User user) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aid", user.getAid());
        jsonObject.put("name", user.getFirstName() + " " + user.getLastName());
        jsonObject.put("date_created", user.getDateCreated());
        jsonObject.put("is_active", user.getIsActive());
        return jsonObject;
    }

    private JSONObject jsonRating(Rating rating) {
        JSONObject jsonObject = new JSONObject();
        User sentByUser = getUser(rating.getSentBy());
        jsonObject.put("rid", rating.getRid());
        jsonObject.put("sent_by_id", rating.getSentBy());
        jsonObject.put("first_name", sentByUser.getFirstName());
        jsonObject.put("date", rating.getRatingDate());
        jsonObject.put("rating", rating.getRating());
        jsonObject.put("comment", rating.getComment());
        return jsonObject;
    }

    private ArrayList<JSONObject> jsonRatings(List<Rating> ratings) {
        ;
        ArrayList<JSONObject> jsonRatingsArray = new ArrayList<JSONObject>();
        for (Rating rating : ratings) {
            jsonRatingsArray.add(jsonRating(rating));
        }
        return jsonRatingsArray;
    }

    private JSONObject jsonUserRides(User user) {
        JSONObject jsonObject = new JSONObject();
        ArrayList<Rating> ratings = user.getRatings();
        ArrayList<JSONObject> jsonRatings = jsonRatings(ratings);
        double avgRating = calculateAvgRating(ratings);
        jsonObject.put("aid", user.getAid());
        jsonObject.put("first_name", user.getFirstName());
        jsonObject.put("rides", user.getNumOfRides());
        jsonObject.put("ratings", ratings.size());
        jsonObject.put("average_rating", avgRating);
        jsonObject.put("detail", jsonRatings);
        return jsonObject;
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

}