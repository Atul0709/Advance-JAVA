import java.sql.*;
import java.util.Scanner;

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

            // Create a table with modified attributes
            String sql = "CREATE TABLE IF NOT EXISTS students " +
                         "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                         " name VARCHAR(50), " +
                         " age INTEGER, " +
                         " gender VARCHAR(10), " +
                         " eno VARCHAR(20), " +
                         " course VARCHAR(50), " +
                         " gpa DECIMAL(3,2))";
            Statement statement = connection.createStatement();
            statement.execute(sql);

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
                        // Insert data
                        insertData(connection, scanner);
                        break;

                    case 2:
                        // Fetch all data
                        fetchAllData(connection);
                        break;

                    case 3:
                        // Update data
                        updateData(connection, scanner);
                        break;

                    case 4:
                        // Delete data
                        deleteData(connection, scanner);
                        break;

                    case 5:
                        // Exit
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

    private static void insertData(Connection connection, Scanner scanner) throws SQLException {
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

    private static void fetchAllData(Connection connection) throws SQLException {
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

    private static void updateData(Connection connection, Scanner scanner) throws SQLException {
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

    private static void deleteData(Connection connection, Scanner scanner) throws SQLException {
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

    private static boolean isStudentExists(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}

