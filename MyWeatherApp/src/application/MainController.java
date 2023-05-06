package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.net.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;

public class MainController {

    public MainController() {
    	
	}
    
    @FXML
    private Button buttData;
    
	@FXML
    private Text currDate;

    @FXML
    private Text currDescr;

    @FXML
    private Text currLoc;

    @FXML
    private Text currWeath;

    @FXML
    private ImageView currWeathSymbol;

    @FXML
    private Text feelsLike;

    @FXML
    private Text forcW1;

    @FXML
    private Text forcW2;

    @FXML
    private Text forcW3;

    @FXML
    private ImageView forcastImag1;

    @FXML
    private ImageView forcastImag2;

    @FXML
    private ImageView forcastImag3;

    @FXML
    private Text humidityCurr;

    @FXML
    private Text pressCurr;

    @FXML
    private Text uvCURR;

    @FXML
    private Text windcurr;
    
    @FXML
    private Text cloudCov;
    
    @FXML
    private ImageView backgroundCond;

    @FXML
    void choiceButton(MouseEvent event) throws JSONException, IOException {
    	
    	try {
			APIConnection();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	
    	setAPIValues();
    
    	double tempNow = this.current.getDouble("temp_c");
        int HumidityNow = this.current.getInt("humidity");
        double feelslke = this.current.getDouble("feelslike_c");
        double windNow =  this.current.getDouble("wind_kph");
        double pressureNow = this.current.getDouble("pressure_mb");
        double uvNow = this.current.getDouble("uv");
        int is_day = this.current.getInt("is_day");
        int cloudCovNow = this.current.getInt("cloud");
        String weCondNow = this.condition.getString("text");
        String iconURL ="http:" + this.condition.getString("icon");
        String currDateTime = this.locationapi.getString("localtime");
       
        humidityCurr.setText("Humidity: "+ HumidityNow);
        currWeath.setText(tempNow+ "°c");
        windcurr.setText("Wind: " +windNow + " kph");
        pressCurr.setText("Pressure: " +pressureNow + " mb");
        uvCURR.setText("UV: " + uvNow);
        cloudCov.setText("Cloud Coverage: " + cloudCovNow + "%");
        feelsLike.setText("Feels like: "+ feelslke+"°c");
        currDate.setText(currDateTime);
        currDescr.setText(weCondNow);
       
		URL url = new URL(iconURL);
		URLConnection conn = url.openConnection();
		InputStream stream = conn.getInputStream();
		Image icon = new Image(stream);
		
        if(is_day == 1 )
        {
        	currWeathSymbol.setImage(icon);
        	
        }
        else if(is_day == 0)
        {
        	currWeathSymbol.setImage(icon);
        	
        }
    }
   
    
    //- - - - - -- - - - - - -- - - - - - - - - - - - - - - - - -
    //API 
    private String response;
	private String apiKey = "f3202cc89fd546e796c223415232604";  // Replace with your API key
    private String location = "Nicosia";   // Replace with your location
    private String apiUrlCURRENT = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + location;
    private String apiUrlForecast ="http://api.weatherapi.com/v1/forecast.json?key=" + apiKey + "&q=" + location + "&days=3&aqi=yes&alerts=no";
    
    private JSONObject jsonResponse;
    private JSONObject forecast;
    private  JSONArray forecastday;
    private JSONObject condition;
    private JSONObject locationapi;
    private JSONObject current;
    private JSONObject day;
    private JSONObject date;
    
    
    public void setAPIValues() throws JSONException {
    	this.jsonResponse= new JSONObject(response);
    	this.current = jsonResponse.getJSONObject("current");
    	this.locationapi = jsonResponse.getJSONObject("location");
    	this.condition = current.getJSONObject("condition");
    	this.forecast = jsonResponse.getJSONObject("forecast");
    	this.forecastday = forecast.getJSONArray("forecastday");
    	this.day = forecastday.getJSONObject(0).getJSONObject("day");
    	this.date = forecastday.getJSONObject(0);
    }
    
    public void APIConnection() throws IOException {
    	URL objectInput = new URL(apiUrlForecast);
        HttpURLConnection connection = (HttpURLConnection) objectInput.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder Response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
                Response.append(inputLine);
            }

        in.close();
        this.response=Response.toString();
    }
}
