package com.make73.annotation_processor.lld_drivers.BookMyShow;

import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.Booking;
import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.Show;
import com.make73.annotation_processor.lld_drivers.BookMyShow.entites.User;
import com.make73.annotation_processor.lld_drivers.BookMyShow.exceptions.SeatsExhaustedException;

public class BookingService {
    public static Booking createBooking(User user, Show show) throws SeatsExhaustedException {
        Booking booking = new Booking(show, user);
        user.addBooking(booking);
        show.addBooking(booking);

        return booking;
    }
}
