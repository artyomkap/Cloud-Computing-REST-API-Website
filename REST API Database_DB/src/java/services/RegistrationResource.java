/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import external.RegistrationService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author T0322864
 */
@Path("Registration")
public class RegistrationResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RegistrationResource
     */
    public RegistrationResource() {
    }

    /**
     * Retrieves representation of an instance of services.RegistrationResource
     * @return an instance of java.lang.String
     */
//    @GET
//    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
//    public String getJson() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     * PUT method for updating or creating an instance of RegistrationResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
//    public void putJson(String content) {
//    }
    
      /**
     * POST method for user registration
     *
     * @param fullName User's full name
     * @param userName User's username
     * @param password User's password
     * @return a String containing the result of the registration
     */
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String registerUser(InputStream input) {        
        try (JsonReader reader = new JsonReader(new InputStreamReader(input))) {
            JsonObject jsonObject = new com.google.gson.JsonParser().parse(reader).getAsJsonObject();
            
            String fullName = jsonObject.get("fullName").getAsString();
            String userName = jsonObject.get("userName").getAsString();
            String password = jsonObject.get("password").getAsString();
            String id = "";
            URL url;

            try {
                url = new URL("http://localhost:8080/REST_API_Random_Generator/webresources/Random");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                connection.setRequestMethod("GET");

                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line = in.readLine();
                    while (line != null) {
                        id += line + "\r\n";
                        line = in.readLine();
                    }
                    id = id.trim().replaceAll("\\s", "");
                }
                connection.disconnect();
            } catch (IOException ex) {
                Logger.getLogger(RegistrationResource.class.getName()).log(Level.SEVERE, null, ex);
                return "Error with GET request: " + ex.getMessage();
            }

            RegistrationService registrationService = new RegistrationService();
            return registrationService.RegistrateUser(id, fullName, userName, password);
        
        } catch (Exception e) {
            e.printStackTrace();
            return "Error with JSON parsing: " + e.getMessage();
        }
        
    }
    
    
}
