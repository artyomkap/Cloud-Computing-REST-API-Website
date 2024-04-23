/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import external.GetTripsById;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
@Path("GetTripsById")
public class GetTripsByIdResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetTripsByIdResource
     */
    public GetTripsByIdResource() {
    }

    /**
     * Retrieves representation of an instance of services.GetTripsByIdResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam ("trip_id") String trip_id) {
        GetTripsById getTrips = new GetTripsById();
        return getTrips.GetAllTripsByID(trip_id);
    }

    /**
     * PUT method for updating or creating an instance of GetTripsByIdResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
