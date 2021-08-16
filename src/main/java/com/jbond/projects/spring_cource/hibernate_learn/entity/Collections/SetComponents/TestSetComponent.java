package com.jbond.projects.spring_cource.hibernate_learn.entity.Collections.SetComponents;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;

public class TestSetComponent {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemSetComponent.class)
                .buildSessionFactory();

        HashSet<Component> details = new HashSet<>();
        Component comp1= new Component("comp1", 100, 100);
        Component comp2= new Component("comp2", 200, 200);
        Component comp3= new Component("comp3", 300, 300);
        Component comp4= new Component("comp4", 400, 400);
        details.add(comp1);
        details.add(comp2);
        details.add(comp3);
        details.add(comp4);
        ItemSetComponent itemSet = new ItemSetComponent("OneItem",details);

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
