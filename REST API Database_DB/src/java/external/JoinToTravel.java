/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package external;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T0322864
 */
public class JoinToTravel {
    public JoinToTravel() {}
    
    public String joinToTrip(String userId, String tripId) {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {
                //SQL statements for Joining to Trip(creating a new trip_requests) and updating trip status
                String insertSql = "INSERT INTO trip_requests (trip_id, user_id, status) VALUES (?, ?, 'pending')";
                String updateSql = "UPDATE trips SET trip_status = 'pending' WHERE trip_id = ?";

                try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                    insertStatement.setString(1, tripId);
                    insertStatement.setString(2, userId);
                    int affectedRowsInsert = insertStatement.executeUpdate();

                    if (affectedRowsInsert > 0) {
                        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                            updateStatement.setString(1, tripId);
                            int affectedRowsUpdate = updateStatement.executeUpdate();

                            if (affectedRowsUpdate > 0) {
                                //If execution of sql was successfull
                                return "Successfully joined to the trip!";
                            } else {
                                //If execution of sql was not successfull
                                return "Failed to update trip status. No rows affected.";
                            }
                        }
                    } else {
                        return "Failed to join to the trip. No rows affected in the INSERT operation.";
                    }
                }
            } else {
                return "Error with connection to the database";
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoinToTravel.class.getName()).log(Level.SEVERE, null, ex);
            return "Error with connection: " + ex.getMessage();
        }
    }
}
