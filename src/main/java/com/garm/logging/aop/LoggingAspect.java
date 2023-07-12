package com.garm.logging.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect extends BaseLoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void springControllerPointcut() {
        // put your logic here
    }

    @Pointcut("execution (* com.garm.logging.controller..*.*(..)) || execution (* com.garm.logging.feign..*.*(..))")
    public void appControllerPointcut() {
        // put your logic here
    }

    @Pointcut("execution (* com.garm.logging.exception..*.*(..)) || within(@org.springframework.web.bind.annotation.ControllerAdvice *)")
    public void errorHandlerPointcut() {
        // put your logic here
    }

    @AfterThrowing(pointcut = "appControllerPointcut() || springControllerPointcut()", throwing = "ex")
    public void logAfterThrowingError(JoinPoint joinPoint, Throwable ex) {
        logAfterThrowing(joinPoint, ex);
    }

    @Around("appControllerPointcut() || springControllerPointcut() || errorHandlerPointcut()")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        return logAround(joinPoint);
    }
}
