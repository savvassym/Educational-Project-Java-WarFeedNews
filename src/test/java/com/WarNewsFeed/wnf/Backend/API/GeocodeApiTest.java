package com.WarNewsFeed.wnf.Backend.API;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GeocodeApiTest {

    @Test
    public void testGetCoordinates() {
        String inputCountry="greece";
        String list;
        String expectedOutput = "38.9953683  21.9877132";
        GeocodeApi geocodeApi = new GeocodeApi();
        list=geocodeApi.getCoordinates(inputCountry);
        assertEquals(list,expectedOutput);
    }
    @Test
    public void testGetCoordinatesForMultipleCountries() {
        int i;
        List<String> inputCountry=new ArrayList<>();
        inputCountry.add("GREECE");
        inputCountry.add("Germany");
        inputCountry.add("Syria");
        inputCountry.add("Turkey");
        List<String> list = new ArrayList<>() ;
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("38.9953683  21.9877132");
        expectedOutput.add("51.0834196  10.4234469");
        expectedOutput.add("34.6401861  39.0494106");
        expectedOutput.add("38.9597594  34.9249653");
        GeocodeApi geocodeApi = new GeocodeApi();
        for(i=0;i<inputCountry.size();i++) {
            list.add(i,geocodeApi.getCoordinates(inputCountry.get(i)));
        }
        assertEquals(list, expectedOutput);
    }
}