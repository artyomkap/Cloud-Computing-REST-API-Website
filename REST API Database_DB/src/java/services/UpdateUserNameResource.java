/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import external.UpdateUserNameService;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
@Path("UpdateUserName")
public class UpdateUserNameResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UpdateUserNameResource
     */
    public UpdateUserNameResource() {
    }

    /**
     * Retrieves representation of an instance of services.UpdateUserNameResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UpdateUserNameResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String ChangeUserName(InputStream input) {        
        try (JsonReader reader = new JsonReader(new InputStreamReader(input))) {
            JsonObject jsonObject = new com.google.gson.JsonParser().parse(reader).getAsJsonObject();
            
            
            String User_Id = jsonObject.get("user_id").getAsString();
            String user_name = jsonObject.get("user_name").getAsString();
            
            
            UpdateUserNameService updateusername = new UpdateUserNameService();
            return updateusername.UpdateUsername(User_Id, user_name);

        } catch (Exception e) {            
            return "Error with JSON parsing: " + e.getMessage();
        }
    }
}
