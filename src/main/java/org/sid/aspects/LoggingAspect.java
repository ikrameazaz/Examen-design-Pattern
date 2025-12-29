package org.sid.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

    @Pointcut("@annotation(org.sid.aspects.annotations.Log)")
    public void loggedMethods() {
    }

    @Around("loggedMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duree = System.currentTimeMillis() - start;

        System.out.println("[LOG] " + methodName + " execute en " + duree + " ms");

        return result;
    }
}
