package com.qucc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class TimeAspect {

    @Around("execution(* com.qucc.service.impl.*.*(..))") //切入点表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Long begin = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        Long end = System.currentTimeMillis();

        log.info(joinPoint.getSignature() + "方法执行耗时： {}ms", end - begin );
        return result;
    }
}
