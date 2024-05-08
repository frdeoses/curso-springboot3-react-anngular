package com.fran.curso.springboot.app.aop.springbootaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1)
@Aspect
@Component
public class GreetingFooAspect {
    private final Logger logger = LoggerFactory.getLogger(GreetingFooAspect.class);


    //    @Before("execution(* com.fran.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    @Before("GreetingServicePointCut.greetingFooLoggerPointCut()")
    public void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes Foo: " + method + " invocado con los parametros " + args);
    }

    //    @After("execution(* com.fran.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    @After("GreetingServicePointCut.greetingFooLoggerPointCut()")
    public void loggerAfter(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues Foo: " + method + " con los parametros " + args);
    }
}
