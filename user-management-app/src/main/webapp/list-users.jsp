<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.atul.model.User" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>List Users</title>
</head>
<body>
    <%
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<User> users = (List<User>) request.getAttribute("users");
        if (users == null) {
            users = new ArrayList<>();
        }
    %>

    <h2>List Users</h2>
    <a href="logout">Logout</a><br><br>

    <% if (users.isEmpty()) { %>
        <p>No users found.</p>
    <% } else { %>
        <table>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
            </tr>
            <% for (User user : users) { %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getUsername() %></td>
                <td><%= user.getEmail() %></td>
            </tr>
            <% } %>
        </table>
    <% } %>
</body>
</html>
