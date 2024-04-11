package com.home.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.home.model.Hotel;

public class HotelDAOImpl implements HotelDAO {
    // Database connection details

    private static final String SELECT_ALL_HOTELS = "SELECT * FROM hotels";
    private static final String SELECT_HOTEL_BY_ID = "SELECT * FROM hotels WHERE id = ?";
    private static final String INSERT_HOTEL = "INSERT INTO hotels (name, location, price) VALUES (?, ?, ?)";
    private static final String UPDATE_HOTEL = "UPDATE hotels SET name = ?, location = ?, price = ? WHERE id = ?";
    private static final String DELETE_HOTEL = "DELETE FROM hotels WHERE id = ?";

    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement statement = conn.prepareStatement(SELECT_ALL_HOTELS);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setLocation(rs.getString("location"));
                hotel.setPrice(rs.getDouble("price"));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

	@Override
	public Hotel getHotelById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteHotel(int id) {
		// TODO Auto-generated method stub
		
	}

    // Implement other methods such as getHotelById, addHotel, updateHotel, deleteHotel
}
