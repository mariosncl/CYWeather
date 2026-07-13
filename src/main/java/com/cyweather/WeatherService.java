package com.cyweather;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WeatherService {


    private final String API_KEY =
            "YOUR_OPENWEATHER_API_KEY";


    public WeatherData getWeather(String city)
            throws Exception {


        String urlString =
                "https://api.openweathermap.org/data/2.5/weather?q="
                + city +
                ",CY&appid="
                + API_KEY +
                "&units=metric";


        URL url = new URL(urlString);

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();


        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()
                        )
                );


        StringBuilder response =
                new StringBuilder();


        String line;


        while((line = reader.readLine()) != null){
            response.append(line);
        }


        JSONObject json =
                new JSONObject(response.toString());


        double temperature =
                json.getJSONObject("main")
                        .getDouble("temp");


        String description =
                json.getJSONArray("weather")
                        .getJSONObject(0)
                        .getString("description");


        return new WeatherData(
                temperature,
                description,
                city
        );

    }

}