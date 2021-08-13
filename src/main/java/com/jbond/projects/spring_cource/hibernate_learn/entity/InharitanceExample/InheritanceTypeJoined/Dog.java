package com.jbond.projects.spring_cource.hibernate_learn.entity.InharitanceExample.InheritanceTypeJoined;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dog_joined")
@Data
@NoArgsConstructor
public class Dog extends Animal {
    private String phrase = "I am Dog";
    private int age;
    @Column(name = "count_lag")
    private int countLag;
    private String name;

    public Dog(String name, String phrase, int age, int countLag) {
        this.name = name;
        this.phrase = phrase;
        this.age = age;
        this.countLag = countLag;
    }

    @Override
    public void say() {
        System.out.println(this.phrase);
    }
}
