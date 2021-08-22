package com.jbond.projects.spring_cource.hibernate_learn.entity.Transactions;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TransactionConcurrencyVersionTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemConcurrencyWithVersion.class)
                .buildSessionFactory();

//        ItemWithVersion item = new ItemWithVersion();
//        item.setName("item1");
//        ItemWithVersion item2 = new ItemWithVersion();
//        item2.setName("item2");
//        ItemWithVersion item3 = new ItemWithVersion();
//        item3.setName("item3");

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
//            session.save(item);
//            session.save(item2);
//            session.save(item3);


            ItemConcurrencyWithVersion item = session.get(ItemConcurrencyWithVersion.class, 1L, LockMode.OPTIMISTIC_FORCE_INCREMENT);
            // LockMode.OPTIMISTIC_FORCE_INCREMENT - принудительно увеличивает версию при запросе
            // PESSIMISTIC_READ гарантирует повторимость чтения.
            // PESSIMISTIC_WRITE предоставляет дополнительные гарантии: в дополнение к повторимому чтению
            // реализация JPA должна предоставить последовательный доступ к данным, чтобы
            // сделать невозможными фантомные чтения
            item.setNumber_dont_care(1);
            item.setName("item_new");

            System.out.println("------------------DONE-----------------");

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
