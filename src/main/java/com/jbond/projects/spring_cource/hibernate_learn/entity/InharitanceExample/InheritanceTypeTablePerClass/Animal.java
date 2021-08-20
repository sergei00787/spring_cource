package com.jbond.projects.spring_cource.hibernate_learn.entity.InharitanceExample.InheritanceTypeTablePerClass;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "animal_table_per_class")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Animal {
    public String phrase = "I am animal";
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    public void say() {
        System.out.println(this.phrase);
    }

}
