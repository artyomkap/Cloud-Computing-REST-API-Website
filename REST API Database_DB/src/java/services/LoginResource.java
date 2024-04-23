/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import external.LoginService;
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
@Path("Login")
public class LoginResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LoginResource
     */
    public LoginResource() {
    }

//    /**
//     * Retrieves representation of an instance of external.LoginResource
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
//     * PUT method for updating or creating an instance of LoginResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    public void putXml(String content) {
//    }
//    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String loginUser(InputStream input) {        
        try (JsonReader reader = new JsonReader(new InputStreamReader(input))) {
            JsonObject jsonObject = new com.google.gson.JsonParser().parse(reader).getAsJsonObject();

            String userName = jsonObject.get("userName").getAsString();
            String password = jsonObject.get("password").getAsString();

            LoginService loginService = new LoginService();
            return loginService.LoginUser(userName, password);            

        } catch (Exception e) {            
            return "Error with JSON parsing: " + e.getMessage();
        }
    }
}
