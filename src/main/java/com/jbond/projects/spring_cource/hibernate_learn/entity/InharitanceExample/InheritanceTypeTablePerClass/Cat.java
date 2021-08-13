package com.jbond.projects.spring_cource.hibernate_learn.entity.InharitanceExample.InheritanceTypeTablePerClass;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cat_second")
@AttributeOverride(name = "phrase",
        column = @Column(name = "phrase_cat"))
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
