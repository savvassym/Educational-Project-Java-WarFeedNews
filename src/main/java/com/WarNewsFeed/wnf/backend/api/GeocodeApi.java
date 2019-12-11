package com.WarNewsFeed.wnf.backend.api;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageResult;

public class GeocodeApi {
    JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("ba7f58a94c8c4756abac224678b23694");
    private Double latitude,longitude;
    String coordinates;

    public String getCoordinates(String country)
    {
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(country);
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        try {
            JOpenCageResult x =response.getResults().get(0);
            longitude=x.getGeometry().getLng();
            latitude=x.getGeometry().getLat();
            coordinates = latitude+" "+" "+longitude.toString();
        }
        catch (Exception e)
        {
            System.out.println("Not found Sth went wrong");
        }
        return coordinates;
    }

}

