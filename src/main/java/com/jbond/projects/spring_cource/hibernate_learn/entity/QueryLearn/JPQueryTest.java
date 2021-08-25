package com.jbond.projects.spring_cource.hibernate_learn.entity.QueryLearn;

import org.hibernate.ScrollMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.Parameter;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.List;

public class JPQueryTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemForQuery.class)
                .addAnnotatedClass(UserForQuery.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            // Запрос с именованными параметрами

            /*Query<UserForQuery> query =
                    session.createQuery( "select u from UserForQuery u where u.username = :paramUsername");
            query.setParameter("paramUsername", "plaid");

            for(Parameter<?> parameter: query.getParameters()){
                System.out.println("TYPE PARAMERERS -------" + parameter.getParameterType().getSimpleName());
            }

            List<UserForQuery> listUsers = query.getResultList();

            for (UserForQuery itm : listUsers) {
                System.out.println(itm.getUsername() + " - " + itm.getId());
            }
            */


            // Запрос с позиционными параметрами
            /*
            Query<ItemForQuery> query2 =
                    session.createQuery( "select u from ItemForQuery u where u.name = ?1 and u.startDate >= ?2 ");
            query2.setParameter(1, "Dorsey");
            query2.setParameter(2, Date.valueOf("2021-08-14"), TemporalType.TIME);

            List<ItemForQuery> listItems = query2.getResultList();

            for (ItemForQuery itm : listItems) {
                System.out.println("*********************" + itm.toString());
            }
            */


            // Постраничная выборка с запросом
            /*
            Query query3 = session.createQuery("select i from ItemForQuery i where i.startDate >= :paramStart");

            query3.setParameter("paramStart", Date.valueOf("2021-08-01"));
            List<ItemForQuery> listItemForQuery = query3.setFirstResult(1).setMaxResults(40).getResultList();

            System.out.println("----------SIZE LIST ---------- " + listItemForQuery.size());
            */


            // Выборка Hibernate с курсором
            /*
            org.hibernate.query.Query<ItemForQuery> hibQuery = session.createQuery("select i from ItemForQuery i", ItemForQuery.class);

            org.hibernate.ScrollableResults cursor = hibQuery.scroll(ScrollMode.SCROLL_INSENSITIVE);
            // ScrollMode.SCROLL_INSENSITIVE в предыдущем
            //примере означает, что никакие изменения в базе данных не повлияют на курсор,
            //т. е. результат запроса не подвержен неповторимому или фантомному чтению
            // SCROLL_SENSITIVE (курсор, чувствительный к изменениям)
            // и FORWARD_ONLY (курсор, допускающий перемещение только вперед).
            cursor.last();
            int count = cursor.getRowNumber()+1;
            cursor.first();
            cursor.next();
            ItemForQuery tt = (ItemForQuery) cursor.get(0);
            System.out.println("----------*********------------" + tt.toString());
            cursor.close();

            List<ItemForQuery> listItemForQuery = hibQuery.setFirstResult(1).setMaxResults(40).getResultList();

            System.out.println("----------SIZE LIST ---------- " + listItemForQuery.size());
            System.out.println(count);
            */


            // Выборка с именованными запросами
            // Запросы можно хранить в метаданных xml или в аннотациях классов
            TypedQuery<ItemForQuery> queryNamed = session.createNamedQuery("findItemByName");

            queryNamed.setParameter("paramName", "Priscilla");
            List<ItemForQuery> listItemForQuery = queryNamed.getResultList();

            System.out.println("----------SIZE LIST ---------- " + listItemForQuery.size());


            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


    }


}
