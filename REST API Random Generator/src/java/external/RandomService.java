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
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author temka
 */
public class RandomService {
    private final String path = "http://www.randomnumberapi.com/api/v1.0/randomstring?";
    public RandomService() {}
    
    public String getRandomJSON() {
        String response = "";
        try {
            String request = String.format("min=%s&max=%s&count=%s", 5, 8, 1);
            URL url = new URL(path + request);
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
            Logger.getLogger(RandomService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    
    
    public String getRandomData() {
        String RandomValue = "";
        RandomService r = new RandomService();
        String randomJSON = r.getRandomJSON();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>(){}.getType();
        List<String> stringList = gson.fromJson(randomJSON, listType);

        for (String value : stringList) {
            RandomValue = value;
        }
        return RandomValue;
    }
}
