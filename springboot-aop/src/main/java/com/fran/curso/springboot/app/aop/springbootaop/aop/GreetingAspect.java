package com.fran.curso.springboot.app.aop.springbootaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(2)
@Aspect
@Component
public class GreetingAspect {

    private final Logger logger = LoggerFactory.getLogger(GreetingAspect.class);

    //    @Before("execution(String com.fran.curso.springboot.app.aop.springbootaop.services.GreetingService.sayHello(..))")
//    @Before("execution(* com.fran.curso.springboot.app.aop.springbootaop.services.GreetingService.sayHello(..))")
//    @Before("execution(* com.fran.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    @Before("GreetingServicePointCut.greetingLoggerPointCut()")
    public void loggerBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Antes: " + method + " con los argumentos " + args);
    }

    //    @After("execution(* com.fran.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    @After("GreetingServicePointCut.greetingLoggerPointCut()")
    public void loggerAfter(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues: " + method + " con los argumentos " + args);
    }

    //    @AfterReturning("execution(* com.fran.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    @AfterReturning("GreetingServicePointCut.greetingLoggerPointCut()")
    public void loggerReturning(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues de retornar: " + method + " con los argumentos " + args);
    }

    //    @AfterThrowing("execution(* com.fran.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    @AfterThrowing("GreetingServicePointCut.greetingLoggerPointCut()")
    public void loggerThrowing(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("Despues de lanzar una excepcion: " + method + " con los argumentos " + args);
    }

    //    @Around("execution(* com.fran.curso.springboot.app.aop.springbootaop.services.*.*(..))")
    @Around("GreetingServicePointCut.greetingLoggerPointCut()")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        Object result = null;
        try {
            logger.info("El metodo " + method + " () con los parametros " + args);
            result = joinPoint.proceed();
            logger.info("El metodo " + method + " retorna el resultado:" + result);
            return result;
        } catch (Throwable e) {
            logger.error("Error en la llamada del methodo " + method + "()");
            throw e;
        }

//        return result;
    }
}
