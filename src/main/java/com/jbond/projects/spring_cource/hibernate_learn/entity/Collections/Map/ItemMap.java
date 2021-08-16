package com.jbond.projects.spring_cource.hibernate_learn.entity.Collections.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "item_map")
@Data
@NoArgsConstructor
public class ItemMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String itemName;

    @ElementCollection
    @CollectionTable(name = "detail_map")
    @MapKeyColumn(name = "int_key")
    @Column(name = "details")
    private Map<Integer, String> detailMap = new HashMap<Integer, String>();

    public ItemMap(String itemName, Map<Integer, String> detailList) {
        this.itemName = itemName;
        this.detailMap = detailList;
    }
}
