package com.jbond.projects.spring_cource.hibernate_learn.entity.Collections.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemList.class)
                .buildSessionFactory();

        List<String> details = new ArrayList<>();
        details.add("One");
        details.add("Two");
        details.add("Three");
        ItemList itemCollection = new ItemList("OneItem", details);

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
