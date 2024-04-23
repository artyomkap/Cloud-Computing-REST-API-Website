/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import external.Weather;
import external.WeatherOutput;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.QueryParam;
/**
 * REST Web Service
 *
 * @author temka
 */
@Path("Weather")
public class WeatherResource {

    @Context
    private UriInfo context;
    private Double lat;
    private Double lon;
    /**
     * Creates a new instance of WeatherResource
     */
    public WeatherResource() {
    }

    /**
     * Retrieves representation of an instance of services.WeatherResource
     * @param city
     * @return an instance of java.lang.String
     * @throws java.net.MalformedURLException
     */
    @GET
@Produces(MediaType.APPLICATION_JSON)
public String getJson(@QueryParam("city") String city) {
    try {
        if (city != null && !city.isEmpty()) {
            String geocodingRequest = "https://maps.googleapis.com/maps/api/geocode/json?address=" + city + "&key=AIzaSyBEliLgk7DAYXkZCVMbXZfq-bp45ropDao";
            URL geocodingUrl = new URL(geocodingRequest);

            HttpURLConnection geocodingConnection = (HttpURLConnection) geocodingUrl.openConnection();
            geocodingConnection.setRequestMethod("GET");

            BufferedReader geocodingReader = new BufferedReader(new InputStreamReader(geocodingConnection.getInputStream()));
            StringBuilder geocodingResponse = new StringBuilder();
            String geocodingLine;

            while ((geocodingLine = geocodingReader.readLine()) != null) {
                geocodingResponse.append(geocodingLine);
            }

            geocodingReader.close();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject geocodingJson = gson.fromJson(geocodingResponse.toString(), JsonObject.class);

            JsonArray results = geocodingJson.getAsJsonArray("results");
            if (results.size() > 0) {
                JsonObject location = results.get(0).getAsJsonObject().getAsJsonObject("geometry").getAsJsonObject("location");
                lat = location.get("lat").getAsDouble();
                lon = location.get("lng").getAsDouble();

                // Now, you have lat and lon, proceed with the weather API call
                if (lat != null && lon != null) {
                    Weather w = new Weather();
                    WeatherOutput newOutput = w.getWeatherData(lat, lon);
                    return new Gson().toJson(newOutput);
                } else {
                    // Handle the case where lat or lon is not provided
                    return "{\"error\": \"Latitude (lat) and Longitude (lon) are required parameters\"}";
                }
            } else {
                return "{\"error\": \"No results found for the provided city\"}";
            }
        } else {
            return "{\"error\": \"City parameter is required\"}";
        }
    } catch (Exception e) {
        // Log the exception for debugging purposes
        e.printStackTrace();
        return "{\"error\": \"An error occurred while processing the request\"}";
    }
}
}

