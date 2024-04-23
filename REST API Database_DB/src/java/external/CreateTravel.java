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
//The function is for creating a trips
public class CreateTravel {
    public CreateTravel() {}
    
    public String CreateTrip(String User_Id, String City, String City_Picture, String Description) {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {
                //SQL Statement for creation of trip
                String sql = "INSERT INTO trips (user_id, city, city_picture, description, trip_status) VALUES (?, ?, ?, ?,'no_guests')";                
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    
                    statement.setString(1, User_Id);
                    statement.setString(2, City);
                    statement.setString(3, City_Picture);
                    statement.setString(4, Description);
                    
                    
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        //if trip was successfully created
                        return "New Trip Added";
                    } else {
                        //if trip was not created
                        return "Error with adding new trip";
                    }            
                } catch (SQLException e) {
                    return "Error with adding new trip: " + e.getMessage() + User_Id + City + Description;
                }
            }
            return "Error with connection to the database";
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
            return "Error with connection: " + ex.getMessage();
        }
    }
}
