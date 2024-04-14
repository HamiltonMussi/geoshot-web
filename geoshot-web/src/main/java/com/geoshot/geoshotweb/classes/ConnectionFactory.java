package com.geoshot.geoshotweb.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        ConnectionFactory minha_maria_DB_teste = new ConnectionFactory("127.0.0.1","3306","geoshot_db","root","14ed0d589c78a23567c8bc5dc84841976b3df52d43d9ff8d569653605f9325ce");
//        Connection db = minha_maria_DB_teste.Connect();
//        System.out.println(db);
//
//  }
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