package com.jbond.projects.spring_cource;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
@Scope(value = "prototype")
public class Dog implements Pet{
    @Setter
    @Getter
    private String phrase;

    @Setter
    @Getter
    private String name;

    @Getter
    @Setter
    private int age;

    public void say(String phrase) {
        setPhrase(phrase);
        System.out.println(this.phrase + " - " + age + " - " + name);
    }

    @Override
    public void say() {
        System.out.println("Wow wow");
    }
}
