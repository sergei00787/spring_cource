package com.jbond.projects.spring_cource.hibernate_learn.entity.Events.EventListenerHibernate;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item_eventlistener")
@EntityListeners({ItemEventListenerBefor.class, ItemEventListenerAfter.class})
// @ExcludeSuperclassListeners - Если в конкретном подклассе сущности потребуется отключить методы обратного вызова суперкласса
// @ExcludeDefaultListeners - Если потребуется отключить приемник событий по умолчанию
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Version
    private Long version;

    public Item(String name) {
        this.name = name;
    }

    @PostLoad //Метод вызывается после загрузки экземпляра сущности в контекст хранения,
    // либо в результате поиска по идентификатору, в ходе навигации и инициализации
    // прокси-объекта/коллекции, либо при выполнении запроса. Метод также
    // вызывается после изменения уже хранимого экземпляра
    private void post_load(){
        System.out.println("----- AFTER LOAD -----");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id.equals(item.id) && name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml")
                .addAnnotatedClass(Item.class)
                .buildSessionFactory();

        Item item1 = new Item("item1");
        Item item2 = new Item("item1");

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
}
