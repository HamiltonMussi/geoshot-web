package com.geoshot.geoshotweb.classes;

public class Feed {
    private int pubId;
    private String photo, userPhoto, dateOfCreation, username;

    public Feed(int pubId, String username, String photo, String dateOfCreation, String userPhoto) {
        this.pubId          = pubId;
        this.userPhoto      = userPhoto;
        this.photo          = photo;
        this.dateOfCreation = dateOfCreation;
        this.username       = username;
    }

    public int getPubId() { return this.pubId; }

    public String getUserPhoto() { return this.userPhoto; }

    public String getPhoto() { return this.photo; }

    public String getDateOfCreation() { return this.dateOfCreation; }

    public  String getUsername() { return this.username; }
}
