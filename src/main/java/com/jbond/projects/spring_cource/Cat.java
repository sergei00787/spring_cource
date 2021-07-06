package com.jbond.projects.spring_cource;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope(value = "prototype")
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
