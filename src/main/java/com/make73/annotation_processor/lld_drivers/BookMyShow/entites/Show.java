package com.make73.annotation_processor.lld_drivers.BookMyShow.entites;

import com.make73.annotation_processor.lld_drivers.BookMyShow.exceptions.SeatsExhaustedException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Show {
    private int id;
    private Theatre theatre;
    private Movie movie;
    private LocalDateTime startTime;
    private List<Booking> bookings;

    public Show(int id, Theatre theatre, Movie movie, LocalDateTime startTime) {
        this.id = id;
        this.theatre = theatre;
        this.movie = movie;
        this.startTime = startTime;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) throws SeatsExhaustedException {
        // if remainingSeats < 1 throw error
        if (getRemainingSeats() < 1) {
            throw new SeatsExhaustedException("No seats available");
        }
        bookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    int getRemainingSeats() {
        return theatre.getSeats() - bookings.size();
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getId() {
        return id;
    }
}
