package com.lms.eureka.hub.domain.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @AfterThrowing(pointcut = "(execution(* com.lms.eureka.hub.domain.service.HubDomainService..*(..)))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String layer = getLayerName(className);

        log.error("[{}] [Exception] {}.{}(): {}", layer, className, methodName, ex.getMessage());
    }

    @Before("(execution(* com.lms.eureka.hub.domain.service.HubDomainService..*(..)))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String layer = getLayerName(className);

        log.info("[{}] [Executing] {}.{}()", layer, className, methodName);
    }

    @Around("(execution(* com.lms.eureka.hub.domain.service.HubDomainService..*(..)))")
    public Object logAfter(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String layer = getLayerName(className);

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        log.info("[{}] [Completed] {}.{}() - Execution Time: {}ms", layer, className, methodName, executionTime);
        return result;
    }

    private String getLayerName(String className) {
        if (className.toLowerCase().contains("service")) {
            return "Service";
        }
        return "Unknown";
    }

}