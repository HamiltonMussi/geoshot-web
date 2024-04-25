package com.geoshot.geoshotweb.classes;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CredentialsReader {

    private String server, port, db, user, plainPassword;
    public CredentialsReader(String filename) throws  IOException {
        List<String> credentials = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        scanner.useDelimiter("\n");

        this.server             = scanner.next();
        this.port               = scanner.next();
        this.db                 = scanner.next();
        this.user               = scanner.next();
        this.plainPassword      = scanner.next();
    }

    public String getServer() {
        return this.server;
    }

    public String getPort() {
        return this.port;
    }

    public String getDB() {
        return this.db;
    }

    public String getUser() {
        return this.user;
    }

    public String getPlainPassword() {
        return this.plainPassword;
    }
}

