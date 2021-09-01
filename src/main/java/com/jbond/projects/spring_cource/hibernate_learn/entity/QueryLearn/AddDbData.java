package com.jbond.projects.spring_cource.hibernate_learn.entity.QueryLearn;

import com.github.javafaker.Faker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collection;
import java.util.HashSet;

public class AddDbData {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemForQuery.class)
                .addAnnotatedClass(UserForQuery.class)
                .addAnnotatedClass(ItemForQueryWithCollection.class)
                .addAnnotatedClass(UserForQueryWithCollection.class)
                .buildSessionFactory();

        Faker faker = new Faker();

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            for (int i = 0; i < 100; i++) {
                UserForQueryWithCollection user = new UserForQueryWithCollection(faker.name().username());
                UserForQueryWithCollection user1 = new UserForQueryWithCollection(faker.name().username());
                UserForQueryWithCollection user2 = new UserForQueryWithCollection(faker.name().username());

                ItemForQueryWithCollection item = new ItemForQueryWithCollection(faker.animal().name());
                item.getUsers().add(user);
                item.getUsers().add(user1);
                item.getUsers().add(user2);

                session.persist(item);
            }

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }

        /*

        List<ItemForQuery> listItems = new ArrayList<ItemForQuery>();
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            UserForQuery userTemp = new UserForQuery(faker.hipster().word());
            ItemForQuery item =
                    new ItemForQuery(faker.name().firstName(),
                            faker.number().numberBetween(0, 1000),
                            faker.date().past(20, TimeUnit.DAYS),
                            faker.date().past(10, TimeUnit.DAYS),
                            userTemp);
            listItems.add(item);
        }

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            for (ItemForQuery temp: listItems) {
                session.persist(temp);
            }

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }

        */


    }
}
