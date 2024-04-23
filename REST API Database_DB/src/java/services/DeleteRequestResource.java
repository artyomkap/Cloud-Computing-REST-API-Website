/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import external.DeleteRequest;
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
@Path("DeleteRequest")
public class DeleteRequestResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DeleteRequestResource
     */
    public DeleteRequestResource() {
    }

    /**
     * Retrieves representation of an instance of services.DeleteRequestResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml(@QueryParam("request_id") String request_id, @QueryParam("trip_id") String trip_id) {
        DeleteRequest deleteRequest = new DeleteRequest();
        return deleteRequest.DeleteRequestById(request_id, trip_id);
    }

    /**
     * PUT method for updating or creating an instance of DeleteRequestResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
