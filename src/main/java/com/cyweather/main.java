package com.cyweather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource("/weather.fxml")
        );

        Scene scene = new Scene(loader.load(), 500, 400);

        scene.getStylesheets()
                .add(getClass().getResource("/style.css").toExternalForm());

        stage.setTitle("CYWeather - Cyprus Weather");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}