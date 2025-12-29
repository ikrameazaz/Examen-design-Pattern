package org.sid.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.sid.aspects.annotations.SecuredBy;
import org.sid.security.SecurityContext;
import org.sid.security.User;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
public class SecurityAspect {

    @Pointcut("@annotation(org.sid.aspects.annotations.SecuredBy)")
    public void securedMethods() {
    }

    @Around("securedMethods()")
    public Object checkSecurity(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SecuredBy securedBy = method.getAnnotation(SecuredBy.class);
        String[] requiredRoles = securedBy.roles();

        User currentUser = SecurityContext.getCurrentUser();

        if (currentUser == null) {
            throw new SecurityException("Authentification requise");
        }

        if (!currentUser.hasAnyRole(requiredRoles)) {
            throw new SecurityException("Rôles requis: " + Arrays.toString(requiredRoles));
        }

        return joinPoint.proceed();
    }
}
