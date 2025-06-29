package com.make73.annotation_processor.lld_drivers.BookMyShow.dtos;

import java.time.LocalDateTime;

public class ShowDTO {
    private int id;
    private String theatreName;
    private String movieName;
    private LocalDateTime startTime;

    public ShowDTO(int id, String theatreName, String movieName, LocalDateTime startTime) {
        this.id = id;
        this.theatreName = theatreName;
        this.movieName = movieName;
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public String getMovieName() {
        return movieName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

}
