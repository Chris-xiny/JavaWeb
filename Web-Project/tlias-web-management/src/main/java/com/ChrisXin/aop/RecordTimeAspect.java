package com.ChrisXin.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RecordTimeAspect{

    //@Around("execution(* com.chrisxin.service.impl.*.*(..))")//学习AOP用
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long start = System.currentTimeMillis();
        // 执行目标方法
        Object result = joinPoint.proceed();
        // 记录结束时间
        long end = System.currentTimeMillis();
        // 记录耗时
        log.info("方法{}耗时{}毫秒", joinPoint.getSignature(), end - start);
        return result;
    }
}
