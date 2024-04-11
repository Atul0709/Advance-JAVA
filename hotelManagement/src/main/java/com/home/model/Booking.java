package com.home.model;

import java.util.Date;

public class Booking {
    private int id;
    private int userId;
    private int hotelId;
    private Date checkIn;
    private Date checkOut;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", userId=" + userId + ", hotelId=" + hotelId + ", checkIn=" + checkIn
				+ ", checkOut=" + checkOut + "]";
	}

    // Getters and setters

    // toString method
}
