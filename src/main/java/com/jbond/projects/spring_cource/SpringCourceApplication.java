package com.jbond.projects.spring_cource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringCourceApplication {

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContextWithAnnotation.xml");
        AnnotationConfigApplicationContext contextAnnotation = new AnnotationConfigApplicationContext(Config.class);

        Person person = contextAnnotation.getBean("person", Person.class);

//        Person person = context.getBean("person", Person.class);
        person.sayYourCat();
        person.sayYourDog();

        contextAnnotation.close();
//        context.close();

        //        SpringApplication.run(SpringCourceApplication.class, args);
    }

}
