package com.jbond.projects.spring_cource.hibernate_learn.entity.Relations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

// В данном примере показана связь:
// многие ко многим
// связь имеет двустороннюю направленность (Bi-direction)
public class HibernateTestManyToMany {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addResource("hibernate.cfg.xml");

        //SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
        SessionFactory sessionFactory = configuration
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(EmployeeDetail.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Professor.class)
                .buildSessionFactory();

        // TEST Many to many Relations
        Student student1 = new Student("Petr","Ivanov");
        Student student2 = new Student("Sidr","Pticin");
        Student student3 = new Student("Kolya","Petrov");
        Student student4 = new Student("Masha","Strigheva");

        Professor professor1 = new Professor("Prof1", "Prof1");
        Professor professor2 = new Professor("Prof2", "Prof2");
        Professor professor3 = new Professor("Prof3", "Prof3");
        Professor professor4 = new Professor("Prof4", "Prof4");

        List<Professor> professors_list_1 = new ArrayList<>();
        professors_list_1.add(professor1);
        professors_list_1.add(professor2);

        List<Professor> professors_list_2 = new ArrayList<>();
        professors_list_2.add(professor3);
        professors_list_2.add(professor4);

        student1.setProfessors(professors_list_1);
        student2.setProfessors(professors_list_2);
        student3.setProfessors(professors_list_1);
        student4.setProfessors(professors_list_2);

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(student1);
            session.persist(student2);
            session.persist(student3);
            session.persist(student4);
            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
