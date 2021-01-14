package ro.mta.se.lab;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.json.JSONObject;

import java.io.File;
import java.util.logging.Logger;

public class WeatherController {

    LocationManager locationManager;
    HistoryLogger historyLogger;
    ApiRequest apiRequest;
    FileReader fileReader;

    ObservableList<Country> countryObservableList;
    ObservableList<String> countryNames;

    @FXML
    private ComboBox cityComboBox, countryComboBox;

    @FXML
    private Label  cityName, temperature, wind, humidity, description,windDesc, humDesc, muHumidity, muTemp, muWind;

    @FXML
    private void initialize(){
        locationManager = LocationManager.getInstance();
        historyLogger = HistoryLogger.getInstance();
        apiRequest = new ApiRequest();
        fileReader = new FileReader("./src/resources/inFile");
        hide();

        if(!fileReader.fileScanner()){
            return;
        };

        countryObservableList = FXCollections.observableList(locationManager.getCountryList());

        for(Country country: countryObservableList){
            countryComboBox.getItems().add(country.getCountryCode());
        }

        countryComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String country;
                System.out.println("Selected country: " + countryComboBox.getValue());
                cityComboBox.getSelectionModel().clearSelection();
                cityComboBox.getItems().clear();
                loadCities(countryComboBox.getValue());
            }
        });

        cityComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String country, city;
                int cityid;
                JSONObject jsonObject;
                WeatherForecast weatherForecast;

                country = countryComboBox.getValue().toString();

                if(cityComboBox.getValue() != null) {
                    city = cityComboBox.getValue().toString();

                    cityid = locationManager.getCityId(country, city);
                    jsonObject = apiRequest.makeRequest(cityid);
                    weatherForecast = apiRequest.jsonParser(jsonObject);

                    historyLogger.log(city, country, weatherForecast);
                    showWeather(weatherForecast, city);
                    show();
                }
            }
        });


    }

    private void loadCities(Object value){
        String countryName = value.toString();
        ObservableList<City> cities;

        for(Country country: countryObservableList){
            if(country.getCountryCode().equals(countryName)){
                cities = FXCollections.observableList(country.getCityList());
                for(City city: cities){
                    cityComboBox.getItems().add(city.getCityName());
                }
            }
        }

    }

    private void showWeather(WeatherForecast weatherForecast, String city){

        cityName.setText(city);
        temperature.setText(String.valueOf(Math.round(convertKtoC(weatherForecast.getTemp()))));
        humidity.setText(String.valueOf(weatherForecast.getHumidity()));
        wind.setText(String.valueOf(weatherForecast.getWindSpeed()));
        description.setText(String.valueOf(weatherForecast.getDesc()));
    }

    private float convertKtoC(float temp){
        return (float) (temp - 272.15);
    }

    private void hide(){
        cityName.setVisible(false);
        temperature.setVisible(false);
        wind.setVisible(false);
        humidity.setVisible(false);
        description.setVisible(false);
        muTemp.setVisible(false);
        muHumidity.setVisible(false);
        muWind.setVisible(false);
        windDesc.setVisible(false);
        humDesc.setVisible(false);

    }

    private void show(){
        cityName.setVisible(true);
        temperature.setVisible(true);
        wind.setVisible(true);
        humidity.setVisible(true);
        description.setVisible(true);
        muHumidity.setVisible(true);
        muWind.setVisible(true);
        muTemp.setVisible(true);
        windDesc.setVisible(true);
        humDesc.setVisible(true);

    }
}
