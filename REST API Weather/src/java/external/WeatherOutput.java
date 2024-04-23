/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package external;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author temka
 */
public class WeatherOutput {
    public Coord coord;
    public List<Weather> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    public Clouds clouds;
    public long dt;
    public Sys sys;
    public int timezone;
    public long id;
    public String name;
    public int cod;

    public static class Coord {
        public double lon;
        public double lat;
    }

    public static class Weather {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public static class Main {
        public double temp;
        @SerializedName("feels_like")
        public double feelsLike;
        @SerializedName("temp_min")
        public double tempMin;
        @SerializedName("temp_max")
        public double tempMax;
        public int pressure;
        public int humidity;
    }

    public static class Wind {
        public double speed;
        public int deg;
    }

    public static class Clouds {
        public int all;
    }

    public static class Sys {
        public int type;
        public int id;
        public String country;
        public long sunrise;
        public long sunset;
    }
    
    
    
    public String getWeatherCityName() {        
        return name;
    }    
    
    public String getWeatherDescription() {
    if (weather != null && !weather.isEmpty()) {
        return weather.get(0).description;
    } else {
        return "No weather data available";
    }
}
    
    public String getTemperatureAsString() {
        double temperatureInCelsius = main.temp - 273.15;        
        // Format the result as a string
        return String.format("%.2f °C", temperatureInCelsius);
    }


    public double getFeelsLikeTemperature() {
        return main.feelsLike;
    }

    public double getMinTemperature() {
        return main.tempMin;
    }

    public double getMaxTemperature() {
        return main.tempMax;
    }

    public int getPressure() {
        return main.pressure;
    }

    public int getHumidity() {
        return main.humidity;
    }

    public int getCloudiness() {
        return clouds.all;
    }

}
