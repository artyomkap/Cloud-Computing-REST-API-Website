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
public class DeleteTrip {
    public DeleteTrip() {}
    
    public String DeleteTripById(String trip_id) {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {
                //SQL Statement for deleting a trip
                String sql = "DELETE FROM trips WHERE trip_id = ?";
                
                
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {                    
                    preparedStatement.setString(1, trip_id);                                                                           
                    int affectedRows = preparedStatement.executeUpdate();
                    
                    if (affectedRows > 0) {
                        //If trip was successfully deleted
                        return "Trip was successfuly deleted!";
                    } else {
                        //If trip was not deleted
                        return "error with deleting a trip";
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
