package com.jbond.projects.spring_cource.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect // Объявляем класс компонент как Аспект Spring
@Order(0) // Порядок выполнения аспектов задается данной анотацией
public class LogginAspect {

//    Добавдение и объявление поинтката
    @Pointcut("execution(public void say())")
    private void sayMethod(){};

    // Wildcard * и .. - можно объявлять любой метод/тип/любые параметры метода
    @Pointcut("execution(public * *(..))")
    private void allMethod(){};

    // Агрегация поинткатов || - или, && - и, ! - не
    @Pointcut("sayMethod() || allMethod()")
    private void allMethodAndSay(){};


    // Аспект перед вызовом метода
    // В JoinPoint содержится информация о сигнатуре вызываемого метода и его параметров
    @Before("sayMethod()")
    public void beforeCatSayAdvice(JoinPoint joinPoint){
        System.out.println("Aspect before Cat say");
        System.out.println(joinPoint.getArgs());
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getKind());
    }

    // Аспект вызывается после выдачи результата, но перед передачей результата
    // дальше в принимающий код который использует данный результат.
    // Таким образом можно изменить результат
    @AfterReturning(pointcut = "execution(public int sayMeow())", returning = "result")
    public void afterRetMethodAdvice(JoinPoint joinPoint, int result){
        System.out.println("afterRetMethodAdvice: run");
        result = 2;
    }
}
