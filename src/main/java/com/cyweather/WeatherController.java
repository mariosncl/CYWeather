package com.cyweather;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class WeatherController {


    @FXML
    private TextField cityField;


    @FXML
    private Label resultLabel;



    private WeatherService service =
            new WeatherService();



    @FXML
    public void searchWeather(){


        try {

            String city =
                    cityField.getText();


            WeatherData data =
                    service.getWeather(city);


            resultLabel.setText(
                    "City: "
                    + data.getCity()
                    + "\nTemperature: "
                    + data.getTemperature()
                    + " °C"
                    + "\nCondition: "
                    + data.getDescription()
            );


        }
        catch(Exception e){

            resultLabel.setText(
                    "Unable to retrieve weather data"
            );

        }

    }

}