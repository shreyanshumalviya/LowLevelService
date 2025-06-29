package com.make73.annotation_processor.lld_drivers.BookMyShow.managers;

import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.City;

import java.util.ArrayList;
import java.util.List;

public class CityManager {
    private List<City> cities;

    public CityManager() {
        cities = new ArrayList<>();
    }

    public void addCity(String name) {
        City city = new City(cities.size(), name);
        cities.add(city);
    }

    // list cities
    public List<City> getCities() {
        return cities;
    }

    public City getCity(int id) {
        return cities.get(id);
    }


}
