package com.make73.annotation_processor.lld_drivers.BookMyShow.entites;

import com.make73.annotation_processor.lld_drivers.BookMyShow.managers.GlobalShowManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private int id;
    private String name;
    private int seats;

    private List<Show> shows;

    //constructor
    public Theatre(int id, String name, int seats) {
        this.id = id;
        this.name = name;
        this.seats = seats;
        this.shows = new ArrayList<>();
    }

    protected int getSeats() {
        return seats;
    }

    public String getName() {
        return this.name;
    }

    public void addShow(Movie movie, LocalDateTime startTime) {
        Show show = GlobalShowManager.addShow(this, movie, startTime);
        this.shows.add(show);
    }

    // id getter
    public int getId() {
        return id;
    }

    public List<Show> getShows() {
        return shows;
    }
}
