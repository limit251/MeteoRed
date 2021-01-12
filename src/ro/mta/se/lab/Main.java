package ro.mta.se.lab;

import org.json.JSONObject;

import java.util.List;

public class Main {

    public static void main(String[] args){
        System.out.println("Start");
        LocationManager locationManager  = LocationManager.getInstance();
        ApiRequest requester = new ApiRequest();

        FileReader fileReader = new FileReader("./src/resources/inFile");
        fileReader.fileScanner();
        List<Country> ctList = locationManager.getCountryList();
        for(Country ct: ctList){
            for(City city: ct.getCityList()){
                JSONObject jsonObject = requester.makeRequest(city.getCityId());
                System.out.println(jsonObject.toString(4));
            }
        }
    }

}
