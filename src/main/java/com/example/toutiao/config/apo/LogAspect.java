package com.example.toutiao.config.apo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/15 11:46
 * @description:
 */
@Component
@Aspect
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.example.toutiao.controller..*(..))")
    public void logFunction(){}

    @Before("logFunction()")
    public void beforeMethod(JoinPoint joinPoint) {
        logger.info(joinPoint.getClass().getName() + " called Beginning");
    }

    @AfterReturning("logFunction()")
    public void afterReturnMethod(JoinPoint joinPoint) {
        logger.info(joinPoint.getClass().getName() + " called Finished");
    }

    @AfterThrowing("logFunction()")
    public void afterThrowingMethod(JoinPoint joinPoint) {
        logger.error(joinPoint.getClass().getName() + " throws errors");
    }
}
