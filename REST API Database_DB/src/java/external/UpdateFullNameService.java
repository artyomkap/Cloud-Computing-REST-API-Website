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
//Function for updating the User Full_name
public class UpdateFullNameService {
    public UpdateFullNameService() {}
    
    public String UpdateFullName(String user_id, String full_name) {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {
                //SQL Statement for updating the full name of user
                String sql = "UPDATE users SET full_name = ? WHERE user_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, full_name);                    
                    preparedStatement.setString(2, user_id);

                    int affectedRows = preparedStatement.executeUpdate();
                    
                    if (affectedRows > 0) {
                        return "Successfully updated the Full_Name!";
                    } else {
                        return "Failed to update the Full Name. No rows affected.";
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
