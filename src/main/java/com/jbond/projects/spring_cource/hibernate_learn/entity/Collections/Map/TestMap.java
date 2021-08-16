package com.jbond.projects.spring_cource.hibernate_learn.entity.Collections.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestMap {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemMap.class)
                .buildSessionFactory();

        Map<Integer, String> details = new HashMap<>();
        details.put(1, "One");
        details.put(2, "Two");
        details.put(3, "Three");
        details.put(4, "Four");
        details.put(5, "Five");
        ItemMap itemCollection = new ItemMap("OneItem", details);

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
