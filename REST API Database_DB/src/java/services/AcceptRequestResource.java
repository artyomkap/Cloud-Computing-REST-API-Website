/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import external.AcceptRequest;
import external.CancelRequest;
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
@Path("AcceptRequest")
public class AcceptRequestResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AcceptRequestResource
     */
    public AcceptRequestResource() {
    }

    /**
     * Retrieves representation of an instance of services.AcceptRequestResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("request_id") String request_id, @QueryParam("trip_id") String trip_id) {
        AcceptRequest acceptRequest = new AcceptRequest();
        return acceptRequest.AcceptRequestById(request_id, trip_id);
    }

    /**
     * PUT method for updating or creating an instance of AcceptRequestResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
