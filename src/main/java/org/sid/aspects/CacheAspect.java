package org.sid.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class CacheAspect {

    private static Map<String, Object> cache = new HashMap<>();

    @Pointcut("@annotation(org.sid.aspects.annotations.Cachable)")
    public void cachableMethods() {
    }

    // IMPORTANT: Mise a jour du package org.sid.observer.Agent
    @Pointcut("execution(* org.sid.observer.Agent.addTransaction(..))")
    public void addTransactionMethods() {
    }

    @Around("cachableMethods()")
    public Object cacheResult(ProceedingJoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getTarget().hashCode() + "." + joinPoint.getSignature().getName();

        if (cache.containsKey(key)) {
            System.out.println("[CACHE] Resultat trouve");
            return cache.get(key);
        }

        Object result = joinPoint.proceed();
        if (result != null) {
            cache.put(key, result);
            System.out.println("[CACHE] Mise en cache");
        }

        return result;
    }

    @Around("addTransactionMethods()")
    public Object invalidateCache(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        String keyPrefix = String.valueOf(joinPoint.getTarget().hashCode());
        cache.entrySet().removeIf(e -> e.getKey().startsWith(keyPrefix));
        System.out.println("[CACHE] Invalidation du cache");

        return result;
    }

    public static void clearCache() {
        cache.clear();
    }
}
