package com.jbond.projects.spring_cource.hibernate_learn.entity.QueryLearn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.Tuple;
import java.util.List;

public class QueryWithJoinTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemForQuery.class)
                .addAnnotatedClass(UserForQuery.class)
                .addAnnotatedClass(ItemForQueryWithCollection.class)
                .addAnnotatedClass(UserForQueryWithCollection.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            // Запрос с Неявным соединением
            // Неявные соединения работают для ассоциаций многие ко многим и один ко многим,
            // но не работают для ассоциации с коллекциями
            // (Этого не хватает в нативном SQL)
            /*
            Query<ItemForQuery> query =
                    session.createQuery("select i from ItemForQuery i where i.name = :paramItem " +
                            "and i.user.username like :paramUsernamePattern"); // Неявный вызов username
            query.setParameter("paramItem", "Nick");
            query.setParameter("paramUsernamePattern", "_outin_");


            List<ItemForQuery> listUsers = query.getResultList();

            for (ItemForQuery itm : listUsers) {
                System.out.println(itm.toString());
            }
            */

            // Запрос с явным соединением
            /*
            Query<Tuple> q = session.createQuery("select i, u from ItemForQuery i left join UserForQuery u on u.id = i.user "
                    + "where u.username = 'YOLO'");

            List<Tuple> list = q.getResultList();
            System.out.println("SIZE LIST--- " + list.size());
            */


            // Динамическое извлечение (работает и для коллекций элементов и для связей)
            // подводные камни.
            // 1) Никогда не присваивайте псевдонимы любым связям, извлекаемым немедленно, для использования в ограничениях или проекциях. Запрос left join
            // fetch i.bids b where b.amount ... не сработает.
            // 2) Не извлекайте более одной коллекции; в противном случае вы получите декартово произведение.
            // 3) Запросы игнорируют любые стратегии извлечения, определяемые в метаданных отображения с помощью
            // аннотации @org.hibernate.annotations.Fetch
            // 4) При немедленном извлечении коллекции возвращаемый Hibernate список
            //List содержит столько же записей, сколько вернул запрос SQL, в том числе
            //повторяющиеся ссылки.
            String strQuery = "select distinct i from ItemForQueryWithCollection as i " +
                    " join fetch i.users" +
                    " where i.name = 'baboon'";

            Query<ItemForQueryWithCollection> q = session.createQuery(strQuery);

            List<ItemForQueryWithCollection> list = q.getResultList();
            for (ItemForQueryWithCollection t: list){
                System.out.println(t.getName());
                System.out.println(t.getUsers());
            }



            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


        // ТЕТА соединение - это соединение посредством декартового произведения с условием объединения (where)
        // соединения могут не быть первичным и вторичными ключами например такой запрос :
        // select u, log from User u, LogRecord log where u.username = log.username


    }


}
