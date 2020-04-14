package com.mycompany.app.boundaryInterfaces;

import com.mycompany.app.models.Rating;
import com.mycompany.app.models.User;

//interface to manage everything user related
public interface UserBoundaryInterface {

    String createAccount (User user);

    void createRating(Rating rating, String aid);

    User getRating(String aid);

    boolean sendMessageNotification(User person);
}