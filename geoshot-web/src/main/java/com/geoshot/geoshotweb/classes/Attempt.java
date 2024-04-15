package com.geoshot.geoshotweb.classes;

public class Attempt {

    private int pubId, ownerAttemptUserId;
    private String attemptDate;
    private double accuracy;

    public Attempt(int pubId, int ownerAttemptUserId, String attemptDate, double accuracy) {
        this.pubId              = pubId;
        this.ownerAttemptUserId = ownerAttemptUserId;
        this.attemptDate        = attemptDate;
        this.accuracy           = accuracy;
    }

    public int getPubId() { return this.pubId; }

    public int getOwnerAttemptUserId() { return this.ownerAttemptUserId; }

    public String getAttemptDate() { return this.attemptDate; }

    public  double getAccuracy() { return this.accuracy; }
}
