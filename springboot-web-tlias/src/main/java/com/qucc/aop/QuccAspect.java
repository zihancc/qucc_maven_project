package com.qucc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class QuccAspect {

    @Before("execution(* com.qucc.service.impl.DeptServiceImpl.*(..))")
    public void before(){
        log.info("before 运行了~~");
    }

    @Around("execution(* com.qucc.service.impl.DeptServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("around before运行了~~");

        Object result = proceedingJoinPoint.proceed();

        log.info("around after运行了~~");

        return result;
    }

    @After("execution(* com.qucc.service.impl.DeptServiceImpl.*(..))")
    public void after(){
        log.info("after 运行了~~");
    }

    @AfterReturning("execution(* com.qucc.service.impl.DeptServiceImpl.*(..))")
    public void afterReturing(){
        log.info("afterReturning 运行了~~");
    }

    @AfterThrowing("execution(* com.qucc.service.impl.DeptServiceImpl.*(..))")
    public void afterThrowing(){
        log.info("afterThrowing 运行了~~");
    }
}
