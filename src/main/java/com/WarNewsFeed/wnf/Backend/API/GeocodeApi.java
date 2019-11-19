package com.WarNewsFeed.wnf.Backend.API;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageResult;

import java.util.HashMap;
import java.util.Map;

public class GeocodeApi {
    JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("ba7f58a94c8c4756abac224678b23694");
    private Double latitude,longitude;
    private Map<Double,Double> coordinates = new HashMap<>();

    public Map<Double,Double> getCoordinates(String country)
    {
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(country);
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageResult x =response.getResults().get(0);
        longitude=x.getGeometry().getLng();
        latitude=x.getGeometry().getLat();
        coordinates.put(latitude,longitude);
        return coordinates;
    }

}
