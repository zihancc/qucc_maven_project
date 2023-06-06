package com.qucc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
//@Aspect
public class QuccAspect1 {

    @Pointcut("@annotation(com.qucc.annotation.QuccLog)")
    private void pt(){}

    @Before("pt()")
    public void before(){
        log.info("QuccAspect1...before 运行了~~");
    }

    @Around("pt()")
    public Object exeAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("QuccAspect1...exeAround before运行了~~");

        String className = proceedingJoinPoint.getTarget().getClass().getName();
        log.info("目标对象的类名：{}", className);

        String methodName = proceedingJoinPoint.getSignature().getName();
        log.info("目标对象的方法名： {}", methodName);

        Object[] args = proceedingJoinPoint.getArgs();
        log.info("目标方法的方法运行时传入的参数： {}", Arrays.toString(args));
        Object result = proceedingJoinPoint.proceed();

        log.info("目标方法运行的返回值： {}", result);

        log.info("QuccAspect1...exeAround after运行了~~");

        return result;
    }

}
