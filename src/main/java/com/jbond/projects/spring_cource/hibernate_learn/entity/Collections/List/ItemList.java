package com.jbond.projects.spring_cource.hibernate_learn.entity.Collections.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "item_list")
@Data
@NoArgsConstructor
public class ItemList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String itemName;

    @ElementCollection
    @CollectionTable(name = "detail_list")
    @OrderColumn // Добавляет  столбец который позволяет сохранять порядок записей
    @Column(name = "details")
    private List<String> detailList = new ArrayList<String>();

    public ItemList(String itemName, List<String> detailList) {
        this.itemName = itemName;
        this.detailList = detailList;
    }
}
