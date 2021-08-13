package com.jbond.projects.spring_cource.hibernate_learn.entity.EmbeddedEntityExample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmbedTestExample {


    public static void main(String[] args) {


        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(MainCl.class)
                .addAnnotatedClass(EmbedCl.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            EmbedCl embedCl = new EmbedCl("EmbString", 1, EnmsCl.ENMS2);
            MainCl mainCl = new MainCl("Name3", embedCl);

            session.beginTransaction();
            session.persist(mainCl);
            session.getTransaction().commit();

//            session.beginTransaction();
//            List<MainCl> mc = session.createQuery("select m from MainCl m where m.id = 6").list();
//            MainCl m1 = mc.get(0);
//            EmbedCl e1 = m1.getEmbedCl();
//            System.out.println(e1.getPropEnums().toString());
//            session.getTransaction().commit();
//
//            session.beginTransaction();
//            session.createQuery("delete from MainCl m where m.id = :paramId")
//                    .setParameter("paramId",  mainCl.getId())
//                    .executeUpdate();
//            session.getTransaction().commit();

            System.out.println("--------------------DONE---------------------");
        } finally {
            sessionFactory.close();
        }
    }


}
