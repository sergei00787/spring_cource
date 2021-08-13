package com.jbond.projects.spring_cource.hibernate_learn.entity.InharitanceExample.InheritanceTypeJoined;


import javax.persistence.*;

@Entity
@Table(name = "animal_joined")
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String phrase = "I am animal";

    public void say() {
        System.out.println(this.phrase);
    }

}
