package com.jbond.projects.spring_cource.hibernate_learn.entity.Events.Interceptors;

import org.hibernate.*;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AuditItemInterceptor extends EmptyInterceptor {
    private Session currentSession;
    private Long currentUserId;
    private final Set<Auditable> inserts = new HashSet<Auditable>();
    private final Set<Auditable> updates = new HashSet<Auditable>();

    public void setCurrentSession(Session session) {
        this.currentSession = session;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    @Override
    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types)
            throws CallbackException {
        if (entity instanceof Auditable) inserts.add((Auditable) entity);
        return false;
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        super.onDelete(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity,
                                Serializable id,
                                Object[] currentState,
                                Object[] previousState,
                                String[] propertyNames,
                                Type[] types)
            throws CallbackException {
        if (entity instanceof Auditable)
            updates.add((Auditable) entity);
        return false;
    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        return super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public void postFlush(Iterator entities) {
        Session tempSession = currentSession.sessionWithOptions()
                .transactionContext()
                .connection()
                .openSession();
        try {
            for (Auditable entity : inserts) { //Сохраняет экземпляры AuditLogRecords
                tempSession.persist(
                        new AuditLogRecord("insert", entity.getId(), entity.getClass(), currentUserId.toString())
                );
            }
            for (Auditable entity : updates) {
                tempSession.persist(
                        new AuditLogRecord("update", entity.getId(), entity.getClass(), currentUserId.toString())
                );
            }
            tempSession.flush(); // Закрывает временный сеанс
        } finally {
            tempSession.close();
            inserts.clear();
            updates.clear();
        }
    }

    @Override
    public void preFlush(Iterator entities) {
        super.preFlush(entities);
    }

    @Override
    public Boolean isTransient(Object entity) {
        return super.isTransient(entity);
    }

    @Override
    public Object instantiate(String entityName, EntityMode entityMode, Serializable id) {
        return super.instantiate(entityName, entityMode, id);
    }

    @Override
    public int[] findDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        return super.findDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public String getEntityName(Object object) {
        return super.getEntityName(object);

    }

    @Override
    public Object getEntity(String entityName, Serializable id) {
        return super.getEntity(entityName, id);
    }

    @Override
    public void afterTransactionBegin(Transaction tx) {
        super.afterTransactionBegin(tx);
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        super.afterTransactionCompletion(tx);
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        super.beforeTransactionCompletion(tx);
    }

    @Override
    public String onPrepareStatement(String sql) {
        return super.onPrepareStatement(sql);
    }

    @Override
    public void onCollectionRemove(Object collection, Serializable key) throws CallbackException {
        super.onCollectionRemove(collection, key);
    }

    @Override
    public void onCollectionRecreate(Object collection, Serializable key) throws CallbackException {
        super.onCollectionRecreate(collection, key);
    }

    @Override
    public void onCollectionUpdate(Object collection, Serializable key) throws CallbackException {
        super.onCollectionUpdate(collection, key);
    }
}
