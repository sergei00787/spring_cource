package com.jbond.projects.spring_cource.hibernate_learn.entity.HibernateEnvers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditQuery;

import java.util.Date;
import java.util.List;

public class EnversTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemAuditEnvers.class)
                .addAnnotatedClass(DetailAuditEnvers.class)
                .buildSessionFactory();

        ItemAuditEnvers item1 = new ItemAuditEnvers("item1", 1);
        ItemAuditEnvers item2 = new ItemAuditEnvers("item2", 2);
        ItemAuditEnvers item3 = new ItemAuditEnvers("item3", 3);

        DetailAuditEnvers detailAuditEnvers1 = new DetailAuditEnvers("detail1", item1);
        DetailAuditEnvers detailAuditEnvers2 = new DetailAuditEnvers("detail2", item1);
        DetailAuditEnvers detailAuditEnvers3 = new DetailAuditEnvers("detail3", item1);
        DetailAuditEnvers detailAuditEnvers4 = new DetailAuditEnvers("detail4", item2);
        DetailAuditEnvers detailAuditEnvers5 = new DetailAuditEnvers("detail5", item2);
        DetailAuditEnvers detailAuditEnvers6 = new DetailAuditEnvers("detail6", item2);
        DetailAuditEnvers detailAuditEnvers7 = new DetailAuditEnvers("detail7", item3);
        DetailAuditEnvers detailAuditEnvers8 = new DetailAuditEnvers("detail8", item3);
        DetailAuditEnvers detailAuditEnvers9 = new DetailAuditEnvers("detail9", item3);

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            session.persist(item1);
            session.persist(item2);
            session.persist(item3);
            session.persist(detailAuditEnvers1);
            session.persist(detailAuditEnvers2);
            session.persist(detailAuditEnvers3);
            session.persist(detailAuditEnvers4);
            session.persist(detailAuditEnvers5);
            session.persist(detailAuditEnvers6);
            session.persist(detailAuditEnvers7);
            session.persist(detailAuditEnvers8);
            session.persist(detailAuditEnvers9);

            item1.getDetails().add(detailAuditEnvers1);
            item1.getDetails().add(detailAuditEnvers2);
            item1.getDetails().add(detailAuditEnvers3);
            item2.getDetails().add(detailAuditEnvers4);
            item2.getDetails().add(detailAuditEnvers5);
            item2.getDetails().add(detailAuditEnvers6);
            item3.getDetails().add(detailAuditEnvers7);
            item3.getDetails().add(detailAuditEnvers8);
            item3.getDetails().add(detailAuditEnvers9);

            session.getTransaction().commit();

            session.getTransaction().begin();

            ItemAuditEnvers itemFind1 = session.find(ItemAuditEnvers.class, 1L);
            ItemAuditEnvers itemFind2 = session.find(ItemAuditEnvers.class, 2L);
            ItemAuditEnvers itemFind3 = session.find(ItemAuditEnvers.class, 3L);

            System.out.println("------------------------------" + itemFind1.getDetails().isEmpty());


            itemFind1.setName("new_item1_name");
            itemFind2.setSimpleNum(2);
            itemFind3.setName("new_item3_name");

            session.getTransaction().commit();


            // ПОИСК версий
            AuditReader auditReader = AuditReaderFactory.get(session);
            Number revCreate = auditReader.getRevisionNumberForDate(new Date(1629823086528L));

            System.out.println("REV CREATE --------------- " + revCreate.toString());

            List<Number> itemRevisions = auditReader.getRevisions(ItemAuditEnvers.class, 1L);

            System.out.println("ITEM REVISIONS-------------------" + itemRevisions.toString());

            // Поиск версий с помощью запросов
            AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(DetailAuditEnvers.class, false, false);
            List<Object[]> result = query.getResultList();
            for (Object[] tuple: result) {
                DetailAuditEnvers detailAuditEnversByQuery = (DetailAuditEnvers) tuple[0];
                DefaultRevisionEntity revision = (DefaultRevisionEntity) tuple[1];
                RevisionType revissionType = (RevisionType) tuple[2];

                System.out.println("*************************");
                System.out.println(detailAuditEnversByQuery);
                System.out.println(revision.toString());
                System.out.println(revissionType.toString());
                System.out.println("*************************");
            }

        } finally {
            sessionFactory.close();
        }


    }
}
