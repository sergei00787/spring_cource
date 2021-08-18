package com.jbond.projects.spring_cource.hibernate_learn.entity.Relations.ManyToManyAlternative;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Date;

public class AlternativeManyToManyTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(Professor.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(ProfessorXStudent.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Professor professor1 = new Professor("professor1", "master1");
            Professor professor2 = new Professor("professor2", "master2");
            Professor professor3 = new Professor("professor3", "master3");

            session.persist(professor1);
            session.persist(professor2);
            session.persist(professor3);

            Student student1 = new Student("student1", "dummy1");
            Student student2 = new Student("student2", "dummy2");
            Student student3 = new Student("student3", "dummy3");

            session.persist(student1);
            session.persist(student2);
            session.persist(student3);

            ProfessorXStudent linkedProfStudent1 = new ProfessorXStudent(Date.valueOf("2021-01-01"), professor1, student1);
            ProfessorXStudent linkedProfStudent2 = new ProfessorXStudent(Date.valueOf("2021-02-02"), professor2, student2);
            ProfessorXStudent linkedProfStudent3 = new ProfessorXStudent(Date.valueOf("2021-03-03"), professor3, student3);

            session.persist(linkedProfStudent1);
            session.persist(linkedProfStudent2);
            session.persist(linkedProfStudent3);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
