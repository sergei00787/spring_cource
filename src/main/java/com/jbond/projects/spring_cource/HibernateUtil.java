package com.jbond.projects.spring_cource;

import com.jbond.projects.spring_cource.hibernate_learn.entity.Constraints.DomainConstr;
import com.jbond.projects.spring_cource.hibernate_learn.entity.Relations.Department;
import com.jbond.projects.spring_cource.hibernate_learn.entity.Relations.Employee;
import com.jbond.projects.spring_cource.hibernate_learn.entity.Relations.EmployeeDetail;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.io.File;
import java.util.EnumSet;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    private static Metadata metadata;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

                MetadataSources sources = new MetadataSources(registry);
                metadata = sources
                        .addAnnotatedClass(Employee.class)
                        .addAnnotatedClass(EmployeeDetail.class)
                        .addAnnotatedClass(Department.class)
                        .addAnnotatedClass(DomainConstr.class)
                        .getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e){
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void exportSchema(String filename){
        SchemaExport schemaExport = new SchemaExport();
        EnumSet<TargetType> enumSet = EnumSet.of(TargetType.SCRIPT);

        File file = new File(filename);
        new File(filename).delete();
        schemaExport.setDelimiter(";");
        schemaExport.setFormat(true);
        schemaExport.setOutputFile(filename);
        schemaExport.execute(enumSet, SchemaExport.Action.CREATE, metadata);
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
