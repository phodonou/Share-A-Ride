package com.mycompany.app.repositories;

import java.util.ArrayList;
import java.util.List;

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
    public List<User> accounts(){
        return users;
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

}