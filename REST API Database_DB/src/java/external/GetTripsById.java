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
//Function for getting trips by their ID
public class GetTripsById {
    public GetTripsById() {}
    
    public String GetAllTripsByID(String trip_id) {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {
                //SQL Statement for getting trips by it's id
                String sql = "SELECT * FROM trips WHERE trip_id = ?";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, trip_id);
                    
                    try (ResultSet resultSet = statement.executeQuery()) {
                        JsonArray userArray = new JsonArray();

                        while (resultSet.next()) {
                            //Converting SQL columns to a JSON keys
                            JsonObject travelObject = new JsonObject();
                            travelObject.addProperty("trip_id", resultSet.getInt("trip_id"));
                            travelObject.addProperty("user_id", resultSet.getString("user_id"));
                            travelObject.addProperty("city", resultSet.getString("city"));
                            travelObject.addProperty("city_picture", resultSet.getString("city_picture"));
                            travelObject.addProperty("description", resultSet.getString("description"));
                            travelObject.addProperty("guest_id", resultSet.getString("guest_id"));
                            travelObject.addProperty("guest_status", resultSet.getString("guest_status"));
                            travelObject.addProperty("trip_status", resultSet.getString("trip_status"));
                            userArray.add(travelObject);
                        }

                        Gson gson = new Gson();
                        String jsonResult = gson.toJson(userArray);                   
                        System.out.println("User Data Result: " + jsonResult);

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
