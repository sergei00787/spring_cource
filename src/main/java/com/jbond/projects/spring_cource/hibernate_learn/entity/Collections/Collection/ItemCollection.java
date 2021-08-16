package com.jbond.projects.spring_cource.hibernate_learn.entity.Collections.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "item_collection")
@Data
@NoArgsConstructor
public class ItemCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String itemName;

    @ElementCollection
    @CollectionTable(name = "detail_collection")
    @Column(name = "details")
    @GenericGenerator(name = "collection_id_gen", strategy = "sequence")
    @CollectionId(
            columns = @Column(name = "collection_id"),
            type = @Type(type = "long"),
            generator = "collection_id_gen"
    )
    private Collection<String> detailCollection = new ArrayList<>();

    public ItemCollection(String itemName, Collection<String> detailCollection) {
        this.itemName = itemName;
        this.detailCollection = detailCollection;
    }
}
