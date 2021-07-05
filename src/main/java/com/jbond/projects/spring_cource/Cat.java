package com.jbond.projects.spring_cource;

import lombok.*;

@Data
@AllArgsConstructor
public class Cat {
    @Setter
    @Getter
    private String phrase;

    @Setter
    @Getter
    private String name;

    @Getter
    @Setter
    private int age;

    public void say(String phrase){
        setPhrase(phrase);
        System.out.println(this.phrase + " - " + age + " - " + name );
    }
}