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
//Function for regestrating the user
public class RegistrationService {    
    public RegistrationService() {}    
    
    public String RegistrateUser(String ID, String Full_Name, String Username, String Password) {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {
                //SQL Statement which inserts a new user details
                String sql = "INSERT INTO users (user_id, full_name, user_name, password) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, ID);
                    statement.setString(2, Full_Name);
                    statement.setString(3, Username);
                    statement.setString(4, Password);
                    
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        //If SQL Execution was successful
                        return "Successfully Registered";
                    } else {
                          //If SQL Execution was not successful
                        return "Error with registration";
                    }            
                } catch (SQLException e) {
                    return "Error with registration: " + e.getMessage() + ID + Full_Name + Username + Password;
                }
            }
            return "Error with connection to the database";
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
            return "Error with connection: " + ex.getMessage();
        }
    }
}
