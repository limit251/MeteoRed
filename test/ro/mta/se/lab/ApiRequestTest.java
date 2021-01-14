package ro.mta.se.lab;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ApiRequestTest {

    ApiRequest apiRequest = new ApiRequest();

    @Mock
    LocationManager locationManager = mock(LocationManager.class);


    @Test
    void makeRequestExistingCity() {
        when(locationManager.getCityId("RO","Bucharest")).thenReturn(683506);
        int cityId;
        float lat, lon;

        JSONObject jsonObject;

        cityId = locationManager.getCityId("RO", "Bucharest");
        jsonObject = apiRequest.makeRequest(cityId);

        lat =  jsonObject.getJSONObject("coord").getFloat("lat");
        lon = jsonObject.getJSONObject("coord").getFloat("lon");

        assertEquals(lon, 26.1063, 0.0002);
        assertEquals(lat, 44.4323, 0.0002);
    }


    @Test
    void jsonParser() {
        WeatherForecast weatherForecast;
        JSONObject jsonObject = getJson();

        weatherForecast = apiRequest.jsonParser(jsonObject);

        assertEquals(weatherForecast.getTemp(),282.54, 0.002);
        assertEquals(weatherForecast.getHumidity(), 93);
        assertEquals(weatherForecast.getWindSpeed(), 1.5);
        assertEquals(weatherForecast.getDesc(), "broken clouds");

    }

    private JSONObject getJson(){
        String json = "{\n" +
                "   \"weather\":[\n" +
                "      {\n" +
                "         \"id\":803,\n" +
                "         \"main\":\"Clouds\",\n" +
                "         \"description\":\"broken clouds\",\n" +
                "         \"icon\":\"04n\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"base\":\"stations\",\n" +
                "   \"main\":{\n" +
                "      \"temp\":282.54,\n" +
                "      \"humidity\":93\n" +
                "   },\n" +
                "   \"wind\":{\n" +
                "      \"speed\":1.5,\n" +
                "      \"deg\":50\n" +
                "   },\n" +
                "}";

        return new JSONObject(json);
    }

    @Test
    void makeRequestWrongId(){
        JSONObject jsonObject;

        jsonObject = apiRequest.makeRequest(-2);

        assertNull(jsonObject);
    }

}