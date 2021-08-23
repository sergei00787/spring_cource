package com.jbond.projects.spring_cource.hibernate_learn.entity.Events.Interceptors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionImplementor;

public class InterInterseptorTest {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemInterceptors.class)
                .addAnnotatedClass(AuditItemInterceptor.class)
                .addAnnotatedClass(AuditLogRecord.class)
                .buildSessionFactory();


        ItemInterceptors item1 = new ItemInterceptors("item1");

        try (Session session = sessionFactory
                .withOptions()
                .interceptor(new AuditItemInterceptor())
                .openSession()) {

            AuditItemInterceptor interceptor = (AuditItemInterceptor) ((SessionImplementor) session).getInterceptor();
            interceptor.setCurrentSession(session);
            interceptor.setCurrentUserId(1L);

            session.beginTransaction();
            session.persist(item1);

            item1.setName("new_name");
            session.flush();

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


    }

}
