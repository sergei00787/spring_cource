package com.jbond.projects.spring_cource.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogginAspect {
    @Before("execution(public void say())")
    public void beforeCatCreateAdvice(){
        System.out.println("Aspect befor Cat create");
    }
}
