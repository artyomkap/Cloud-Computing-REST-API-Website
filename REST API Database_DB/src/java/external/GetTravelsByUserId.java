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
//The function of for getting Trips and Requests by User Id
public class GetTravelsByUserId {
    public GetTravelsByUserId() {}
    
    public String getTravelsAndRequestsByUserId(String userId) {
        try (Connection connection = SqLiteConnection.connect()) {
            if (connection != null) {
                //SQL Statement for getting a trips by Id
                String travelSql = "SELECT * FROM trips WHERE user_id = ?";

                try (PreparedStatement travelStatement = connection.prepareStatement(travelSql)) {
                    travelStatement.setString(1, userId);

                    try (ResultSet travelResultSet = travelStatement.executeQuery()) {
                        JsonArray travelArray = new JsonArray();

                        while (travelResultSet.next()) {
                            JsonObject travelObject = new JsonObject();
                            travelObject.addProperty("trip_id", travelResultSet.getString("trip_id"));
                            travelObject.addProperty("city", travelResultSet.getString("city"));
                            travelObject.addProperty("description", travelResultSet.getString("description"));
                            travelObject.addProperty("trip_status", travelResultSet.getString("trip_status"));

                            travelArray.add(travelObject);
                        }

                        //Sql statement for getting Trip requests by User_Id
                        String requestSql = "SELECT * FROM trip_requests WHERE trip_id IN (SELECT trip_id FROM trips WHERE user_id = ?)";

                        try (PreparedStatement requestStatement = connection.prepareStatement(requestSql)) {
                            requestStatement.setString(1, userId);

                            try (ResultSet requestResultSet = requestStatement.executeQuery()) {
                                JsonArray requestArray = new JsonArray();

                                while (requestResultSet.next()) {
                                    JsonObject requestObject = new JsonObject();
                                    requestObject.addProperty("request_id", requestResultSet.getString("request_id"));
                                    requestObject.addProperty("trip_id", requestResultSet.getString("trip_id"));
                                    requestObject.addProperty("user_id", requestResultSet.getString("user_id"));
                                    requestObject.addProperty("status", requestResultSet.getString("status"));

                                    requestArray.add(requestObject);
                                }

                                // Combining data from first and second SQL Statement
                                Gson gson = new Gson();
                                JsonObject resultObject = new JsonObject();
                                resultObject.add("travels", travelArray);
                                resultObject.add("requests", requestArray);
                                String jsonResult = gson.toJson(resultObject);                           
                             return jsonResult;
                            }
                        }
                    }
                } catch (SQLException e) {                    
                    e.printStackTrace();
                    return "Error with retrieving data: " + e.getMessage();
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
