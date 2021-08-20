package com.jbond.projects.spring_cource.hibernate_learn.entity.Transactions;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

public class EntityManagerTest {
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("my_catalog");

        EntityManager em = emf.createEntityManager();

        try{

            // Тестируем состояния
            em.getTransaction().begin();
            Item item = new Item("simple_item_1");

            // Состояние Transient (Временное)
            System.out.println(em.contains(item));

            em.persist(item);
            // Состояние MANAGED (Persistent)
            System.out.println(em.contains(item));

            em.flush(); // Выталкиваем контекст

            em.remove(item); // Состояние Removed
            em.persist(item); // Состояние MANAGED (Persistent)
            item.setItemName("changed_name_1");
            em.getTransaction().commit();
            em.close(); // Состояние detacheed

            em = emf.createEntityManager();
            em.getTransaction().begin();

            item = em.merge(item); // Опять состояние MANAGED (Persistent)
            em.refresh(item); // Все еще Persistent состояние и перезагружено в БД
            em.getTransaction().commit();
            em.clear(); // Состояние detached
            em.close();



            // Тестируем получение ссылки на объект
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Item item2 = em.getReference(Item.class, 1L); // Получаем ссылку на объект, создается прокси-объект

            PersistenceUnitUtil persistenceUnitUtil = emf.getPersistenceUnitUtil();
            System.out.println("Объект ITEM2 содержится в контексте хранения? - " + persistenceUnitUtil.isLoaded(item2));  //false
            String str = item2.getItemName();
            Hibernate.initialize(item2); // Загрузка данных из прокси объекта
            System.out.println("Объект ITEM2 содержится в контексте хранения после запроса свойства ItemName? - " + persistenceUnitUtil.isLoaded(item2));  //true
            em.close(); // item2 в отсоединенном состоянии Detached

        } finally {
            em.close();
            emf.close();
        }

    }

}
