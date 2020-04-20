package com.mycompany.app.repositories;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ArrayList;
import org.json.JSONObject;

import com.mycompany.app.models.Rating;
import com.mycompany.app.models.User;
import com.mycompany.app.boundaryInterfaces.UserBoundaryInterface;

//Manages everything user related
//Will have a list of all [Users]
public class UserRepository implements UserBoundaryInterface {

    List<User> users = new ArrayList<User>();

    @Override
    public int createAccount(User user) {
        int aid = user.setAid();
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
        return true;
    }

    @Override
    public List<Map<String, Object>> accounts() {
        List<Map<String, Object>> usersJson = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < users.size(); i++) {
            usersJson.add(jsonUser(users.get(i)).toMap());
        }
        return usersJson;
    }

    @Override
    public Map<String, Object> account(int aid){
        User user = getUser(aid);
        if (user == null)
            return null;
        return jsonUser(user).toMap();
    }



    @Override
    public void createRating(Rating rating, String aid) {
        // TODO Auto-generated method stub

    }

    @Override
    public User getRating(String aid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean sendMessageNotification(User person) {
        // TODO Auto-generated method stub
        return false;
    }

    User getUser(int aid) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getAid() == aid) {
                return user;
            }
        }
        return null;
    }

    JSONObject jsonUser(User user) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aid", user.getAid());
        jsonObject.put("name", user.getFirstName() + " " + user.getLastName());
        jsonObject.put("date_created", user.getDateCreated());
        jsonObject.put("is_active", user.getIsActive());
        return jsonObject;
    }

}