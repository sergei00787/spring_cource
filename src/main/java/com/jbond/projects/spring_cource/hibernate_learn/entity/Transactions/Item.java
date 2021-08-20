package com.jbond.projects.spring_cource.hibernate_learn.entity.Transactions;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Items")
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;

    public Item(String itemName) {
        this.itemName = itemName;
    }
}
