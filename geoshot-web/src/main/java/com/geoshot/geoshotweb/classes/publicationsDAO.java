package com.geoshot.geoshotweb.classes;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class publicationsDAO {


//    public static void main(String[] args) {
//        publicationsDAO hop = new publicationsDAO();
//        for(Feed f: hop.getFeedFromUser("BruceLee")) {
//            System.out.println(f.getUsername());
//        }
//    }


    private Connection dbconnection;

    public publicationsDAO() {
        ConnectionFactory factory = null;
        try {
            CredentialsReader cred = new CredentialsReader("/home/laplace/Projects/Geoshot/geoshot-web/misc/Credentials");
            factory = new ConnectionFactory(cred.getServer(),cred.getPort(),cred.getDB(),cred.getUser(), cred.getPlainPassword());
            this.dbconnection = factory.Connect();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        } catch(ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int insertPublication(Publication publication) {
        try {
            String queryString = "INSERT INTO publications (photo,owner_user_id,correct_value)";
            queryString += " VALUES (?,?,?)";

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            stmt.setString(1,publication.getPhoto());
            stmt.setInt(2,publication.getOwnerUserId());
            stmt.setInt(3,publication.getCorrectValue());

            return stmt.executeUpdate();

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public int insertPublication(String photo, int ownerUserId, int correctValue) {
        return this.insertPublication(new Publication(0,ownerUserId,photo,"",correctValue));
    }

    public int insertPublication(String photo, String username, int correctValue) {
        try {
            String queryString = String.format("SELECT users.usr_id as usr_id FROM users WHERE users.username = \"%s\"", username);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            ResultSet result = stmt.executeQuery();

            result.next();

            int ownerUserId = result.getInt("usr_id");

            return this.insertPublication(photo,ownerUserId,correctValue);

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }


    public List<Feed> getFeedFromUser(String username) {
        List<Feed> feed = new ArrayList<>();

        try {

            String queryString = String.format("SELECT p.pub_id as pub_id, p.photo as photo, p.date_of_creation as date_of_creation, u.username as username, u.photo as userphoto\n" +
                                 "FROM\n" +
                                 "(SELECT friends.usr_id2 as followed_id FROM friends WHERE usr_id1 = (SELECT users.usr_id FROM users WHERE users.username=\"%s\")) f\n" +
                                 "INNER JOIN publications p\n" +
                                 "ON p.owner_user_id = f.followed_id\n" +
                                 "INNER JOIN users u\n" +
                                 "ON p.owner_user_id = u.usr_id\n" +
                                 "ORDER BY p.date_of_creation DESC;",username);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            ResultSet results = stmt.executeQuery();

            while(results.next()) {
                feed.add(new Feed(results.getInt("pub_id"),
                                  results.getString("username"),
                                  results.getString("photo"),
                                  results.getString("date_of_creation"),
                                  results.getString("userphoto")));
            }

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return feed;
    }

}