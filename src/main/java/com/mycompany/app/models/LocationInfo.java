package com.mycompany.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationInfo {

    private String fromCity;
    private String fromZip;
    private String toCity;
    private String toZip;

    public LocationInfo() {
    }

    public LocationInfo(String fromCity, String fromZip, String toCity, String toZip) {
        this.fromCity = fromCity;
        this.fromZip = fromZip;
        this.toCity = toCity;
        this.toZip = toZip;
    }

    @JsonProperty("from_city")
    public String getFromCity() {
        return this.fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    @JsonProperty("from_zip")
    public String getFromZip() {
        return this.fromZip;
    }

    public void setFromZip(String fromZip) {
        this.fromZip = fromZip;
    }

    @JsonProperty("to_city")
    public String getToCity() {
        return this.toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    @JsonProperty("to_zip")
    public String getToZip() {
        return this.toZip;
    }

    public void setToZip(String toZip) {
        this.toZip = toZip;
    }

}