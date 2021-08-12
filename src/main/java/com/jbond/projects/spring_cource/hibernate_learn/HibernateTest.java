package com.jbond.projects.spring_cource.hibernate_learn;

import com.jbond.projects.spring_cource.hibernate_learn.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
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

            Employee emp1 = new Employee("Oleg", "Smirnov", 1000);
            Employee emp2 = new Employee("Sidor", "Ivanchuk", 200);
            Employee emp3 = new Employee("Ivan", "Usachev", 1500);
            Employee emp4 = new Employee("Kirill", "Petrov", 300);

            EmployeeDetail detail1 = new EmployeeDetail("Moskow", "Oleg@gmail.com");
            EmployeeDetail detail2 = new EmployeeDetail("New-York", "Sidor@gmail.com");
            EmployeeDetail detail3 = new EmployeeDetail("NSK", "Ivan@gmail.com");
            EmployeeDetail detail4 = new EmployeeDetail("Petrograd", "Kirill@gmail.com");

            Department department1 = new Department("IT", 500, 2000);
            Department department2 = new Department("HR", 200, 2000);
            Department department3 = new Department("PR", 200, 2000);


            joinEmployeeData(emp1, detail1, department1);
            joinEmployeeData(emp2, detail2, department1);
            joinEmployeeData(emp3, detail3, department2);
            joinEmployeeData(emp4, detail4, department3);

            session.persist(emp1);
            session.persist(emp2);
            session.save(emp3);
            session.save(emp4);

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


    public static void joinEmployeeData(Employee emp, EmployeeDetail detail, Department department) {
        emp.setDetail(detail);
        detail.setEmployee(emp);
        emp.setDepartment(department);
    }
}
