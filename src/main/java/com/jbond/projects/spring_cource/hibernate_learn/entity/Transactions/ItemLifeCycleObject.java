package com.jbond.projects.spring_cource.hibernate_learn.entity.Transactions;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Items_LifeCycleObject")
@NoArgsConstructor
public class ItemLifeCycleObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;

    public ItemLifeCycleObject(String itemName) {
        this.itemName = itemName;
    }
}
