package com.jbond.projects.spring_cource.hibernate_learn.entity.Collections.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_set")
@Data
@NoArgsConstructor
public class ItemSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String itemName;

    @ElementCollection
    @CollectionTable(name = "detail_set", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "details")
    private Set<String> detailSet = new HashSet<String>();

    public ItemSet(String itemName, Set<String> detailSet) {
        this.itemName = itemName;
        this.detailSet = detailSet;
    }
}
