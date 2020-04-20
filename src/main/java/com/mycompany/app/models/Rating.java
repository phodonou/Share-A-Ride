package com.mycompany.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.app.utilities.UniqueIdGenerator;

public class Rating {

    private int sid;
    private int rid;
    private int sentBy;
    private String ratingDate;
    private int rating;
    private String comment;

    public Rating() {
    }

    public Rating(int rid, int sentBy, int rating, String comment) {
        this.rid = rid;
        this.sentBy = sentBy;
        this.rating = rating;
        this.comment = comment;
    }

    public int getSid() {
        return this.sid;
    }

    public int setSid() {
        int id = UniqueIdGenerator.getUniqueID();
        this.rid = id;
        return id;
    }

    @JsonProperty("rid")
    public int getRid() {
        return this.rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @JsonProperty("sent_by_id")
    public int getSentBy() {
        return this.sentBy;
    }

    public void setSentBy(int sentBy) {
        this.sentBy = sentBy;
    }

    public String getRatingDate() {
        return this.ratingDate;
    }

    public void setRatingDate(String ratingDate) {
        this.ratingDate = ratingDate;
    }

    @JsonProperty("rating")
    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @JsonProperty("comment")
    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "{" + " sid='" + getSid() + "'" + ", rid='" + getRid() + "'" + ", sentBy='" + getSentBy() + "'"
                + ", ratingDate='" + getRatingDate() + "'" + ", rating='" + getRating() + "'" + ", comment='"
                + getComment() + "'" + "}";
    }

}
