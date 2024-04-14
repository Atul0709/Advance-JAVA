package com.app.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.app.model.User;
import com.app.resources.HibernateUtil;

public class UserDAOHibernate {

    public void insertUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("\nSuccessfully Created Record(s) In The Database!\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
            System.out.println("\nSuccessfully Get Record(s) In The Database!\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    public boolean updateUser(User user) {
        boolean rowUpdated = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) { // Create session here
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            rowUpdated = true;
            System.out.println("\nSuccessfully Updated Record In The Database!\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }


    public boolean deleteUser(int id) {
        boolean rowDeleted = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) { // Create session here
            Transaction transaction = session.beginTransaction();
            User findUser = (User) session.load(User.class, id);
            session.delete(findUser);
            transaction.commit();
            rowDeleted = true;
            System.out.println("\nSuccessfully Deleted Record In The Database!\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }


    public User loginUser(String login_email, String login_password) {
        User user = null;
        String login_status = "Active";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) { // Create session here
            Transaction transaction = session.beginTransaction();

            // FROM User, the User is Object / Class and so the param belong to Object / Class, not the table name!
            Query hql_query = session.createQuery("FROM User u WHERE u.email = :email AND u.password = :password AND u.status = :status");
            hql_query.setParameter("email", login_email);
            hql_query.setParameter("password", login_password);
            hql_query.setParameter("status", login_status);

            List<User> result = hql_query.list();
            if (!result.isEmpty())
                user = result.get(0);

            System.out.println("\nSuccessfully Login !\n");
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public User selectUser(int id) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) { // Create session here
            Transaction transaction = session.beginTransaction();

            // FROM User, the User is Object / Class and so the param belong to Object / Class, not the table name!
            user = (User) session.load(User.class, id);

            // Committing The Transactions To The Database (Not required for select)
            // transaction.commit();
            System.out.println("\nSuccessfully Selected 1 Record By ID In The Database!\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public User selectUser(String email, String role) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) { // Create session here
            Transaction transaction = session.beginTransaction();

            // FROM User, the User is Object / Class and so the param belong to Object / Class, not the table name!
            Query hql_query = session.createQuery("FROM User u WHERE u.email = :email AND u.role = :role");
            hql_query.setParameter("email", email);
            hql_query.setParameter("role", role);

            List<User> result = hql_query.list();
            if (!result.isEmpty())
                user = result.get(0);

            System.out.println("\nSuccessfully Selected 1 Record By Email and Role In The Database!\n");
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

