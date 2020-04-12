package com.mycompany.app.models;

import java.time.LocalDateTime;

public class Rating {
    private int rid;
    private User sentBy;
    private LocalDateTime ratingDate;
    private Rating rating;
    private Comment comment;
}