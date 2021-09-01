package com.jbond.projects.spring_cource.hibernate_learn.entity.QueryLearn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class CriteriaProjectionTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemForQuery.class)
                .addAnnotatedClass(UserForQuery.class)
                .buildSessionFactory();


        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = cb.createQuery(Tuple.class);

            Root<ItemForQuery> rItem = criteriaQuery.from(ItemForQuery.class);
            Root<UserForQuery> rUser = criteriaQuery.from(UserForQuery.class);
            //criteriaQuery.multiselect(rItem.alias("aliasItem"), rUser.alias("aliasUser"));
            criteriaQuery.multiselect(rItem.get("name").alias("name"), rUser.get("username").alias("user"));
            criteriaQuery.where( cb.equal(rItem.get("user"),rUser.get("id")));

            TypedQuery<Tuple> query = session.createQuery(criteriaQuery);

            List<Tuple> listItems = query.getResultList();

            for (Tuple tuple : listItems) {
                String itm = tuple.get("name", String.class);
                String user = tuple.get("user", String.class);

                System.out.println(itm + " - " + user);
            }

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


    }


}
