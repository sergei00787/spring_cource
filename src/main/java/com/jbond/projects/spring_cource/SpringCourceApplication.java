package com.jbond.projects.spring_cource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringCourceApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


        Person person = context.getBean("personTest", Person.class);
        person.sayYourCat();
        person.sayYourDog();

        context.close();

        //        SpringApplication.run(SpringCourceApplication.class, args);
    }

}
