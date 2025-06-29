package com.make73.annotation_processor.lld_drivers.BookMyShow.entites;

public class Booking {
    private Show show;
    private User user;

    public Booking(Show show, User user) {
        this.show = show;
        this.user = user;
    }

    public Show getShow() {
        return show;
    }
}
