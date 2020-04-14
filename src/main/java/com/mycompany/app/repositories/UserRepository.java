package com.mycompany.app.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.app.boundaryInterfaces.UserBoundaryInterface;
import com.mycompany.app.models.Rating;
import com.mycompany.app.models.User;

//Manages everything user related
//Will have a list of all [Users]
public class UserRepository implements UserBoundaryInterface {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public String createAccount(User user) {
        return String.valueOf(user.setAid());
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

}