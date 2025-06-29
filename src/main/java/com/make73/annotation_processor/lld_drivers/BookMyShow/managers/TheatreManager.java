package com.make73.annotation_processor.lld_drivers.BookMyShow.managers;

import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.Theatre;

import java.util.ArrayList;
import java.util.List;

public class TheatreManager {
    private List<Theatre> theatres;

    public TheatreManager() {
        this.theatres = new ArrayList<>();
    }

    public void addTheatre(String name, int seats) {
        Theatre theatre = new Theatre(theatres.size(), name, seats);
        theatres.add(theatre);
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }

}
