package com.WarNewsFeed.wnf.Backend.model;

import java.sql.Timestamp;

public class Tweet {
    private String tid;
    private String country;
    private Timestamp time_stamp;
    private String coordinates;

    public Tweet() {
    }

    public Tweet(String tid, String country, Timestamp time_stamp, String coordinates) {
        this.tid = tid;
        this.country = country;
        this.time_stamp = time_stamp;
        this.coordinates = coordinates;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Timestamp time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tid='" + tid + '\'' +
                ", country='" + country + '\'' +
                ", time_stamp='" + time_stamp + '\'' +
                ", coordinates='" + coordinates + '\'' +
                '}';
    }
}
