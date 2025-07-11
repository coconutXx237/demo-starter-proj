package ru.study.demoapp.model;

public class CityCount {

    private String city;
    private long count;

    public CityCount(String city, long count) {
        this.city = city;
        this.count = count;
    }

    public String getCity() {
        return city;
    }

    public long getCount() {
        return count;
    }
}
