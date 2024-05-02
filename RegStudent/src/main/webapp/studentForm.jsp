<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Registration Form</title>
</head>
<body>
    <h2>Student Registration Form</h2>
    <form action="StudentServlet" method="post">
        Name: <input type="text" name="name"><br>
        Email: <input type="email" name="email"><br>
        Phone: <input type="text" name="phone"><br>
        Address: <input type="text" name="address"><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
