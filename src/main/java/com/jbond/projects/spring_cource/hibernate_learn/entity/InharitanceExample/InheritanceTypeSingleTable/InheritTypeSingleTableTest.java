package com.jbond.projects.spring_cource.hibernate_learn.entity.InharitanceExample.InheritanceTypeSingleTable;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Одна таблица для всех конкретных классов и полиморфизм
// главная проблема – целостность данных. Столбцы для свойств,
// объявленных в подклассах, могут содержать null.
public class InheritTypeSingleTableTest {
    public static void main(String[] args) {

        Cat cat = new Cat(1, "Vaska", "I am Vaska");
        Dog dog = new Dog("Tuzik", "I am Tuzik", 3, 4);

        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(Cat.class)
                .addAnnotatedClass(Dog.class)
                .addAnnotatedClass(Animal.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.persist(cat);
            session.persist(dog);
            session.getTransaction().commit();

            System.out.println("--------------------DONE---------------------");
        } finally {
            sessionFactory.close();
        }
    }
}
