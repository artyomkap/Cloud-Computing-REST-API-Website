/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package external;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author temka
 */
public class Weather {
    private final String path = "https://api.openweathermap.org/data/2.5/weather?";
    private final String path2 = "&appid=94e628e49e7f1f03a385d72b5f3d381b";
    private final String apiKey = "94e628e49e7f1f03a385d72b5f3d381b";
    public Weather() {}
    
    public String getWeatherJSON(Double lat, Double lon) {
        String response = "";   
        try
        {
            String request = String.format("lat=%s&lon=%s", lat, lon);
            
            URL url = new URL(path + request + path2);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestMethod("GET");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = in.readLine();
                while ( line!= null) 
                {
                    response += line + "\r\n";
                    line = in.readLine();
                }
        } catch (IOException ex) {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
        
    }
    
    public WeatherOutput getWeatherData(Double lat, Double lon) {
        String WeatherJSON = this.getWeatherJSON(lat, lon);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();             
        WeatherOutput w = gson.fromJson(WeatherJSON, WeatherOutput.class);
        
        return w;
    }
    
    
//    public static void main(String args[]) {
//        Weather w = new Weather();    
//        WeatherOutput newOutput = w.getWeatherData("52.950001", "-1.150000");
//        System.out.println("Temperature: " + newOutput.getTemperature());
//        System.out.println("Clouds: " + newOutput.getCloudiness());
//        System.out.println("Weather description: " + newOutput.getWeatherDescription());
//    }
}
