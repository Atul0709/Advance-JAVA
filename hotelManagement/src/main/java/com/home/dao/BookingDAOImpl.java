package com.home.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.home.model.Booking;

public class BookingDAOImpl implements BookingDAO {
    // Database connection details

    private static final String SELECT_USER_BOOKINGS = "SELECT * FROM bookings WHERE user_id = ?";
    private static final String INSERT_BOOKING = "INSERT INTO bookings (user_id, hotel_id, check_in, check_out) VALUES (?, ?, ?, ?)";
    private static final String DELETE_BOOKING = "DELETE FROM bookings WHERE id = ?";

    @Override
    public List<Booking> getUserBookings(int userId) {
        List<Booking> bookings = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement statement = conn.prepareStatement(SELECT_USER_BOOKINGS)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setId(rs.getInt("id"));
                    booking.setUserId(rs.getInt("user_id"));
                    booking.setHotelId(rs.getInt("hotel_id"));
                    booking.setCheckIn(rs.getDate("check_in"));
                    booking.setCheckOut(rs.getDate("check_out"));
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

	@Override
	public void bookHotel(Booking booking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelBooking(int bookingId) {
		// TODO Auto-generated method stub
		
	}

    // Implement other methods such as bookHotel, cancelBooking
}
