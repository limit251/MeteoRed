package ro.mta.se.lab;

public class City {

    private final String cityName;
    private final int cityId;

    public City(int id, String name){
        cityName = name;
        cityId = id;
    }

    //getters
    public String getCityName(){
        return cityName;
    }

    public int getCityId(){
        return cityId;
    }
}
