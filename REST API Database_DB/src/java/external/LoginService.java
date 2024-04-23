/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package external;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T0322864
 */
public class LoginService {
    public LoginService() {}
    
    public String LoginUser(String Username, String Password) {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {
                //SQL Statement for getting a user detailsby checking correct username and password
            String sql = "SELECT user_id, full_name FROM users WHERE user_name = ? AND password = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, Username);
                statement.setString(2, Password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        //Converting SQL Columns to text return, it was first versions of REST API creation
                        String userId = resultSet.getString("user_id");
                        String fullName = resultSet.getString("full_name");
                        return "User ID: " + userId + ", Full Name: " + fullName;
                    } else {
                        
                        return "Invalid username or password";
                    }
                }
            } catch (SQLException e) {
                return "Error with login: " + e.getMessage();
            }
        }
        return "Error with connection to the database";
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
            return "Error with connection: " + ex.getMessage();
        }
    }
}
