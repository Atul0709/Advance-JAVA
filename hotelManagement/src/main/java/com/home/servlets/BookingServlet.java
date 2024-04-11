package com.home.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.home.dao.BookingDAO;
import com.home.dao.BookingDAOImpl;
import com.home.model.Booking;
import com.home.model.User;

@WebServlet("/bookHotel")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BookingDAO bookingDao = new BookingDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        int hotelId = Integer.parseInt(request.getParameter("id"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date checkIn = null;
        Date checkOut = null;
        try {
            checkIn = dateFormat.parse(request.getParameter("checkIn"));
            checkOut = dateFormat.parse(request.getParameter("checkOut"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setHotelId(hotelId);
        booking.setCheckIn(checkIn);
        booking.setCheckOut(checkOut);

        bookingDao.bookHotel(booking);

        response.sendRedirect("userPanel.jsp");
    }
}
