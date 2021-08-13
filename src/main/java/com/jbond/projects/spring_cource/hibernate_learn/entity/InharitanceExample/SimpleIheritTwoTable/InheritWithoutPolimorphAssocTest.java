package com.jbond.projects.spring_cource.hibernate_learn.entity.InharitanceExample.SimpleIheritTwoTable;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Одна таблица для каждого конкретного класса и неявный полиморфизм
// Главная проблема неявного отображения наследования заключается в отсутствии достаточной поддержки полиморфных ассоциаций
// Обычно в базе данных ассоциации представлены в виде связей по внешнему ключу. Если все подклассы
// отображаются на разные таблицы, как показано в схеме на рис. 6.1, полиморфные
// ассоциации с их суперклассом
public class InheritWithoutPolimorphAssocTest {
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
