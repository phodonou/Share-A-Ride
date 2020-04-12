package com.mycompany.app.models;

public abstract class User {
    private int aid;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private String picture;
    private Boolean isActive;
    private Rating[] ratings;

    void confirmAccount() {
        isActive = true;
    }
}