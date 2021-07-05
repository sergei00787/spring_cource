package com.jbond.projects.spring_cource;

public class Person {
    private Cat cat;

    public Person(Cat cat) {
        this.cat = cat;
    }

    public void sayYourPet(){
        System.out.println("my cat name is " + cat.getName());
    }
}
