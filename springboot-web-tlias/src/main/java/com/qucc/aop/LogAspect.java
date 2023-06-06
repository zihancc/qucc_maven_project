package com.qucc.aop;

import com.alibaba.fastjson.JSONObject;
import com.qucc.mapper.OperateLogMapper;
import com.qucc.pojo.OperateLog;
import com.qucc.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.qucc.annotation.Log)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id"); //操作人ID
        LocalDateTime operateTime = LocalDateTime.now(); //操作时间
        String className = proceedingJoinPoint.getTarget().getClass().getName(); //操作类名
        String methodName = proceedingJoinPoint.getSignature().getName(); //操作方法名
        String methodParams = Arrays.toString(proceedingJoinPoint.getArgs()); //操作方法参数
        Long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        Long end = System.currentTimeMillis();
        String returnValue = JSONObject.toJSONString(result); //操作方法返回值
        Long costTime = end - start; //操作耗时
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams,
                returnValue, costTime);
        operateLogMapper.insert(operateLog);
        log.info("AOP记录操作日志 ： {}", operateLog);
        return result;
    }
}
