package employee.hibernate.HQL;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class EmployeeUtil {
    static SessionFactory factory = null;

    public static SessionFactory getSessionFactory() {
        try {
            Configuration config = new Configuration();

            Properties hibernateProperties = new Properties();

            hibernateProperties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            hibernateProperties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/advancejavadb?useSSL=false");
            hibernateProperties.put("hibernate.connection.username", "root");
            hibernateProperties.put("hibernate.connection.password", "1234");
            hibernateProperties.put("hibernate.hbm2ddl.auto", "create-drop");
            hibernateProperties.put("hibernate.show_sql", "true");
            hibernateProperties.put("hibernate.format_sql", "true");

            config.setProperties(hibernateProperties);

            config.addAnnotatedClass(Employee.class);

            factory = config.buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return factory;
    }

    public static void closeFactory() {
        if (factory != null)
            factory.close();
    }
}
