package com.ChrisXin.aop;

import com.ChrisXin.entity.OperateLog;
import com.ChrisXin.mapper.OperateLogMapper;
import com.ChrisXin.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.ChrisXin.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable
    {
        // 记录开始时间
        long start = System.currentTimeMillis();

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 记录耗时
        long end = System.currentTimeMillis();
        long costTime = end - start;

        // 封装日志数据
        OperateLog olog = new OperateLog();
        olog.setOperateEmpId(CurrentHolder.getCurrentId());
        olog.setOperateTime(LocalDateTime.now());
        olog.setClassName(joinPoint.getTarget().getClass().getName());
        olog.setMethodName(joinPoint.getSignature().getName());
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        olog.setReturnValue(result!=null?result.toString():"NoReturnValue");
        olog.setCostTime(costTime);

        // 保存日志数据
        operateLogMapper.insert(olog);
        log.info("记录操作日志{}", olog);

        return result;

    }
}
