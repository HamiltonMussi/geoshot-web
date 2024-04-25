package com.geoshot.geoshotweb.classes;

public class MyAttempt {
    private int pubId;

    private  double accuracy;

    private String username, photo, userphoto, attemptDate;

    public MyAttempt(int pubId, double accuracy, String photo, String userphoto, String attemptDate, String username) {
        this.pubId       = pubId;
        this.accuracy    = accuracy;
        this.photo       = photo;
        this.userphoto   = userphoto;
        this.attemptDate = attemptDate;
        this.username    = username;
    }

    public int getPubId() { return this.pubId; }

    public double getAccuracy() { return this.accuracy; }

    public String getPhoto() { return this.photo; }

    public String getUserphoto() { return this.userphoto; }

    public String getAttemptDate() { return  this.attemptDate; }

    public String getUsername() { return this.username; }
}


