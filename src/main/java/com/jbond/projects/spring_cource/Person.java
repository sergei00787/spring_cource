package com.jbond.projects.spring_cource;

public class Person {
    private Cat cat;
    private Dog dog;

    public Person(Cat cat) {
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
}
