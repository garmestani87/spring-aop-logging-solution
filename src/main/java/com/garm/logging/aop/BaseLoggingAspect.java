package com.garm.logging.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseLoggingAspect {
    protected void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
        log.error("exception occurred: {}, {} with Exception Message = '{}'",
                joinPoint.getSignature().getName(),
                exception.getMessage(),
                exception);
    }

    protected Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());

        // you can put your logic here before proceeding you method

        if (log.isDebugEnabled()) {
            log.debug("start proceed : {} with arguments = {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
        }

        Object result = joinPoint.proceed();

        // you can put your logic here after proceed you method

        if (log.isDebugEnabled()) {
            log.debug("end proceed : {} result is = {}", joinPoint.getSignature().getName(), result);
        }

        return result;
    }
}