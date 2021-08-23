package com.jbond.projects.spring_cource.hibernate_learn.entity.Events.EventListenerHibernate;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class ItemEventListenerBefor {

    @PrePersist // Вызывается сразу после вызова операции persist() для экземпляра сущности.
    // Также вызывается после выполнения операции merge() для временной сущности,
    // также будет вызван для связей сущностей с опцией CascadeType.PERSIST
    public void send_mes_befor_persist(Object item){
        System.out.println("----- BEFORE PERSISST -----"  + item.toString());
    }

    @PreUpdate
    // Методы вызываются до и после синхронизации контекста хранения с базой
    //данных, т. е. до и после выталкивания контекста, но только в том случае, когда
    //требуется синхронизация сущности (если она определена как изменившаяся)
    public void send_mes_befor_merge(Object item){
        System.out.println("----- BEFORE UPDATE -----"  + item.toString());
    }

    @PreRemove
    // Вызываются при выполнении remove() или каскадном удалении экземпляра
    // сущности, а также после удаления записи из базы данных, когда происходит
    // выталкивание контекста хранения
    public void send_mes_befor_remove(Object item){
        System.out.println("----- BEFORE REMOVE -----"  + item.toString());
    }


}
