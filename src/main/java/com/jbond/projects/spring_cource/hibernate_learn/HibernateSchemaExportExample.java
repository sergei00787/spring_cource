package com.jbond.projects.spring_cource.hibernate_learn;

import com.jbond.projects.spring_cource.HibernateUtil;
import org.hibernate.Session;

// В данном примере показан пример экспорта схемы
public class HibernateSchemaExportExample {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        HibernateUtil.exportSchema("schema.sql");
        session.close();
        HibernateUtil.shutdown();
    }

}
