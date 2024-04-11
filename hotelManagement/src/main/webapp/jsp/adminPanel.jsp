<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Panel</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h2>Welcome Admin!</h2>
    <!-- Add hotel form -->
    <h3>Add Hotel</h3>
    <form action="addHotel" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br>
        <label for="location">Location:</label>
        <input type="text" id="location" name="location" required><br>
        <label for="price">Price:</label>
        <input type="text" id="price" name="price" required><br>
        <input type="submit" value="Add Hotel">
    </form>

    <!-- List of hotels -->
    <h3>Hotels</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Location</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        <%-- Loop through hotels and display them --%>
        <c:forEach var="hotel" items="${hotels}">
            <tr>
                <td>${hotel.id}</td>
                <td>${hotel.name}</td>
                <td>${hotel.location}</td>
                <td>${hotel.price}</td>
                <td><a href="deleteHotel?id=${hotel.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
