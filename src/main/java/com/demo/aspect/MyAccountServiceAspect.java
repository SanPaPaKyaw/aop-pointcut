package com.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class MyAccountServiceAspect {

    @Pointcut("this(com.demo.service.MyAccountService)")
    public void thisMyAccountServicePointCut(){}

   // @Before("thisMyAccountServicePointCut()")
    public void beforeMyAccountAdvice(JoinPoint joinPoint){
        String className=joinPoint.getTarget().getClass().getSimpleName();

        System.out.println(String.format("%s'%s method is invoked with %s before advice in [%s]."
                ,className,joinPoint.getSignature().getName()
                , Arrays.toString(joinPoint.getArgs()), LocalDateTime.now()));
    }

}
