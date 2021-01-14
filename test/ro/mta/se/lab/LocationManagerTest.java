package ro.mta.se.lab;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class LocationManagerTest {
    LocationManager locationManager = LocationManager.getInstance();

    @Mock
    Country country = mock(Country.class);
    City city = mock(City.class);

    @Spy
    private List<City> cityList = new ArrayList<City>();

    @Test
    void getCountryIndexBadString(){

      String badString= "badfFORMATED sTRING";
      assertFalse(locationManager.addCity(badString));
    }

    @Test
    void getCityIdMocked(){
        //voiam initial sa testez getCountryIndex, dar este metoda privată

//        Iterator<Country> itr = Mockito.mock(Iterator.class);
//        Mockito.when(itr.hasNext()).thenReturn(true, false);
//        Mockito.when(itr.next()).thenReturn(Mockito.any(Country.class));
//        List mockCountryList = Mockito.mock(List.class);
//        when(mockCountryList.iterator()).thenReturn(itr);
//
//        when(country.getCountryCode()).thenReturn("RO");
//
//        assertEquals(locationManager.getCountryIndex,0);

        int test = locationManager.getCityId("Wrongcountry", "wrongcity");
        assertEquals(test, 0);
    }


}