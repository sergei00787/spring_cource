package com.jbond.projects.spring_cource.hibernate_learn.entity.Events.Interceptors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "audit_items_by_interceptors")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class AuditLogRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Long itemId;
    private Class itemClass;
    private String userID;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationOn = new Date();

    public AuditLogRecord(String type, Long itemId, Class itemClass, String userID) {
        this.type = type;
        this.itemId = itemId;
        this.itemClass = itemClass;
        this.userID = userID;
    }
}
