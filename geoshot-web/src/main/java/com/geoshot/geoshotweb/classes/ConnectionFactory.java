package com.geoshot.geoshotweb.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String server, port, db, user, password;

    public ConnectionFactory(String server, String port, String db, String user, String password) {
        this.server     =   server;
        this.port       =     port;
        this.db         =       db;
        this.user       =     user;
        this.password   = password;
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

    public String getPassword() {
        return this.password;
    }

    public Connection Connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        String connString = String.format("jdbc:mariadb://%s:%s/%s", getServer(), getPort(), getDB());
        return DriverManager.getConnection(connString, getUser(), getPassword());
    }
}