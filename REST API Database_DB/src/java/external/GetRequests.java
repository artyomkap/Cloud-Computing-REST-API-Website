/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package external;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
//The function if for getting created requests by user_id
public class GetRequests {
    public GetRequests() {}
    
     public String GetAllRequestsByID(String user_id) {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {
                //SQL Statement to get trip requests from Database
                String sql = "SELECT * FROM trip_requests WHERE user_id = ?";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, user_id);
                    
                    try (ResultSet resultSet = statement.executeQuery()) {
                        JsonArray userArray = new JsonArray();
                        
                        while (resultSet.next()) {
                            //Converting SQL columns to json keys
                            JsonObject userObject = new JsonObject();
                            userObject.addProperty("request_id", resultSet.getString("request_id"));
                            userObject.addProperty("trip_id", resultSet.getString("trip_id"));
                            userObject.addProperty("user_id", resultSet.getString("user_id"));
                            userObject.addProperty("status", resultSet.getString("status"));
                            userArray.add(userObject);
                        }
                        
                        Gson gson = new Gson();
                        String jsonResult = gson.toJson(userArray);                 
                        return jsonResult;
                    }
                } catch (SQLException e) {                    
                    e.printStackTrace();
                    return "Error with login: " + e.getMessage();
                }
            }
            return "Error with connection to the database";
        } catch (SQLException ex) {            
            ex.printStackTrace();
            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
            return "Error with connection: " + ex.getMessage();
        }
    }
}
