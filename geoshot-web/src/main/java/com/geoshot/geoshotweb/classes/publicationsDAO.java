package com.geoshot.geoshotweb.classes;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class publicationsDAO {


//    public static void main(String[] args) {
//        publicationsDAO hop = new publicationsDAO();
//        for(Publication hopper: hop.getMyPublications("laplace")) {
//            System.out.println(hopper.getPubId());
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
            stmt.setString(3,publication.getCorrectValue());

            return stmt.executeUpdate();

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public int insertPublication(String photo, int ownerUserId, String correctValue) {
        return this.insertPublication(new Publication(0,ownerUserId,photo,"",correctValue));
    }

    public int insertPublication(String photo, String username, String correctValue) {
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


    public Publication getPublicationById(int pubId) {

        Publication publication = null;

        try {

            String queryString = String.format("SELECT pub_id, correct_value, owner_user_id, photo, date_of_creation " +
                    "FROM publications p " +
                    "WHERE pub_id=%d",pubId);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            ResultSet result = stmt.executeQuery();

            result.next();

            publication = new Publication(
                    pubId,
                    result.getInt("owner_user_id"),
                    result.getString("photo"),
                    result.getString("date_of_creation"),
                    result.getString("correct_value")
            );

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return publication;
    }

    public List<Feed> getFeedFromUser(String username) {
        List<Feed> feed = new ArrayList<>();

        try {

            String queryString = String.format(  "SELECT p.pub_id as pub_id, p.photo as photo, p.date_of_creation as date_of_creation, u.username as username, u.photo as userphoto\n" +
                                                 "FROM\n" +
                                                 "(SELECT friends.usr_id2 as followed_id FROM friends WHERE usr_id1 = (SELECT users.usr_id FROM users WHERE users.username=\"%s\")) f\n" +
                                                 "INNER JOIN publications p\n" +
                                                 "ON p.owner_user_id = f.followed_id\n" +
                                                 "INNER JOIN users u\n" +
                                                 "ON p.owner_user_id = u.usr_id\n" +
                                                 "WHERE pub_id NOT IN (SELECT attempts.pub_id FROM attempts WHERE attempts.owner_att_usr_id = (SELECT users.usr_id FROM users WHERE users.username = \"%s\"))\n" +
                                                 "ORDER BY p.date_of_creation DESC;",username,username);

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

    public List<MyAttempt> getMyAttempts(String username) {
        List<MyAttempt> myAttempts = new ArrayList<>();

        try {

            String queryString = String.format("SELECT p.pub_id as pub_id, u.photo as userphoto, u.username as username, p.photo as photo, " +
                    "a.attempt_date as attempt_date, a.accuracy as accuracy FROM " +
                    "attempts a INNER JOIN publications p ON p.pub_id = a.pub_id " +
                    "INNER JOIN users u ON u.usr_id = a.owner_att_usr_id " +
                    "WHERE u.username=\"%s\"", username);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                myAttempts.add(new MyAttempt(results.getInt("pub_id"),
                        results.getDouble("accuracy"),
                        results.getString("photo"),
                        results.getString("userphoto"),
                        results.getString("attempt_date"),
                        results.getString("username")
                ));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myAttempts;

    }

    public List<Publication> getMyPublications(String username) {
        List<Publication> publications = new ArrayList<>();

        try {

            String queryString = String.format("SELECT pub_id, owner_user_id, photo, date_of_creation, correct_value FROM publications p " +
                    "WHERE p.owner_user_id = (SELECT u.usr_id FROM users u WHERE u.username=\"%s\")",username);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            ResultSet results = stmt.executeQuery();

            while(results.next()) {
                publications.add(
                        new Publication(
                                results.getInt("pub_id"),
                                results.getInt("owner_user_id"),
                                results.getString("photo"),
                                results.getString("date_of_creation"),
                                results.getString("correct_value")
                        )
                );
            }

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return publications;
    }

    public void removePublicationById(int pubId) {

        try {
            String queryString = String.format("DELETE FROM publications " +
                    "WHERE pub_id = %d", pubId);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            stmt.executeQuery();

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
