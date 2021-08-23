package com.jbond.projects.spring_cource.hibernate_learn.entity.Events.Interceptors;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.Objects;

// Реализация перехватчиков Hibernate
@Entity
@Table(name = "item_interseptor")
@Data
@NoArgsConstructor
public class ItemInterceptors implements Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Version
    private Long version;

    public ItemInterceptors(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(ItemInterceptors.class)
                .buildSessionFactory();

        ItemInterceptors item1 = new ItemInterceptors("item1");
        ItemInterceptors item2 = new ItemInterceptors("item1");

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(item1);
            session.persist(item2);

            session.getTransaction().commit();

            session.getTransaction().begin();
            session.detach(item1);
            item1.setName("item1_new_name");
            session.merge(item1);

            session.remove(item2);

            session.flush();
            session.getTransaction().commit();
            session.close();
        } finally {
            sessionFactory.close();
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemInterceptors item = (ItemInterceptors) o;
        return id.equals(item.id) && name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
