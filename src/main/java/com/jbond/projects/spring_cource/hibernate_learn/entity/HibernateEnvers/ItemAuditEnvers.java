package com.jbond.projects.spring_cource.hibernate_learn.entity.HibernateEnvers;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item_audit_envers")
@Audited
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"details"})
public class ItemAuditEnvers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotAudited
    private int simpleNum;

    @OneToMany(mappedBy = "item", cascade = CascadeType.PERSIST)
    private List<DetailAuditEnvers> details = new ArrayList<DetailAuditEnvers>();

    public ItemAuditEnvers(String name, int simpleNum) {
        this.name = name;
        this.simpleNum = simpleNum;
    }

    @Override
    public String toString() {
        return "ItemAuditEnvers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", simpleNum=" + simpleNum +
                '}';
    }
}
