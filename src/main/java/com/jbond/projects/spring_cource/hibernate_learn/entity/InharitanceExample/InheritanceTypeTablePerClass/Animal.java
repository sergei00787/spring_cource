package com.jbond.projects.spring_cource.hibernate_learn.entity.InharitanceExample.InheritanceTypeTablePerClass;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.mapping.Constraint;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Animal {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    public String phrase = "I am animal";

    public void say() {
        System.out.println(this.phrase);
    }

}
