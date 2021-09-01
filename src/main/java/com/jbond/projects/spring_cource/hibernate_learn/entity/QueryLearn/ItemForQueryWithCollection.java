package com.jbond.projects.spring_cource.hibernate_learn.entity.QueryLearn;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item_for_query_with_collection")

@Data
@NoArgsConstructor
public class ItemForQueryWithCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_collection_for_item")
    @Column(name = "user")
    private Set<UserForQueryWithCollection> users = new HashSet<>();

    public ItemForQueryWithCollection(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ItemForQueryWithCollection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
