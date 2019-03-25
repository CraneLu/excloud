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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.reflect.generics.tree.ClassSignature;

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
        Method sMethod = ((MethodSignature) pjp.getSignature()).getMethod();
        String key = getLockKey(sMethod);
        DistributedLock distributedLock = sMethod.getAnnotation(DistributedLock.class);
        if("zookeeper".equalsIgnoreCase(lockType)){
            lockService = applicationContext.getBean(DistributedLockByZookeeper.class);
            isLocked = lockService.acquireLock(key);
        } else if("redis".equalsIgnoreCase(lockType)){
            lockService = applicationContext.getBean(DistributedLockByRedis.class);
            long expire = distributedLock.keepMills();
            int retryTimes = distributedLock.retryTimes();
            long retrySleepMilliSeconds = distributedLock.retrysleepMills();
            isLocked = lockService.acquireLock(key, expire, retryTimes, retrySleepMilliSeconds);
            if(!isLocked){
                System.out.println("...获取redis锁失败...");
            }
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

    private String getLockKey(Method method){
        RequestMapping methodAnnotion = method.getAnnotation(RequestMapping.class);
        return methodAnnotion.value()[0].substring(1);
    }

}
