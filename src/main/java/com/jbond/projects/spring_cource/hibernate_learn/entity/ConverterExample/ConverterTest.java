package com.jbond.projects.spring_cource.hibernate_learn.entity.ConverterExample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Date;

public class ConverterTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(ConverterTest.class)
                .buildSessionFactory();

        Date date = Date.valueOf("2021-03-01");
        Item itemTest = new Item(false, date);

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();



//            session.save(itemTest);
            Item item = (Item) session.load(Item.class, 1L);
            System.out.println(item.getStrDate());
            System.out.println("------------------DONE-----------------");

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
