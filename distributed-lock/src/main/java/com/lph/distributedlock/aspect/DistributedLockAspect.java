package com.lph.distributedlock.aspect;

import com.lph.distributedlock.annotation.DistributedLock;
import com.lph.distributedlock.service.ILockService;
import com.lph.distributedlock.service.impl.DistributedLockByRedis;
import com.lph.distributedlock.service.impl.DistributedLockByZookeeper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class DistributedLockAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(com.lph.distributedlock.annotation.DistributedLock)")
    public void distributedLockPoint(){}

    @Around(value = "distributedLockPoint()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        boolean isLocked = false;
        ILockService lockService;
        String lockType = getLockType();
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);
        String key = distributedLock.key();
        if("zookeeper".equalsIgnoreCase(lockType)){
            lockService = applicationContext.getBean(DistributedLockByZookeeper.class);
            isLocked = lockService.acquireLock(key);
        } else if("redis".equalsIgnoreCase(lockType)){
            lockService = applicationContext.getBean(DistributedLockByRedis.class);
            long expire = distributedLock.keepMills();
            int retryTimes = distributedLock.retryTimes();
            long retrySleepMilliSeconds = distributedLock.retrysleepMills();
            isLocked = lockService.acquireLock(key, expire, retryTimes, retrySleepMilliSeconds);
        } else {
            throw new RuntimeException("配置{distributed-lock.type}异常");
        }
        if(isLocked){
            Object object = pjp.proceed();
            lockService.releaseLock(key);
            return object;
        }
        return "无法获取Lock";
    }

    private String getLockType(){
        return applicationContext.getEnvironment()
                .getProperty("distributed-lock.type");
    }

}
