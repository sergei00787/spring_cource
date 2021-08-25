package com.jbond.projects.spring_cource.hibernate_learn.entity.HibernateEnvers;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class DetailAuditEnvers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detailName;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "item_id")
    private ItemAuditEnvers item;

    public DetailAuditEnvers(String detailName, ItemAuditEnvers item) {
        this.detailName = detailName;
        this.item = item;
    }

    @Override
    public String toString() {
        return "DetailAuditEnvers{" +
                "id=" + id +
                ", detailName='" + detailName + '\'' +
                ", item=" + item +
                '}';
    }
}
