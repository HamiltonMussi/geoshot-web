package com.geoshot.geoshotweb.classes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

public class usersDAO {
    private Connection dbconnection;

    public usersDAO() {
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

    public int insertUser(User user) {
        try {
            String queryString = "INSERT INTO users (name, username, email, password, photo)";
            queryString += " VALUES (?,?,?,?,?)";

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            stmt.setString(1,user.getName());
            stmt.setString(2,user.getUsername());
            stmt.setString(3,user.getEmail());
            stmt.setString(4,user.getPassword());
            stmt.setString(5,user.getPhoto());

            return stmt.executeUpdate();
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public int insertUser(String name, String username, String email, String password, String photo) {
        return this.insertUser(new User(0,0, name, username, email, password, photo,0.));
    }

    public boolean check(String fieldToCheck, String checker) {
        try {
            String queryString = String.format("SELECT users.%s FROM users WHERE users.%s = \"%s\"",fieldToCheck,fieldToCheck,checker);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            ResultSet result = stmt.executeQuery();

            result.next();

            if(checker.equalsIgnoreCase(result.getString(fieldToCheck))) {
                return true;
            } else {
                return false;
            }

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public String checkUser(String username, String hashPassword) {
        try {
            String queryString = String.format("SELECT users.username, users.password FROM users WHERE users.username = \"%s\" AND users.password = \"%s\"", username, hashPassword);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            ResultSet userpass = stmt.executeQuery();

            userpass.next();

            if(username.equalsIgnoreCase(userpass.getString("username"))) {
                return userpass.getString("username");
            } else {
                return null;
            }

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public User getUser(String username) {
        if(!this.check("username",username)) {
            return null;
        } else {

            User thisUser = null;

            try {

                String queryString = String.format("SELECT * FROM users WHERE users.username = \"%s\"",username);

                PreparedStatement stmt = dbconnection.prepareStatement(queryString);

                ResultSet result = stmt.executeQuery();

                result.next();

                thisUser = new User(
                        result.getInt("usr_id"),
                        result.getInt("attempts"),
                        result.getString("name"),
                        result.getString("username"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("photo"),
                        result.getDouble("accuracy")
                );


            } catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return thisUser;

        }
    }

    public void changePasswordFromUser(String username, String newPassword) {

        String newHashPassword = HashGeneretor.getHash("SHA256",newPassword);

        try {

            String queryString = String.format("UPDATE users SET users.password=\"%s\" " +
                    "WHERE users.username=\"%s\"", newHashPassword, username);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            stmt.executeQuery();

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void changePhotoFromUser(String username, String encodedPhoto) {

        try {

            String queryString = String.format("UPDATE users SET users.photo=\"%s\" " +
                    "WHERE users.username=\"%s\"", encodedPhoto, username);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            stmt.executeQuery();

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean follows(String usernameFollower, String usernameFollowed) {
        try {
            String queryString = String.format("SELECT * FROM friends f WHERE " +
                    "f.usr_id1 = (SELECT usr_id FROM users u WHERE u.username = \"%s\") AND " +
                    "f.usr_id2 = (SELECT usr_id FROM users u WHERE u.username = \"%s\")", usernameFollower, usernameFollowed);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            ResultSet result = stmt.executeQuery();

            if(result.next()) return true;
            else return false;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public void insertFollow(String usernameFollower, String usernameFollowed) {
        try {
            String queryString = String.format("INSERT INTO friends (usr_id1, usr_id2) VALUES ((SELECT usr_id FROM users " +
                    "WHERE users.username=\"%s\"),(SELECT usr_id FROM users WHERE users.username=\"%s\"));", usernameFollower, usernameFollowed);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            stmt.executeQuery();

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public  void removeFollow(String usernameFollower, String usernameFollowed) {
        try {
            String queryString = String.format("DELETE FROM friends WHERE " +
                    "friends.usr_id1=(SELECT usr_id FROM users WHERE users.username=\"%s\") AND " +
                    "friends.usr_id2=(SELECT usr_id FROM users WHERE users.username=\"%s\")", usernameFollower, usernameFollowed);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            stmt.executeQuery();

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws SQLException, Throwable {
        try {
            this.dbconnection.close();
        } finally {
            super.finalize();
        }
    }
}