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
//Function for declining a requests from users
public class CancelRequest {
    public CancelRequest() {}
    
    public String CancelRequestById(String request_id, String trip_id) {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {      
                // SQL Statements to update trip requests and trips
                String sql = "UPDATE trip_requests SET status = 'canceled' WHERE request_id = ?";
                String updateSql = "UPDATE trips SET trip_status = 'no_guests' WHERE trip_id = ?";                
                
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {                    
                    preparedStatement.setString(1, request_id);                
                                     
                    int affectedRowsInsert = preparedStatement.executeUpdate();
                    
                    if (affectedRowsInsert > 0) {
                        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                            
                            updateStatement.setString(1, trip_id);
                          
                            int affectedRowsUpdate = updateStatement.executeUpdate();

                            if (affectedRowsUpdate > 0) {
                                //If update was successful
                                return "Request successfully cancelled!";
                            } else {
                                //If update was not successful
                                return "Request was not canceled due to Error!";
                            }
                        }
                    } else {
                        return "Request was not accepted due to Error!";
                    }
                }
                
            } else {
                return "Error with connection to the database";
            }
        } catch (SQLException ex) {
            Logger.getLogger(CancelRequest.class.getName()).log(Level.SEVERE, null, ex);
            return "Error with connection: " + ex.getMessage();
        }
    }
}
