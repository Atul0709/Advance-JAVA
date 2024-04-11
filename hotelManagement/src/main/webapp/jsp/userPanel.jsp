<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Panel</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h2>Welcome User!</h2>
    <!-- Display available hotels -->
    <h3>Available Hotels</h3>
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
                <td><a href="bookHotel?id=${hotel.id}">Book</a></td>
            </tr>
        </c:forEach>
    </table>

    <!-- Display user bookings -->
    <h3>Your Bookings</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>Hotel</th>
            <th>Check-in</th>
            <th>Check-out</th>
            <th>Action</th>
        </tr>
        <c:forEach var="booking" items="${bookings}">
            <tr>
                <td>${booking.id}</td>
                <td>${booking.hotelName}</td>
                <td>${booking.checkIn}</td>
                <td>${booking.checkOut}</td>
                <td><a href="cancelBooking?id=${booking.id}">Cancel</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
