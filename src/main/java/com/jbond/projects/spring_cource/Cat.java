package com.jbond.projects.spring_cource;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component("cat")
public class Cat implements Pet{
    private String phrase;
    private String name;
    private int age;

    @Override
    public void say() {
        System.out.println("Meow");
    }
}
