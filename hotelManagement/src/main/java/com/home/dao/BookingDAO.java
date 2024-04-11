package com.home.dao;

import java.util.List;
import com.home.model.Booking;

public interface BookingDAO {
    List<Booking> getUserBookings(int userId);
    void bookHotel(Booking booking);
    void cancelBooking(int bookingId);
}
