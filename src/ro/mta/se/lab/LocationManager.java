package ro.mta.se.lab;

import java.util.ArrayList;
import java.util.List;

public class LocationManager {
    private List<Country> countryList;
    private static LocationManager single_instance = null;

    private LocationManager(){
        countryList = new ArrayList<Country>();
    }

    public static LocationManager getInstance(){
        if(single_instance == null){
            single_instance = new LocationManager();
        }
        return single_instance;
    }

    public List<Country> getCountryList(){
        return countryList;
    }

    public boolean addCity(String fileLine){

            String[] splitted = null;
            Country currentCountry;
            int index = -2;

            try {
                splitted = fileLine.split(" ");
                index = getCountryIndex(splitted[4]);
            } catch (Exception e) {
                System.out.println("Wrong formatted file");
                return false;
            }
            if (index == -1) {
                currentCountry = new Country(splitted[4]);
                countryList.add(currentCountry);
            } else {
                currentCountry = countryList.get(index);
            }

            currentCountry.addCity(Integer.parseInt(splitted[0].trim()), splitted[1]);


        return true;
    }

    private int getCountryIndex(String countryCode){
        int index = 0;

        for(Country country: countryList){
            if(country.getCountryCode().equals(countryCode)){
                return index;
            }
            index++;
        }

        return -1;
    }

    public int getCityId(String country, String city){

        for(Country ct: countryList){
            if(ct.getCountryCode().equals(country)){
                for(City cit: ct.getCityList()){
                    if(cit.getCityName().equals(city)){
                        return cit.getCityId();
                    }
                }
            }
        }
        return 0;
    }
}
