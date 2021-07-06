package com.jbond.projects.spring_cource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Person {
    @Qualifier("cat")
    @Autowired
    private Pet pet;
    private String name;

    public void sayYourCat(){
        System.out.println("my cat name is " + pet.toString());
    }

    public void sayYourDog(){
        System.out.println("my Dog's name is " + pet.toString());
    }

    public String getName() {
        return name;
    }

    public void initPerson(){
        System.out.println("Init person bean");
        this.name = "DefaultName";
    }

    public void destroyPerson(){
        System.out.println("Destroy person bean");
        this.name = null;
    }
}
