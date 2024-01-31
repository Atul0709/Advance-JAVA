package BCA.model;

import java.sql.*;
import java.util.Scanner;

public class student {

    public static void insertData(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        System.out.print("Enter student gender (M/F): ");
        String gender = scanner.next();
        System.out.print("Enter student E.No.: ");
        String eno = scanner.next();
        System.out.print("Enter student course: ");
        String course = scanner.next();
        System.out.print("Enter student GPA: ");
        double gpa = scanner.nextDouble();

        String sql = "INSERT INTO students (name, age, gender, eno, course, gpa) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, eno);
            preparedStatement.setString(5, course);
            preparedStatement.setDouble(6, gpa);
            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully!");
        }
    }

    public static void fetchAllData(Connection connection) throws SQLException {
        String sql = "SELECT * FROM students";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("\nStudent Details:");
            System.out.println("-----------------");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("Gender: " + resultSet.getString("gender"));
                System.out.println("E.No.: " + resultSet.getString("eno"));
                System.out.println("Course: " + resultSet.getString("course"));
                System.out.println("GPA: " + resultSet.getDouble("gpa"));
                System.out.println("-----------------");
            }
        }
    }

    public static void updateData(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter the ID of the student to update: ");
        int id = scanner.nextInt();

        // Check if the ID exists
        if (!isStudentExists(connection, id)) {
            System.out.println("Student with ID " + id + " does not exist.");
            return;
        }

        System.out.print("Enter new student name: ");
        String name = scanner.next();
        System.out.print("Enter new student age: ");
        int age = scanner.nextInt();
        System.out.print("Enter new student gender (M/F): ");
        String gender = scanner.next();
        System.out.print("Enter new student E.No.: ");
        String eno = scanner.next();
        System.out.print("Enter new student course: ");
        String course = scanner.next();
        System.out.print("Enter new student GPA: ");
        double gpa = scanner.nextDouble();

        String sql = "UPDATE students SET name=?, age=?, gender=?, eno=?, course=?, gpa=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, eno);
            preparedStatement.setString(5, course);
            preparedStatement.setDouble(6, gpa);
            preparedStatement.setInt(7, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data updated successfully!");
            } else {
                System.out.println("Failed to update data.");
            }
        }
    }

    public static void deleteData(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter the ID of the student to delete: ");
        int id = scanner.nextInt();

        // Check if the ID exists
        if (!isStudentExists(connection, id)) {
            System.out.println("Student with ID " + id + " does not exist.");
            return;
        }

        String sql = "DELETE FROM students WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data deleted successfully!");
            } else {
                System.out.println("Failed to delete data.");
            }
        }
    }

    public static boolean isStudentExists(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}
