package com.geoshot.geoshotweb.classes;

public class User {
    private int userId, attempts;
    private String username, name, email, password, photo;
    private double accuracy;

    public User(int userId, int attempts, String name, String username, String email, String password, String photo, double accuracy) {
        this.userId     =   userId;
        this.username   = username;
        this.attempts   = attempts;
        this.name       =     name;
        this.email      =    email;
        this.password   = password;
        this.photo      =    photo;
        this.accuracy   = accuracy;
    }

    public int getUserId() {
        return this.userId;
    }

    public int getAttempts() {
        return this.attempts;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getUsername() { return this.username; }

    public double getAccuracy() {
        return this.accuracy;
    }
}
