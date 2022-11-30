package com.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class CurrencyServiceAspect {

    @Pointcut("within(com.demo.service.*)")
    public void withinCurrencyServicePointCut(){

    }
    @Pointcut("@within(com.demo.annotation.Secured)")
    public void withinAnnotationCurrencyServicePointCut(){}

    @Pointcut("@target(com.demo.annotation.Secured))")
    public void targetAnnotationCurrencyServicePointCut(){}

    @Pointcut("@annotation(com.demo.annotation.InTransaction)")
    public void annotationCurrencyServicePointCut(){}

    @Pointcut("bean(currency)")
    public void beanCurrencyServicePointCut(){}

    @Pointcut("args(int,int)")
    public void argsCurrencyServicePointCut(){}

    @Pointcut("@args(com.demo.annotation.Validated)")
    public void argsAnnotationCurrencyServicePointCut(){}


   // @Before("withinCurrencyServicePointCut()")
    public void beforeCurrencyAdvice(JoinPoint joinPoint) {
        String className=joinPoint.getTarget().getClass().getSimpleName();

        System.out.println(String.format("%s'%s method is invoked with %s parameter before "+"advice in [%s]."
                ,className,joinPoint.getSignature().getName()
                , Arrays.toString(joinPoint.getArgs()), LocalDateTime.now())
        );
    }

   // @Before("withinAnnotationCurrencyServicePointCut()")
    public void beforeWithinAnnotationCurrencyAdvice(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();

        System.out.println(String.format("%s'%s method is invoked with %s parameter before " + "advice in [%s]."
                , className, joinPoint.getSignature().getName()
                , Arrays.toString(joinPoint.getArgs()), LocalDateTime.now()));
    }
   // @Before("annotationCurrencyServicePointCut()")
    public void beforeAnnotationCurrencyAdvice(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();

        System.out.println(String.format("%s'%s method is invoked with %s parameter before " + "advice in [%s]."
                , className, joinPoint.getSignature().getName()
                , Arrays.toString(joinPoint.getArgs()), LocalDateTime.now()));
    }
   // @Before("beanCurrencyServicePointCut()")
    public void beforeBeanCurrencyServiceAdvice(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();

        System.out.println(String.format("%s'%s method is invoked with %s parameter before " + "advice in [%s]."
                , className, joinPoint.getSignature().getName()
                , Arrays.toString(joinPoint.getArgs()), LocalDateTime.now()));
    }
   // @Before("argsCurrencyServicePointCut()")
   // @Before("withinCurrencyServicePointCut() && argsCurrencyServicePointCut()")
    public void argsBeanCurrencyAdvice(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();

        System.out.println(String.format("%s'%s method is invoked with %s parameter before " + "advice in [%s]."
                , className, joinPoint.getSignature().getName()
                , Arrays.toString(joinPoint.getArgs()), LocalDateTime.now()));
    }

    @After("argsAnnotationCurrencyServicePointCut()")
    public void argsAnnotationCurrencyAdvice(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();

        System.out.println(String.format("%s'%s method is invoked with %s parameter after " + "advice in [%s]."
                , className, joinPoint.getSignature().getName()
                , Arrays.toString(joinPoint.getArgs()), LocalDateTime.now()));
    }


    @Around("targetAnnotationCurrencyServicePointCut()")
    public Object argsAnnotationCurrencyAroundAdvice(ProceedingJoinPoint joinPoint)throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();

        System.out.println(String.format("%s'%s method is invoked with %s parameter after " + "advice in [%s]."
                , className, joinPoint.getSignature().getName()
                , Arrays.toString(joinPoint.getArgs()), LocalDateTime.now()));

        try {
            return joinPoint.proceed();
        }finally{
            System.out.println("After invoking method.......");
        }

    }


    @AfterReturning(value = "argsAnnotationCurrencyServicePointCut()",returning = "country")
    public void argsAnnotationAfterReturningCurrencyAdvice(JoinPoint joinPoint,String country) {
        String className = joinPoint.getTarget().getClass().getSimpleName();

        System.out.println(String.format("%s'%s method is invoked with %s parameter after returning " + "advice return value:: [%s] in [%s]."
                , className, joinPoint.getSignature().getName()
                , Arrays.toString(joinPoint.getArgs())
                ,country, LocalDateTime.now()));
    }

    @AfterThrowing(value = "argsAnnotationCurrencyServicePointCut()",throwing = "e")
    public void argsAnnotationAfterThrowingCurrencyAdvice(JoinPoint joinPoint,Throwable e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();

        System.out.println(String.format("%s'%s method is invoked with %s parameter after throwing " + "advice exception class is:: [%s] in [%s]."
                , className, joinPoint.getSignature().getName()
                , Arrays.toString(joinPoint.getArgs())
                ,e.getClass().getSimpleName(), LocalDateTime.now()));
    }

}
