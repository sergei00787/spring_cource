package com.jbond.projects.spring_cource.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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


    // Advice перед вызовом метода
    // В JoinPoint содержится информация о сигнатуре вызываемого метода и его параметров
    @Before("sayMethod()")
    public void beforeCatSayAdvice(JoinPoint joinPoint){
        System.out.println("Aspect before Cat say");
        System.out.println(joinPoint.getArgs());
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getKind());
    }

    // Advice вызывается после выдачи результата, но перед передачей результата
    // дальше в принимающий код который использует данный результат.
    // Таким образом можно изменить результат
    @AfterReturning(pointcut = "execution(public String sayKuku())", returning = "result")
    public void afterRetMethodAdvice(JoinPoint joinPoint, String result){
        System.out.println("afterRetMethodAdvice: run");
        result = "KUKU returning in Advice";
    }

    //@AfterThrowing
    // Advice вызывается после выбрасывания исключения, можно поймать ошибку вставляя параметр.

    //@After()
    // Advice вызывается либо при нормальном исполнении программы и выдачи результата, либо с выдачей ошибки.
    // С помощью данного аспекта нельзя отловить ошибку и нельзя повлиять на результат выдачи метода

    // @Around()
    // Advice
    @Around("execution(public String sayMeow())")
    public String aroundSayMeowAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("begin advice aroundSayMeowAdvice.");


        Object target = proceedingJoinPoint.proceed();
        target = "TARGET CHANGED";

        System.out.println("end advice aroundSayMeowAdvice.");

        return (String)target;
    }


}
