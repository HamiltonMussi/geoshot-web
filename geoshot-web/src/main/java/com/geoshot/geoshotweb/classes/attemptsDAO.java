package com.geoshot.geoshotweb.classes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class attemptsDAO {

    private Connection dbconnection;

    public attemptsDAO() {
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

    public int insertAttempt(Attempt attempt) {
        try {
            String queryString = "INSERT INTO attempts (pub_id,owner_att_usr_id,accuracy)";
            queryString += " VALUES (?,?,?)";

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            stmt.setInt(1,attempt.getPubId());
            stmt.setInt(2,attempt.getOwnerAttemptUserId());
            stmt.setDouble(3,attempt.getAccuracy());

            return stmt.executeUpdate();

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public int insertAttempt(int pubId, int ownerAttemptUserId, double accuracy) {
        return this.insertAttempt(new Attempt(pubId, ownerAttemptUserId, "", accuracy));
    }

    public int insertAttempt(int pubId, String username, double accuracy) {
        try {
            String queryString = String.format("SELECT users.usr_id as usr_id FROM users WHERE users.username = \"%s\"", username);

            PreparedStatement stmt = this.dbconnection.prepareStatement(queryString);

            ResultSet result = stmt.executeQuery();

            result.next();

            int ownerUserId = result.getInt("usr_id");

            return this.insertAttempt(pubId,ownerUserId,accuracy);

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }
}
