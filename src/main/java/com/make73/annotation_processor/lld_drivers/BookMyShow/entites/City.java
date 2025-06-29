package com.make73.annotation_processor.lld_drivers.BookMyShow.entites;

import com.make73.annotation_processor.lld_drivers.BookMyShow.managers.TheatreManager;

import java.util.List;

public class City {
    private int id;
    private String name;

    private TheatreManager theatreManager;

    public City(int id, String name) {
        this.id = id;
        this.name = name;
        this.theatreManager = new TheatreManager();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addTheatre(String name, int seats) {
        theatreManager.addTheatre(name, seats);
    }

    public Theatre getTheatre(int theatreId) {
        return theatreManager.getTheatres().get(theatreId);
    }

    public List<Theatre> getTheatres() {
        return theatreManager.getTheatres();
    }
}
