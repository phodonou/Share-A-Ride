package com.mycompany.app.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ride {
    private int rid;
    private Rider rider;
    private LocalDateTime localDateTime;
    private int maxPassengers;
    private int amountPerPassenger;
    private String conditions;
    private Point startingPoint;
    private Point endPoint;
    private Car car;
    private ArrayList<Note> messages;
}