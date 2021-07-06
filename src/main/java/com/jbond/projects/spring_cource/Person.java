package com.jbond.projects.spring_cource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class Person {
    @Autowired
    private Cat cat;
    @Autowired
    private Dog dog;
    private String name;

    public Person(Cat cat, Dog dog) {
        this.dog = dog;
        this.cat = cat;
    }

    public void sayYourCat(){
        System.out.println("my cat name is " + cat.getName());
    }

    public void sayYourDog(){
        System.out.println("my Dog's name is " + dog.getName());
    }

    public void setDog(Dog dog) {
        this.dog = dog;
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
