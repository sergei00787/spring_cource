package com.jbond.projects.spring_cource.hibernate_learn.entity.InharitanceExample.InheritanceTypeJoined;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cat_joined")
@AttributeOverride(name = "phrase", column = @Column(name = "phrase_cat"))
@PrimaryKeyJoinColumn(name = "cat_id")
@Data
@NoArgsConstructor
public class Cat extends Animal {
    private int age;
    private String name;

    public Cat(int age, String name, String phrase) {
        this.age = age;
        this.name = name;
        this.phrase = phrase;
    }

    @Override
    public void say() {
        System.out.println(this.phrase);
    }
}
