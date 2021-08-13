package com.jbond.projects.spring_cource.hibernate_learn.entity.InharitanceExample.InheritanceTypeTablePerClass;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Одна таблица для каждого конкретного класса и полиморфизм
// Поддерживается полиморфных ассоциаций
// Теперь можно родительскую класс можно связывать с другими классами по ключу
public class InheritTypeTablePerClassTest {
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
