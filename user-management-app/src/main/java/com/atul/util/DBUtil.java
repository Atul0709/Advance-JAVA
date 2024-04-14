package com.atul.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // Replace with your database driver
    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_management"; // Replace with your database connection details
    private static final String USERNAME = "user"; // Replace with your database username
    private static final String PASSWORD = "password"; // Replace with your database password

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("JDBC Driver not found.");
            }
        }
        return connection;
    }
}
