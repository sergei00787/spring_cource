package com.jbond.projects.spring_cource.hibernate_learn.entity.Collections.Set;

import com.jbond.projects.spring_cource.hibernate_learn.entity.ConverterExample.ConverterTest;
import com.jbond.projects.spring_cource.hibernate_learn.entity.ConverterExample.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.HashSet;

public class TestSet {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemSet.class)
                .buildSessionFactory();

        HashSet<String> details = new HashSet<>();
        details.add("One");
        details.add("Two");
        details.add("Three");
        ItemSet itemSet = new ItemSet("OneItem",details);

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(itemSet);
            System.out.println("------------------DONE-----------------");

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
