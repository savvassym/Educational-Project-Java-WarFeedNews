package com.WarNewsFeed.wnf.Backend.API;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GeocodeApiTest {

    @Test
    public void getCoordinates() {
        String inputCountry="greece";
        Map<Double,Double> list;
        Map<Double,Double> expectedOutput = new HashMap<>();
        GeocodeApi geocodeApi = new GeocodeApi();
        list=geocodeApi.getCoordinates(inputCountry);
        expectedOutput.put(38.9953683,21.9877132);
        assertEquals(list,expectedOutput);
    }
    @Test
    public void getCoordinates2() {
        int i;
        List<String> inputCountry=new ArrayList<>();
        inputCountry.add("GREECE");
        inputCountry.add("Germany");
        inputCountry.add("Syria");
        inputCountry.add("Turkey");
        Map<Double,Double> list = new HashMap<>() ;
        Map<Double,Double> expectedOutput = new HashMap<>();
        expectedOutput.put(38.9953683,21.9877132);
        expectedOutput.put(51.0834196,10.4234469);
        expectedOutput.put(34.6401861,39.0494106);
        expectedOutput.put(38.9597594,34.9249653);
        GeocodeApi geocodeApi = new GeocodeApi();
        for(i=0;i<inputCountry.size();i++) {
            list = geocodeApi.getCoordinates(inputCountry.get(i));
        }
        assertEquals(list, expectedOutput);
    }
}