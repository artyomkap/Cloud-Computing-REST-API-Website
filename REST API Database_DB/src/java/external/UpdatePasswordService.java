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
//Function for updating the User Password
public class UpdatePasswordService {
    public UpdatePasswordService() {}
    
    public String UpdatePass(String user_id, String password) {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {
                //SQL Statement for updating the password of user
                String sql = "UPDATE users SET password = ? WHERE user_id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, password);                    
                    preparedStatement.setString(2, user_id);

                    int affectedRows = preparedStatement.executeUpdate();
                    
                    if (affectedRows > 0) {
                        return "Successfully updated the password!";
                    } else {
                        return "Failed to update the password. No rows affected.";
                    }
                }
                
            } else {
                return "Error with connection to the database";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdatePasswordService.class.getName()).log(Level.SEVERE, null, ex);
            return "Error with connection: " + ex.getMessage();
        }
    }
}
