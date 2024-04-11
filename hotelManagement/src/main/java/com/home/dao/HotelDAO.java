package com.home.dao;

import java.util.List;
import com.home.model.Hotel;

public interface HotelDAO {
    List<Hotel> getAllHotels();
    Hotel getHotelById(int id);
    void addHotel(Hotel hotel);
    void updateHotel(Hotel hotel);
    void deleteHotel(int id);
}
