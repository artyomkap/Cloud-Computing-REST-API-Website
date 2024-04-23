/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import external.RandomService;
/**
 * REST Web Service
 *
 * @author temka
 */
@Path("Random")
public class RandomResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RandomResource
     */
    public RandomResource() {
    }

    /**
     * Retrieves representation of an instance of services.RandomResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getJson() {
        try {
            RandomService randomService = new RandomService();
            String randomData = randomService.getRandomData();
            return randomData;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"An error occurred while processing the request\"}";
        }
    }

    /**
     * PUT method for updating or creating an instance of RandomResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
