package com.make73.annotation_processor.lld_drivers.BookMyShow.managers;

import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.Movie;
import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.Show;
import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.Theatre;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GlobalShowManager {
    private static List<Show> shows = new ArrayList<>();
    ;


    public static Show addShow(Theatre theatre, Movie movie, LocalDateTime startTime) {
        Show show = new Show(shows.size(), theatre, movie, startTime);
        shows.add(show);
        return show;
    }


    public static Show getShow(int showId) {
        return shows.get(showId);
    }
}
