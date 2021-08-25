package com.jbond.projects.spring_cource.hibernate_learn.entity.QueryLearn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaQueryTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemForQuery.class)
                .addAnnotatedClass(UserForQuery.class)
                .buildSessionFactory();


        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ItemForQuery> criteriaQuery = cb.createQuery(ItemForQuery.class);
            Root<ItemForQuery> root = criteriaQuery.from(ItemForQuery.class);
            criteriaQuery.select(root);

            TypedQuery<ItemForQuery> query = session.createQuery(criteriaQuery);

            List<ItemForQuery> listItems = query.getResultList();

            for (ItemForQuery itm : listItems) {
                System.out.println(itm.getName());
            }

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


    }


}
