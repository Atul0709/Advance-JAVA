package employee.hibernate.HQL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Connect to database
        SessionFactory factory = EmployeeUtil.getSessionFactory();

        // CRUD operations
        // 1. Insert
        insertEmployee(factory);

        // 2. Select
        selectEmployee(factory);

        // 3. Update
        updateEmployee(factory);

        // 4. Delete
        deleteEmployee(factory);

        // 5. HQL Statement
        executeHQLStatement(factory);

        // Close session factory
        EmployeeUtil.closeFactory();
    }

    private static void insertEmployee(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Employee employee = new Employee();
            employee.setName("John Doe");
            employee.setDepartment("IT");
            employee.setSalary(50000);
            employee.setContact("1234567890");
            session.save(employee);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void selectEmployee(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            List<Employee> employees = query.getResultList();
            for (Employee employee : employees) {
                System.out.println("Employee: " + employee.getName() + ", Department: " + employee.getDepartment());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateEmployee(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("UPDATE Employee SET department = :dept WHERE name = :name");
            query.setParameter("dept", "Finance");
            query.setParameter("name", "John Doe");
            int rowCount = query.executeUpdate();
            tx.commit();
            System.out.println("Updated " + rowCount + " record(s).");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteEmployee(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Employee WHERE name = :name");
            query.setParameter("name", "John Doe");
            int rowCount = query.executeUpdate();
            tx.commit();
            System.out.println("Deleted " + rowCount + " record(s).");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeHQLStatement(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("SELECT name, department FROM Employee WHERE salary > :salary");
            query.setParameter("salary", 40000.0);
            List<Object[]> resultList = query.getResultList();
            for (Object[] row : resultList) {
                System.out.println("Name: " + row[0] + ", Department: " + row[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
