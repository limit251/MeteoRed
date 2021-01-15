package ro.mta.se.lab;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequest {
    private static final String USER_AGENT = "MeteoRed";
    private static final String apiKey = "92f46bc987b40af941d75f1dc23373fb";
    private static final String getRequest = "https://api.openweathermap.org/data/2.5/weather?";
    private HttpURLConnection con;
    private int responseCode;
    URL url;

    public JSONObject makeRequest(int cityId){
        JSONObject jsonObject;
        String finalRequest;
        StringBuffer response;

        finalRequest = getRequest + "id=" + cityId + "&appid=" + apiKey;

        try {

            url = new URL(finalRequest);
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            responseCode = con.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                jsonObject = new JSONObject(response.toString());
                return jsonObject;
            }
            else{
                AlertClass alertClass = new AlertClass(1, "Eroare", "Pagină Indisponibilă");
                alertClass.showAlert();
            }
        }
        catch (IOException e){
            AlertClass alertClass = new AlertClass(1, "Eroare", "Eroare detectată");
            alertClass.showAlert();
        }
        return null;
    }

    public WeatherForecast jsonParser(JSONObject jsonObject){
        String description;
        float temperature, windSpeed;
        int humidity;

        WeatherForecast weatherForecast = new WeatherForecast();

        temperature = jsonObject.getJSONObject("main").getFloat("temp");
        windSpeed = jsonObject.getJSONObject("wind").getFloat("speed");
        humidity = jsonObject.getJSONObject("main").getInt("humidity");
        description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

        weatherForecast.setDesc(description);
        weatherForecast.setHumidity(humidity);
        weatherForecast.setTemp(temperature);
        weatherForecast.setWindSpeed(windSpeed);

        return weatherForecast;
    }
}
