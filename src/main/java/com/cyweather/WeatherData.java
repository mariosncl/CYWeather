package com.cyweather;


public class WeatherData {

    private double temperature;
    private String description;
    private String city;


    public WeatherData(
            double temperature,
            String description,
            String city
    ){
        this.temperature = temperature;
        this.description = description;
        this.city = city;
    }


    public double getTemperature(){
        return temperature;
    }


    public String getDescription(){
        return description;
    }


    public String getCity(){
        return city;
    }

}