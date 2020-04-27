package com.mycompany.app.models;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.mycompany.app.utilities.UniqueIdGenerator;

public class User {
    private int aid;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private String picture;
    private boolean isActive;
    private ArrayList<Rating> ratings;
    private String dateCreated;
    private int numOfRides;

    public User() {
    }

    public User(String firstName, String lastName, String cellPhone, String picture, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellPhone = cellPhone;
        this.picture = picture;
        this.isActive = isActive;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("phone")
    public String getCellPhone() {
        return cellPhone;
    }

    @JsonProperty("picture")
    public String getPicture() {
        return picture;
    }

    @JsonProperty("is_active")
    public boolean getIsActive() {
        return isActive;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public int getAid() {
        return aid;
    }

    public int setAid() {
        int id = UniqueIdGenerator.getUniqueID();
        this.aid = id;
        return id;
    }

    public int getNumOfRides() {
        return numOfRides;
    }

    public void increaseNumOfRides() {
        this.numOfRides++;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setRating() {
        this.ratings = new ArrayList<Rating>();
    }

    public void replaceAid(int aid) {
        this.aid = aid;
    }

    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }

    public void confirmAccount() {
        isActive = true;
    }
}