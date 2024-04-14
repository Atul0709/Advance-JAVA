package com.atul.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atul.dao.UserDAO;
import com.atul.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = null;
        try {
            user = userDAO.getUserById(username); // Assuming method exists
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors gracefully (e.g., error message to user)
        }

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentUser", user);
            response.sendRedirect("list-users.jsp"); // Redirect to user list or profile
        } else {
            out.println("<html><body>");
            out.println("<b>Invalid username or password!</b>");
            out.println("<br><a href='login.jsp'>Login again</a>");
            out.println("</body></html>");
        }
    }
}
