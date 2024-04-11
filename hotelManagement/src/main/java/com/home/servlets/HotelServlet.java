package com.home.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.home.dao.HotelDAO;
import com.home.dao.HotelDAOImpl;
import com.home.model.Hotel;

@WebServlet("/hotels")
public class HotelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private HotelDAO hotelDao = new HotelDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve all hotels
        List<Hotel> hotels = hotelDao.getAllHotels();
        request.setAttribute("hotels", hotels);
        request.getRequestDispatcher("userPanel.jsp").forward(request, response);
    }
}
