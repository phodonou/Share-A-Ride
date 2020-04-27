package com.mycompany.app.models;

import org.junit.Test;

public class CarTest {

    @Test
    public void testGetters() {
        Car car1 = new Car("", "", "", "", "");
        car1.getClass();
        car1.getColor();
        car1.getMake();
        car1.getModel();
        car1.getPlateSerial();
        car1.getPlateState();
    }

    @Test
    public void testSetters() {
        Car car = new Car();
        car.setColor("");
        car.setMake("");
        car.setModel("");
        car.setPlateState("");
        car.setPlateSerial("");
    }
}