package com.jbond.projects.spring_cource;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component("cat")
public class Cat implements Pet{
    private String phrase;
    private String name;
    private Male male;
    private int age;
    private List<Cat> parents;


    @Override
    public void say() {
        System.out.println("Meow");
    }

    public String sayMeow(){
        return "Cat Phrase";
    }
    public String sayKuku(){
        return "Cat Phrase";
    }
}
