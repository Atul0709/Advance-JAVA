package employee.hibernate.crud;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.configure();

        SessionFactory factory = config.buildSessionFactory();

        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("1. Add Employee");
                System.out.println("2. Update Employee");
                System.out.println("3. Delete Employee");
                System.out.println("4. Display All Employees");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        addEmployee(session, scanner);
                        break;
                    case 2:
                        updateEmployee(session, scanner);
                        break;
                    case 3:
                        deleteEmployee(session, scanner);
                        break;
                    case 4:
                        displayAllEmployees(session);
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close(); // close scanner
            session.close();
            factory.close();
        }
    }

    private static void addEmployee(Session session, Scanner scanner) {
       
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee department: ");
        String department = scanner.nextLine();
        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Enter employee contact: ");
        String contact = scanner.nextLine();

        employee emp = new employee( 0, name, department, salary, contact);
        session.save(emp);
        System.out.println("Employee added successfully!");
    }

    private static void updateEmployee(Session session, Scanner scanner) {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        employee emp = session.get(employee.class, id);
        if (emp != null) {
            System.out.print("Enter updated name: ");
            emp.setName(scanner.nextLine());
            System.out.print("Enter updated department: ");
            emp.setDepartment(scanner.nextLine());
            System.out.print("Enter updated salary: ");
            emp.setSalary(scanner.nextDouble());
            scanner.nextLine(); // consume newline
            System.out.print("Enter updated contact: ");
            emp.setContact(scanner.nextLine());

            session.update(emp);
            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Employee with ID " + id + " not found!");
        }
    }

    private static void deleteEmployee(Session session, Scanner scanner) {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        employee emp = session.get(employee.class, id);
        if (emp != null) {
            session.delete(emp);
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee with ID " + id + " not found!");
        }
    }

    private static void displayAllEmployees(Session session) {
        Query<employee> query = session.createQuery("from employee", employee.class);
        List<employee> employees = query.list();
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
        } else {
            System.out.println("Employee List:");
            for (employee emp : employees) {
                System.out.println(emp);
            }
        }
    }
}
