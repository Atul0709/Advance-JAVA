package BCA.drive;



import java.sql.*;
import java.util.Scanner;

import BCA.model.student;

public class main {

    public static void main(String[] args) {
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String username = "root";
        String password = "1234";

        try {
            // Register the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully!");

            // Create a table with modified attributes (move this code to a separate setup class if needed)
            String sql = "CREATE TABLE IF NOT EXISTS students " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    " name VARCHAR(50), " +
                    " age INTEGER, " +
                    " gender VARCHAR(10), " +
                    " eno VARCHAR(20), " +
                    " course VARCHAR(50), " +
                    " gpa DECIMAL(3,2))";
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
            }

            while (true) {
                System.out.println("\n1. Insert data");
                System.out.println("2. Fetch all data");
                System.out.println("3. Update data");
                System.out.println("4. Delete data");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        student.insertData(connection, scanner);
                        break;

                    case 2:
                        student.fetchAllData(connection);
                        break;

                    case 3:
                        student.updateData(connection, scanner);
                        break;

                    case 4:
                        student.deleteData(connection, scanner);
                        break;

                    case 5:
                        System.out.println("Exiting the program...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice, please try again...");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error occurred while closing the connection: " + e.getMessage());
            }
        }
    }
}
