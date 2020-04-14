package com.mycompany.app.models;

public class Rider extends User {

    public Rider(int aid, String firstName, String lastName, String cellPhone, String picture, boolean isActive,
            Rating[] ratings) {
        super(firstName, lastName, cellPhone, picture, isActive);
    }

}