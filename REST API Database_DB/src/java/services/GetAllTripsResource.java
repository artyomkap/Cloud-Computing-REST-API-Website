/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import external.GetAllTravels;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author T0322864
 */
@Path("GetAllTrips")
public class GetAllTripsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetAllTripsResource
     */
    public GetAllTripsResource() {
    }

    /**
     * Retrieves representation of an instance of services.GetAllTripsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        try {
            GetAllTravels getAllTravels = new GetAllTravels();
            String AllTrips = getAllTravels.GetAllTrips();
            return AllTrips;
        } catch (Exception e) {
            return "{\"error\": \"An error occurred while processing the request\"}" + e;
        }
    }

    /**
     * PUT method for updating or creating an instance of GetAllTripsResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
