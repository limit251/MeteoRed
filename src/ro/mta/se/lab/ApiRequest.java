package ro.mta.se.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;

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
                System.out.println("Eroaree");
            }
        }
        catch (IOException e){
            System.out.println("Exceptioon");
        }
        return null;
    }


}
