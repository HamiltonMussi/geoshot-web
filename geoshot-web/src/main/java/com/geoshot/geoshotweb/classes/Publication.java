package com.geoshot.geoshotweb.classes;
public class Publication {
    private int pubId, ownerUserId;
    private String photo, dateOfCreation, correctValue;

    public Publication(int pubId, int ownerUserId, String photo, String dateOfCreation, String correctValue) {
        this.pubId          = pubId;
        this.correctValue   = correctValue;
        this.photo          = photo;
        this.dateOfCreation = dateOfCreation;
        this.ownerUserId    = ownerUserId;
    }

    public int getPubId() { return this.pubId; }

    public String getCorrectValue() { return this.correctValue; }

    public String getPhoto() { return this.photo; }

    public String getDateOfCreation() { return this.dateOfCreation; }

    public  int getOwnerUserId() { return this.ownerUserId; }

}
