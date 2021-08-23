package com.jbond.projects.spring_cource.hibernate_learn.entity.Events.EventListenerHibernate;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

public class ItemEventListenerAfter {

    @PostPersist
    public void send_mes_after_persist(Object item) {
        System.out.println("----- AFTER PERSIST -----" + item.toString());
    }

    @PostUpdate
    // Методы вызываются до и после синхронизации контекста хранения с базой
    //данных, т. е. до и после выталкивания контекста, но только в том случае, когда
    //требуется синхронизация сущности (если она определена как изменившаяся)
    public void send_mes_after_update(Object item) {
        System.out.println("----- AFTER UPDATE -----" + item.toString());
    }

    @PostRemove
    // Вызываются при выполнении remove() или каскадном удалении экземпляра
    // сущности, а также после удаления записи из базы данных, когда происходит
    // выталкивание контекста хранения
    public void send_mes_after_remove(Object item) {
        System.out.println("----- BEFORE REMOVE -----" + item.toString());
    }


}
