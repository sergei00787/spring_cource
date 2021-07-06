package com.jbond.projects.spring_cource;

import com.jbond.projects.spring_cource.employeesPkg.ProjectManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringCourceApplication {

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContextWithAnnotation.xml");
//        AnnotationConfigApplicationContext contextAnnotation = new AnnotationConfigApplicationContext(Config.class);

//        Person person = contextAnnotation.getBean("person", Person.class);

//        Person person = context.getBean("person", Person.class);
//        person.sayYourCat();
//        person.sayYourDog();

//        contextAnnotation.close();
//        context.close();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigWithoutComponentScan.class);

        ProjectManager projectManager = context.getBean("projectManagerBean", ProjectManager.class);
        System.out.println(projectManager.getSalary());

        context.close();

        //        SpringApplication.run(SpringCourceApplication.class, args);
    }

}
