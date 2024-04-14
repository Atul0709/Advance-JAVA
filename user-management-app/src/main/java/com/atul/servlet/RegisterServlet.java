package com.atul.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atul.dao.UserDAO;
import com.atul.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserDAO userDAO = new UserDAO();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            if (userDAO.getUserById(username) != null) { // Assuming method exists
                out.println("<html><body>");
                out.println("<b>Username already exists!</b>");
                out.println("<br><a href='register.jsp'>Try again</a>");
                out.println("</body></html>");
            } else {
                User user = new User(0, username, password, email);
                userDAO.addUser(user);
                out.println("<html><body>");
                out.println("<b>Registration successful!</b>");
                out.println("<br><a href='login.jsp'>Login</a>");
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors gracefully (e.g., error message to user)
        }
    }
}
