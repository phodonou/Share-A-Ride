package com.mycompany.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {
    private String make;
    private String model;
    private String color;
    private String plateState;
    private String plateSerial;

    public Car() {
    }

    public Car(String make, String model, String color, String plateState, String plateSerial) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.plateState = plateState;
        this.plateSerial = plateSerial;
    }

    @JsonProperty("make")
    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @JsonProperty("model")
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @JsonProperty("color")
    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty("plate_state")
    public String getPlateState() {
        return this.plateState;
    }

    public void setPlateState(String plateState) {
        this.plateState = plateState;
    }

    @JsonProperty("plate_serial")
    public String getPlateSerial() {
        return this.plateSerial;
    }

    public void setPlateSerial(String plateSerial) {
        this.plateSerial = plateSerial;
    }
}
