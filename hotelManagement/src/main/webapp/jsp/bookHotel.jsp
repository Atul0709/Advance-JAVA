<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Hotel</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h2>Book Hotel</h2>
    <form action="bookHotel" method="get">
        <input type="hidden" name="id" value="${hotel.id}">
        <label for="checkIn">Check-in Date:</label>
        <input type="date" id="checkIn" name="checkIn" required><br>
        <label for="checkOut">Check-out Date:</label>
        <input type="date" id="checkOut" name="checkOut" required><br>
        <input type="submit" value="Book">
    </form>
</body>
</html>
