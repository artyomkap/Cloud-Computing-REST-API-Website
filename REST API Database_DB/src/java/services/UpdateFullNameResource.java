/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import external.JoinToTravel;
import external.UpdateFullNameService;
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
@Path("UpdateFullName")
public class UpdateFullNameResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UpdateFullNameResource
     */
    public UpdateFullNameResource() {
    }

    /**
     * Retrieves representation of an instance of services.UpdateFullNameResource
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String ChangeFullName(InputStream input) {        
        try (JsonReader reader = new JsonReader(new InputStreamReader(input))) {
            JsonObject jsonObject = new com.google.gson.JsonParser().parse(reader).getAsJsonObject();
            
            
            String User_Id = jsonObject.get("user_id").getAsString();
            String full_name = jsonObject.get("full_name").getAsString();
            
            
            UpdateFullNameService updatefname = new UpdateFullNameService();
            return updatefname.UpdateFullName(User_Id, full_name);

        } catch (Exception e) {            
            return "Error with JSON parsing: " + e.getMessage();
        }
    }
}
