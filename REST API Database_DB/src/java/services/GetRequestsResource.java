/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import external.GetRequests;
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
@Path("GetRequests")
public class GetRequestsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetRequestsResource
     */
    public GetRequestsResource() {
    }

    /**
     * Retrieves representation of an instance of services.GetRequestsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam ("user_id")  String user_id) {
        GetRequests getRequests = new GetRequests();
        return getRequests.GetAllRequestsByID(user_id);
    }

    /**
     * PUT method for updating or creating an instance of GetRequestsResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
