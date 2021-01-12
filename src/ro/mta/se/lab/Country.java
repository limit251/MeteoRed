package ro.mta.se.lab;

import java.util.ArrayList;
import java.util.List;

public class Country {

    private List<City> cityList;
    private String countryCode;

    public Country(String name){
        countryCode= name;
        cityList = new ArrayList<City>();
    }

    public void addCity(int id, String name){
        cityList.add(new City(id, name));
    }

    //getters
    public List<City> getCityList(){
        return cityList;
    }

    public String getCountryCode(){
        return countryCode;
    }

}
