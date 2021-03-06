package com.jbond.projects.spring_cource.hibernate_learn.entity.QueryLearn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
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
            //.where(cb.lessThanOrEqualTo(root.<Date>get("startDate"), new Date()));
            //.where(cb.<String>in(root.<String>get("name")).value("Priscilla").value("Carlee"));

            Predicate predicate  = cb.and(
                    cb.like(root.<String>get("name"), "%ar%"),
                    cb.isNotNull(root.<String>get("id"))
            );
            predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.<Date>get("startDate"), new Date()));
            criteriaQuery.where(predicate);
            criteriaQuery.orderBy(cb.desc(root.get("startDate")));

            TypedQuery<ItemForQuery> query = session.createQuery(criteriaQuery);

            List<ItemForQuery> listItems = query.getResultList();

            for (ItemForQuery itm : listItems) {
                System.out.println(itm);
            }

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


    }


}
