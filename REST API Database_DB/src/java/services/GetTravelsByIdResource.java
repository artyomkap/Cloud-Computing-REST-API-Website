/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import external.GetTravelsByUserId;
import external.GetUser;
import external.JoinToTravel;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author T0322864
 */
@Path("GetTravelsById")
public class GetTravelsByIdResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetTravelsByIdResource
     */
    public GetTravelsByIdResource() {
    }

    /**
     * Retrieves representation of an instance of services.GetTravelsByIdResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("user_id") String user_id) {        
        GetTravelsByUserId getTravel = new GetTravelsByUserId();
        return getTravel.getTravelsAndRequestsByUserId(user_id);
    }

    /**
     * PUT method for updating or creating an instance of GetTravelsByIdResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    
}
