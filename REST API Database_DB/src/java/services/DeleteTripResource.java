/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import external.DeleteTrip;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author T0322864
 */
@Path("DeleteTrip")
public class DeleteTripResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DeleteTripResource
     */
    public DeleteTripResource() {
    }

    /**
     * Retrieves representation of an instance of services.DeleteTripResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("trip_id") String trip_id) {
        //TODO return proper representation object
        DeleteTrip deleteTrip = new DeleteTrip();
        return deleteTrip.DeleteTripById(trip_id);        
    }

    /**
     * PUT method for updating or creating an instance of DeleteTripResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
