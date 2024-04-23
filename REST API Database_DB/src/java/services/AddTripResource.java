/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import external.CreateTravel;
import external.LoginService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author T0322864
 */
@Path("AddTrip")
public class AddTripResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddTripResource
     */
    public AddTripResource() {
    }

//    /**
//     * Retrieves representation of an instance of services.AddTripResource
//     * @return an instance of java.lang.String
//     */
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public String getXml() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     * PUT method for updating or creating an instance of AddTripResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    public void putXml(String content) {
//    }
//    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String AddTrip(InputStream input) {        
        try (JsonReader reader = new JsonReader(new InputStreamReader(input))) {
            JsonObject jsonObject = new com.google.gson.JsonParser().parse(reader).getAsJsonObject();            
            
            String User_Id = jsonObject.get("User_Id").getAsString();
            String City = jsonObject.get("City").getAsString();
            String Description = jsonObject.get("Description").getAsString();
            String City_Picture = getCityPicture(City);
            
            CreateTravel createTravel = new CreateTravel();
            return createTravel.CreateTrip(User_Id, City, City_Picture, Description);
          

        } catch (Exception e) {            
            return "Error with JSON parsing: " + e.getMessage();
        }
    }
    
    private String getCityPicture(String city) {
        try {
            String apiKey = "AIzaSyBEliLgk7DAYXkZCVMbXZfq-bp45ropDao";
            String cx = "a56428ed5740441cc";
            String searchQuery = city;

            String apiUrl = "https://www.googleapis.com/customsearch/v1?key=" + apiKey + "&num=1&cx=" + cx + "&searchType=image&q=" + searchQuery;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                connection.disconnect();

                // Parse the JSON response to extract City_Picture
                JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
                JsonArray items = jsonResponse.getAsJsonArray("items");

                if (items != null && items.size() > 0) {
                    JsonObject firstItem = items.get(0).getAsJsonObject();
                    String link = firstItem.get("link").getAsString();
                    return link;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
