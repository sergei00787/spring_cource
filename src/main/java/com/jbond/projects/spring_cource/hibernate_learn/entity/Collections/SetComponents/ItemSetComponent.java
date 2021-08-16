package com.jbond.projects.spring_cource.hibernate_learn.entity.Collections.SetComponents;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_set_components")
@Data
@NoArgsConstructor
public class ItemSetComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String itemName;

    @ElementCollection
    @CollectionTable(name = "detail_set_components")
    @Column(name = "details")
    @AttributeOverride(name = "name", column = @Column(name = "component_name"))
    private Set<Component> detailSetComponent = new HashSet<Component>();

    public ItemSetComponent(String itemName, Set<Component> detailSetComponent) {
        this.itemName = itemName;
        this.detailSetComponent = detailSetComponent;
    }
}
