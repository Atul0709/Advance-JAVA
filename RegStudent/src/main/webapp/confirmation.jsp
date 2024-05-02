<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmation</title>
</head>
<body>
    <h2>Registration Successful!</h2>
    <%
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // JDBC Connection and Retrieval
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atul", "root", "1234");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM students WHERE name=? AND email=? AND phone=? AND address=?");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
    %>
                <p>Name: <%= rs.getString("name") %></p>
                <p>Email: <%= rs.getString("email") %></p>
                <p>Phone: <%= rs.getString("phone") %></p>
                <p>Address: <%= rs.getString("address") %></p>
    <%
            } else {
    %>
                <p>No data found.</p>
    <%
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    %>
</body>
</html>
