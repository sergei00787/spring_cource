package com.jbond.projects.spring_cource.hibernate_learn.entity.InharitanceExample.SimpleIheritTwoTable;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Animal {
    public String phrase = "I am animal";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public void say() {
        System.out.println(this.phrase);
    }

}
