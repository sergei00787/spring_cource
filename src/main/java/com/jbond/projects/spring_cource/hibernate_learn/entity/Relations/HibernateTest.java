package com.jbond.projects.spring_cource.hibernate_learn.entity.Relations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

// В данном примере показаны связи:
// один к одному
// один ко многим
// многие ко многим
// все связи имеют двустороннюю направленность (Bi-direction)
public class HibernateTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(EmployeeDetail.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Professor.class)
                .buildSessionFactory();


        // First time we clear table Employee, because tables EmpDetail and Department has ForeygnKey
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Employee").executeUpdate();
            session.getTransaction().commit();
        }

        // Clear table EmployeeDetail
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from EmployeeDetail ").executeUpdate();
            session.getTransaction().commit();
        }

        // Clear table Department
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Department ").executeUpdate();
            session.getTransaction().commit();
        }


        // Открываем Сессию
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            //*********************************** Create Data and Save to DB

            EmployeeDetail detail1 = new EmployeeDetail("Moskow", "Oleg@gmail.com");
            EmployeeDetail detail2 = new EmployeeDetail("New-York", "Sidor@gmail.com");
            EmployeeDetail detail3 = new EmployeeDetail("NSK", "Ivan@gmail.com");
            EmployeeDetail detail4 = new EmployeeDetail("Petrograd", "Kirill@gmail.com");

            Employee emp1 = new Employee("Oleg", "Smirnov", 1000, detail1);
            Employee emp2 = new Employee("Sidor", "Ivanchuk", 200, detail2);
            Employee emp3 = new Employee("Ivan", "Usachev", 1500, detail3);
            Employee emp4 = new Employee("Kirill", "Petrov", 300, detail4);

            Department department1 = new Department("IT", 500, 2000);
            Department department2 = new Department("HR", 200, 2000);
            Department department3 = new Department("PR", 200, 2000);

            emp1.setDepartment(department1);
            emp2.setDepartment(department1);
            emp3.setDepartment(department2);
            emp4.setDepartment(department3);

            session.persist(emp1);
            session.persist(emp2);
            session.persist(emp3);
            session.persist(emp4);

            //*********************************** Select EmployeeDetail where City equals NSK
            List<EmployeeDetail> details = session.createQuery("from EmployeeDetail where city = :paramCity")
                    .setParameter("paramCity", "NSK")
                    .list();
            for (EmployeeDetail employeeDetails : details) {
                if (employeeDetails.getEmployee() != null)
                    System.out.println(employeeDetails.getEmployee().toString());
            }
            System.out.println("******************   DONE    ***********************");

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }



    }

}
