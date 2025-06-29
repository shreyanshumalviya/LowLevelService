package com.make73.annotation_processor.lld_drivers.BookMyShow.entites;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private List<Booking> bookings;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}
