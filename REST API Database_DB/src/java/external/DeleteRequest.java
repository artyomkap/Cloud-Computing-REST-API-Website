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
//The function for deleting the user's created requests
public class DeleteRequest {
    public DeleteRequest() {}
    
    public String DeleteRequestById(String request_id, String trip_id) {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {
                // Prepare the SQL statements for deleting from trip_requests and update trips
                String sql = "DELETE FROM trip_requests WHERE request_id = ?";                               
                String sql_new = "UPDATE trips SET trip_status = 'no_guests' WHERE trip_id = ?";
                
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, request_id);
                    int affectedRows = preparedStatement.executeUpdate();
                    if (affectedRows > 0) {

                        try (PreparedStatement secondStatement = connection.prepareStatement(sql_new)) {
                            secondStatement.setString(1, trip_id);
                            int secondAffectedRows = secondStatement.executeUpdate();
                            if (secondAffectedRows > 0) {
                                //if request was deleted
                                return "Request was successfully deleted, and trip status was updated!";
                            } else {
                                //if request was not deleted
                                return "Request was deleted, but trip status was not updated due to an error!";
                            }
                        }
                    } else {
                        return "Request was not deleted due to an error!";
                    }
                }
            } else {
                return "Error with connection to the database";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeleteTrip.class.getName()).log(Level.SEVERE, null, ex);
            return "Error with connection: " + ex.getMessage();
        }
    }
}
