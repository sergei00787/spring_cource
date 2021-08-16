package com.jbond.projects.spring_cource.hibernate_learn.entity.Collections.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class TestCollection {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemCollection.class)
                .buildSessionFactory();

        Collection<String> details = new ArrayList<>();
        details.add("One");
        details.add("Two");
        details.add("Three");
        ItemCollection itemCollection = new ItemCollection("OneItem",details);

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(itemCollection);
            System.out.println("------------------DONE-----------------");

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
