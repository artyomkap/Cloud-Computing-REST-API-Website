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
//The function for getting all travels from DB
public class GetAllTravels {
    public GetAllTravels() {}
    
    public String GetAllTrips() {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {
                //SQL statement to get infromation from trips table
                String sql = "SELECT * FROM trips";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        JsonArray travelsArray = new JsonArray();
                        
                        while (resultSet.next()) {
                            //Converting SQL columns to json keys
                            JsonObject travelObject = new JsonObject();
                            travelObject.addProperty("trip_id", resultSet.getInt("trip_id"));
                            travelObject.addProperty("user_id", resultSet.getString("user_id"));
                            travelObject.addProperty("city", resultSet.getString("city"));
                            travelObject.addProperty("city_picture", resultSet.getString("city_picture"));
                            travelObject.addProperty("description", resultSet.getString("description"));
                            travelObject.addProperty("guest_id", resultSet.getString("guest_id"));
                            travelObject.addProperty("guest_status", resultSet.getString("guest_status"));
                            travelObject.addProperty("trip_status", resultSet.getString("trip_status"));
                            travelsArray.add(travelObject);
                        }

                        // Convert the JsonArray to a JSON formatted string and return to user
                        Gson gson = new Gson();
                        return gson.toJson(travelsArray);
                    }
                } catch (SQLException e) {
                    return "Error with getting travels: " + e.getMessage();
                }
            }
            return "Error with connection to the database";
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
            return "Error with connection: " + ex.getMessage();
        }
    }
}
